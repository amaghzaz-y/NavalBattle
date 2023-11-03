import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Ship extends Sprite {

    public enum Direction {
        Horizontal,
        Vertical
    }

    public enum Type {
        VerySmall,
        Small,
        Medium,
        Big
    }

    float stateTime;
    Animation<Texture> animation;
    public int hitpoints;
    public Vector2 position = new Vector2();
    public int id;
    public Direction direction;
    public Type type;
    public Array<Vector2> collisionBox;
    public float rotation;

    public Ship(Type type, Direction direction, Vector2 position) {
        super();
        this.stateTime = 0.0F;
        this.position = position;
        this.type = type;
        if (this.direction == Direction.Horizontal) {
            this.setRotation(90.0F);
        }
        this.rotation = 30.0F;
        if (type == Type.VerySmall) {
            this.animation = Assets.Ships.VerySmall();
        }
        if (type == Type.Small) {
            this.animation = Assets.Ships.Small();
        }
        if (type == Type.Medium) {
            this.animation = Assets.Ships.Medium();
        }
        if (type == Type.Big) {
            this.animation = Assets.Ships.Big();
        }
        this.setPosition(this.position.x, this.position.y);
        this.setColor(Color.RED);
    }

    @Override
    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        Texture currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, position.x, position.y);
    }

    public void dispose() {

    }
}
