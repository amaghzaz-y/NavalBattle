import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Bounds {
	public void draw(ShapeRenderer renderer) {
		var width = 960;
		var height = 520;
		var cellSize = 40; // 10, 12, 20, 40, 30... are possible
		renderer.setColor(Color.BLACK);
		renderer.setAutoShapeType(true);
		renderer.begin(ShapeType.Line);
		for (int i = 0; i < width; i += cellSize) {
			for (int e = 0; e < height; e += cellSize) {
				renderer.rect(i, e, cellSize, cellSize);
			}
		}
		renderer.set(ShapeType.Filled);
		renderer.setColor(Color.BLACK);
		renderer.rectLine(320, 0, 320, height, 5);
		renderer.rectLine(640, 0, 640, height, 5);
		renderer.end();
	}
}
