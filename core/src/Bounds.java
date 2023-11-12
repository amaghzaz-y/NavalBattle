import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Bounds {
	private ShapeRenderer renderer;
	final int cellSize = 40;

	public void addShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public void render() {
		var height = 520;
		renderer.setColor(Color.BLACK);
		renderer.set(ShapeType.Line);
		for (int i = 0; i < 640; i += cellSize) {
			for (int e = 0; e < height - 80; e += cellSize) {
				renderer.rect(i, e, cellSize, cellSize);
			}
		}
		renderer.set(ShapeType.Filled);
		renderer.setColor(Color.BLACK);
		renderer.rectLine(320, 0, 320, height, 5);
		renderer.rectLine(640, 0, 640, height, 5);
	}
}
