import java.util.HashSet;
import java.util.Set;

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
	Set<Rectangle> cells;

	public Ship(Vector2 position, Direction direction, Type type) {
		super(position, direction, type);
		setAnimation();
		autoScale();
		setTransform();
		stateTime = 0.0F;
		cells = new HashSet<>();
	}

	public void addShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public void handleClick(Vector2 mouse) {
		if (sprite.getBoundingRectangle().contains(mouse.x, mouse.y)) {
			mouse = normalizedClick(mouse);
			cells.add(new Rectangle(mouse.x, mouse.y, 40, 40));
			bboxState = true;
		} else {
			bboxState = false;
		}
	}

	public Vector2 normalizedClick(Vector2 click) {
		int rX = (int) click.x % 40; // 40 is the cell size
		int rY = (int) click.y % 40;
		return new Vector2(click.x - rX, click.y - rY);
	}

	public void setTransform() {
		sprite.setPosition(position.x, position.y);
		if (direction == Direction.Horizontal)
			sprite.rotate90(true);
	}

	public void setAnimation() {
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

	public void renderBounds() {
		var rect = getBounds();
		renderer.set(ShapeType.Line);
		renderer.setColor(Color.RED);
		renderer.rect(rect.x, rect.y, rect.width, rect.height);
	}

	public Rectangle getBounds() {
		var rect = new Rectangle();
		rect.x = sprite.getX();
		rect.y = sprite.getY();
		rect.width = 40 * 3;
		rect.height = 40 * 3;
		// switch (type) {
		// case VerySmall:
		// rect.x = sprite.getX();
		// rect.y = sprite.getY();
		// if (direction == Direction.Vertical) {

		// }
		// break;
		// }
		return rect;
	}

	public void autoScale() {
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

	public void render() {
		if (bboxState) {
			for (Rectangle cell : cells) {
				renderer.set(ShapeType.Line);
				renderer.setColor(Color.RED);
				renderer.rect(cell.x, cell.y, cell.width, cell.height);
			}
		}
	}
}