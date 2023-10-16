package dev.amaghzaz.navalbattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
    private final Texture texture;
    private int hitpoints = 0;
    private Vector2 position = new Vector2();
    private int id = 0;
    private Direction direction = Direction.Horizontal;
    private Type type = Type.VerySmall;
    private Array<Vector2> collisionBox = new Array<Vector2>();
    private float rotation = 0.0F;

    public Ship(Type type, Direction direction, Vector2 position) {
        super();
        this.position = position;
        this.type = type;
        this.direction = direction;
        if (direction == Direction.Horizontal) {
            this.rotation = 90.0F;
        }
        this.texture = new Texture(Gdx.files.internal("Boats/Boat1_water_frame1.png"));
    }

    @Override
    public void draw(Batch batch) {
        this.setPosition(this.position.x, this.position.y);
        this.setRotation(this.rotation);
        batch.draw(texture, position.x, position.y);
    }

    public void dispose(){
        texture.dispose();
    }
}
