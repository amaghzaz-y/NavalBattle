import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Cells {
	public void render(ShapeRenderer renderer) {
		var width = 960;
		var height = 540;
		var cellSize = 30; // 10, 12, 20, 40, 30... are possible
		renderer.setColor(Color.RED);
		renderer.begin(ShapeType.Line);
		for (int i = 0; i < width; i += cellSize) {
			for (int e = 0; e < height; e += cellSize) {
				renderer.rect(i, e, cellSize, cellSize);
			}
		}

		renderer.end();
	}
}
