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
import com.badlogic.gdx.utils.Array;

public class Ship extends ShipBase {
	float stateTime;
	Animation<Texture> animation;
	Sprite sprite;
	ShapeRenderer renderer;
	boolean bboxState = false;
	Array<Vector2> hp;
	public Ship(Vector2 position, Direction direction, Type type) {
		super(position, direction, type);
		setAnimation(type);
		autoScale(direction, type);
		stateTime = 0.0F;
		setTransform(position, direction);
		hp = new Array<>();
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
			renderer.set(ShapeType.Line);
			renderer.setColor(Color.RED);
			renderer.rect(rec.x, rec.y, rec.width, rec.height);
		}
	}

	public void setTransform(Vector2 position, Direction direction) {
		sprite.setPosition(position.x, position.y);
		if (direction == Direction.Horizontal)
			sprite.rotate90(true);
	}

	public void setAnimation(Type type) {
		switch (type) {
			case VerySmall:
				animation = Assets.Ships.VerySmall();
				break;
			case Small:
				animation = Assets.Ships.Small();
				break;
			case Medium:
				animation = Assets.Ships.Medium();
				break;
			case Big:
				animation = Assets.Ships.Big();
				break;
		}
		Texture currentFrame = animation.getKeyFrames()[0];
		sprite = new Sprite(currentFrame);
	}

	public void autoScale(Direction direction, Type type) {
		switch (type) {
			case VerySmall:
				if (direction == Direction.Vertical)
					sprite.setScale(1.0f, 0.8f);
				else
					sprite.setScale(0.8f, 1.0f);
				break;
			case Small:
				if (direction == Direction.Vertical)
					sprite.setScale(1.0f, 1.3f);
				else
					sprite.setScale(1.3f, 1.0f);
				break;
			case Medium:
				if (direction == Direction.Vertical)
					sprite.setScale(1.0f, 1.3f);
				else
					sprite.setScale(1.3f, 1.0f);
				break;
			case Big:
				if (direction == Direction.Vertical)
					sprite.setScale(1.0f, 1.3f);
				else
					sprite.setScale(1.3f, 1.0f);
				break;
			default:
				break;
		}
	}

	public void draw(Batch batch) {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		Texture currentFrame = animation.getKeyFrame(stateTime, true);
		sprite.setTexture(currentFrame);
		sprite.draw(batch);
	}
}