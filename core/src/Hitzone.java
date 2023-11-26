
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import common.Utils;

public class Hitzone {
	private ShapeRenderer renderer;
	final int cellSize = 40;
	private Set<Rectangle> cells;
	private int height = 520;
	private int width = 320;

	public Hitzone() {
		cells = new HashSet<>();
		for (int i = 0; i < width; i += cellSize) {
			for (int e = 0; e < height - 80; e += cellSize) {
				cells.add(new Rectangle(i, e, cellSize, cellSize));
			}
		}
	}

	public void setRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public void onTouchDown(Vector2 position) {
		position = Utils.normalizeVector2(position);
		cells.remove(new Rectangle(position.x, position.y, 40, 40));
	}

	public void render() {
		renderer.setColor(Color.BLACK);
		renderer.set(ShapeType.Filled);
		for (Rectangle cell : cells) {
			renderer.rect(cell.x, cell.y, cell.width, cell.height);
		}
	}
}
