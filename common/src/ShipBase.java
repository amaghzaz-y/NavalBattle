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

    private int id;
    private int size;
    private int health; // mutable size that represents health
    private Vector2 position; // head of the boat, top to bottom, pos in board cases
    private Array<Vector2> hitpoints; // hull of the boat
    private Array<Vector2> sunkParts; // parts of the ship that are destroyed

    private Direction direction;
    private Type type;

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

    public boolean isHit(Vector2 missile) {
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