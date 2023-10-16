package dev.amaghzaz.navalbattle;

import com.badlogic.gdx.Gdx;
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
    private final Texture texture;
    private int hitpoints;
    private Vector2 position = new Vector2();
    private int id;
    private Direction direction = Direction.Horizontal;
    private Type type = Type.VerySmall;
    private Array<Vector2> collisionBox;
    private float rotation;

    public Ship(Type type, Direction direction, Vector2 position) {
        super();
        stateTime = 0.0F;
        this.position = position;
        this.type = type;
        this.direction = direction;
        if (direction == Direction.Horizontal) {
            this.rotation = 90.0F;
        }
        switch (type){
            case VerySmall:
                this.animation =  Assets.Ships.VerySmall();
            case Small:
                this.animation =  Assets.Ships.Small();
            case Medium:
                this.animation =  Assets.Ships.Medium();
            case Big:
                this.animation =  Assets.Ships.Big();
        }
        this.texture = new Texture(Gdx.files.internal("Boats/Boat1_water_frame1.png"));
    }

    @Override
    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        Texture currentFrame = animation.getKeyFrame(stateTime, true);
        this.setPosition(this.position.x, this.position.y);
        this.setRotation(this.rotation);
        batch.draw(currentFrame, position.x, position.y);
    }

    public void dispose(){
        texture.dispose();
    }
}
