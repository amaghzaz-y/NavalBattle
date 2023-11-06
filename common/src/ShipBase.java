import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ShipBase {

    public enum Direction {
        Horizontal,
        Vertical,
    }

    public enum Type {
        VerySmall, // size 2
        Small, // size 3
        Medium, // size 4
        Big // size 5
    }

    public int id = 0;
    public int size = 0;
    public int health = 0; // mutable size that represents health
    public Vector2 position = new Vector2(0, 0); // head of the boat, top to bottom, pos in board cases
    public Array<Vector2> hitpoints = new Array<>(); // hull of the boat
    public Array<Vector2> sunkParts = new Array<>(); // parts of the ship that are destroyed

    public Direction direction = Direction.Horizontal;
    public Type type = Type.VerySmall;

    ShipBase(Vector2 position, Direction directon, Type type) {
        this.position = position;
        this.direction = directon;
        this.type = type;
        this.size = getSize();
        this.health = size;

        for (int i = 0; i < size; i++) {
            switch (direction) {
                case Vertical:
                    hitpoints.add(position.add(0, i));
                    break;

                case Horizontal:
                    hitpoints.add(position.add(i, 0));
                    break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        switch (type) {
            case VerySmall:
                size = 2;
                break;
            case Small:
                size = 3;
                break;
            case Medium:
                size = 4;
                break;
            case Big:
                size = 5;
                break;
            default:
                size = 5;
                break;
        }
        return size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Array<Vector2> getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Array<Vector2> hitpoints) {
        this.hitpoints = hitpoints;
    }

    public Direction getDirection() {
        return direction;
    }

    public Type getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public boolean computeHitCheck(Vector2 missile) {
        // we ignore the hit if the part is already destoryed
        for (Vector2 sunkpart : sunkParts) {
            if (sunkpart.equals(missile)) {
                return false;
            }
        }
        // we destroy the collided part
        for (Vector2 hp : hitpoints) {
            if (hp.equals(missile)) {
                // deduct a healthpoint and add the missile pos to the sunkpart
                sunkParts.add(missile);
                health--;
                return true;
            }
        }
        return false;
    }

    public boolean isCollision(ShipBase other) {
        for (Vector2 selfcell : hitpoints) {
            for (Vector2 othercell : other.getHitpoints()) {
                if (selfcell.equals(othercell)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSunk() {
        if (health <= 0) {
            return true;
        }
        return false;
    }
}