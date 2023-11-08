import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Ship extends ShipBase {
	float stateTime;
	Animation<Texture> animation;
	Sprite sprite;
	ShapeRenderer renderer;
	boolean bboxState = false;
	public Actor actor;

	public Ship(Vector2 position, Direction direction, Type type) {
		super(position, direction, type);
		actor = new Actor();
		switch (type) {
			case VerySmall:
				this.animation = Assets.Ships.VerySmall();
				break;
			case Small:
				this.animation = Assets.Ships.Small();
				break;
			case Medium:
				this.animation = Assets.Ships.Medium();
				break;
			case Big:
				this.animation = Assets.Ships.Big();
				break;
		}
		Texture currentFrame = animation.getKeyFrames()[0];
		sprite = new Sprite(currentFrame);
		sprite.setPosition(position.x, position.y);
		stateTime = 0.0F;
		if (direction == Direction.Horizontal)
			sprite.rotate90(true);
		actor.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("down");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("up");
			}
			// public void
		});
	}

	public void addShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public void boundingBox(boolean state) {
		bboxState = state;
		if (bboxState == true) {
			Rectangle rec = sprite.getBoundingRectangle();
			renderer.begin(ShapeType.Line);
			renderer.setColor(Color.RED);
			renderer.rect(rec.x, rec.y, rec.width, rec.height);
			renderer.end();
		}
	}

	public void draw(Batch batch) {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		Texture currentFrame = animation.getKeyFrame(stateTime, true);
		sprite.setTexture(currentFrame);
		sprite.draw(batch);
		actor.draw(batch, 0.5f);
	}

	public void dispose() {

	}
}