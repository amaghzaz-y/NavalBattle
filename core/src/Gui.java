import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Gui {
	ShapeRenderer renderer;

	public void addShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public void draw() {
		var height = 520;
		renderer.begin(ShapeType.Filled);
		renderer.setColor(Color.LIGHT_GRAY);
		renderer.rect(640, 0, 320, height);
		renderer.end();
	}
}
