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
	Set<Rectangle> cells;
	boolean isDrawBounds = false;
	boolean isSelected = false;

	public Ship(Vector2 position, Direction direction, Type type) {
		super(position, direction, type);
		this.position = normalizeVector2(position);
		setAnimation();
		autoScale();
		autoPosition();
		autoRotation();
		stateTime = 0.0F;
		cells = new HashSet<>();
	}

	public void addShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public int touches() {
		return cells.size();
	}

	public boolean isDead() {
		switch (type) {
			case VerySmall:
				return cells.size() >= 2;
			case Small:
				return cells.size() >= 4;
			case Medium:
				return cells.size() >= 6;
			case Big:
				return cells.size() >= 4;
		}
		return false;
	}

	private void autoPosition() {
		if (direction == Direction.Vertical) {
			if (type == Type.Medium) {
				sprite.setCenterX(this.position.x);
				sprite.setCenterY(this.position.y + 20);
			} else {
				sprite.setCenterX(this.position.x + 20);
				sprite.setCenterY(this.position.y);
			}
		} else {
			if (type == Type.Medium) {
				sprite.setCenterX(this.position.x + 20);
				sprite.setCenterY(this.position.y);
			} else {
				sprite.setCenterX(this.position.x);
				sprite.setCenterY(this.position.y + 20);
			}
		}
	}

	public void onTouchDown(Vector2 mouse, int type) {
		// 0 left, 1 right
		System.out.println(type);
		handleInitialPosition(mouse, type);
	}

	private void autoFlipXY() {
		direction = direction == Direction.Vertical ? Direction.Horizontal : Direction.Vertical;
		sprite.rotate(90f);
	}

	private void handleInitialPosition(Vector2 position, int type) {
		if (getBounds().contains(position) || isSelected) {
			isDrawBounds = true;
			if (isSelected) {
				position = normalizeVector2(position);
				sprite.setPosition(position.x - 40, position.y - 40);
				if (type == 1)
					autoFlipXY();
				isSelected = false;
			} else {
				isSelected = true;
			}
		} else {
			isDrawBounds = false;
			isSelected = false;
		}
	}

	public void handleMissile(Vector2 position) {
		if (getBounds().contains(position)) {
			position = normalizeVector2(position);
			cells.add(new Rectangle(position.x, position.y, 40, 40));
		} else {
		}
		if (isDead()) {
			sprite.setColor(Color.NAVY);
		}
	}

	public Vector2 normalizeVector2(Vector2 click) {
		int rX = (int) click.x % 40; // 40 is the cell size
		int rY = (int) click.y % 40;
		return new Vector2(click.x - rX, click.y - rY);
	}

	public void autoRotation() {
		if (direction == Direction.Horizontal)
			sprite.rotate90(false);
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
		if (isDrawBounds) {
			var rect = getBounds();
			renderer.set(ShapeType.Line);
			renderer.setColor(Color.GREEN);
			renderer.rect(rect.x, rect.y, rect.width, rect.height);
		}
	}

	public Rectangle getBounds() {
		var rect = new Rectangle();
		rect.x = sprite.getBoundingRectangle().x + 45;
		rect.y = sprite.getBoundingRectangle().y + 15;
		switch (type) {
			case VerySmall:
				if (direction == Direction.Vertical) {
					rect.x = sprite.getX() + 45;
					rect.y = sprite.getY() + 25;
					rect.width = 40;
					rect.height = 40 * 2f;
				} else {
					rect.x = sprite.getX() + 25;
					rect.y = sprite.getY() + 45;
					rect.width = 40 * 2f;
					rect.height = 40;
				}
				break;
			case Small:
				if (direction == Direction.Vertical) {
					rect.x = sprite.getX() + 45;
					rect.y = sprite.getY() - 15;
					rect.width = 40;
					rect.height = 40 * 4f;
				} else {
					rect.x = sprite.getX() - 15;
					rect.y = sprite.getY() + 45;
					rect.width = 40 * 4f;
					rect.height = 40;
				}
				break;
			case Medium:
				if (direction == Direction.Vertical) {
					rect.x = sprite.getX() + 25;
					rect.y = sprite.getY() + 5;
					rect.width = 40 * 2f;
					rect.height = 40 * 3f;
				} else {
					rect.x = sprite.getX() + 5;
					rect.y = sprite.getY() + 25;
					rect.width = 40 * 3f;
					rect.height = 40 * 2f;
				}
				break;
			case Big:
				if (direction == Direction.Vertical) {
					rect.x = sprite.getX() + 45;
					rect.y = sprite.getY() - 15;
					rect.width = 40;
					rect.height = 40 * 4f;
				} else {
					rect.x = sprite.getX() - 15;
					rect.y = sprite.getY() + 45;
					rect.width = 40 * 4f;
					rect.height = 40;
				}
				break;
		}
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
					sprite.setScale(0.8f, 1.7f);
				else
					sprite.setScale(1.7f, 0.8f);
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
		if (!isDead()) {
			for (Rectangle cell : cells) {
				renderer.set(ShapeType.Line);
				renderer.setColor(Color.RED);
				renderer.rect(cell.x, cell.y, cell.width, cell.height);
			}
		}
	}
}