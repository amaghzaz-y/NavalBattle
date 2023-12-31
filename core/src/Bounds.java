import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Bounds {
	private ShapeRenderer renderer;
	final int cellSize = 40;

	public void addShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public void renderTurn(boolean turn) {
		if (turn == true) {
			renderer.setColor(Color.GREEN);
			renderer.set(ShapeType.Line);
			renderer.rect(320, 0, 320, 440);
		} else {
			renderer.setColor(Color.RED);
			renderer.set(ShapeType.Line);
			renderer.rect(0, 0, 320, 440);
		}
	}

	public void render() {
		var height = 520;
		renderer.setColor(Color.BLACK);
		renderer.set(ShapeType.Line);
		for (int i = 0; i < 320; i += cellSize) {
			for (int e = 0; e < height - 80; e += cellSize) {
				renderer.rect(i, e, cellSize, cellSize);
			}
		}
		renderer.set(ShapeType.Filled);
		renderer.setColor(Color.BLACK);
		renderer.rectLine(320, 0, 320, height, 5);
	}
}
