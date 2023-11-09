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

public class Ship extends ShipBase {
	float stateTime;
	Animation<Texture> animation;
	Sprite sprite;
	ShapeRenderer renderer;
	boolean bboxState = false;

	public Ship(Vector2 position, Direction direction, Type type) {
		super(position, direction, type);

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
	}

	public void addShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public void handleClick(Vector2 mouse) {
		if (sprite.getBoundingRectangle().contains(mouse.x, mouse.y)) {
			bboxState = true;
		} else {
			bboxState = false;
		}
	}

	public void drawBoundingBox() {
		if (bboxState) {
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
	}
}