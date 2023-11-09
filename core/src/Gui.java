import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Gui {
	ShapeRenderer renderer;
	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	BitmapFont font;
	BitmapFont fontSmall;
	BitmapFont fontVerySmall;

	public Gui() {
		parameter.size = 36;
		font = generator.generateFont(parameter); // font size 36 pixels
		parameter.size = 20;
		fontSmall = generator.generateFont(parameter); // font size 20 pixels
		parameter.size = 16;
		fontVerySmall = generator.generateFont(parameter); // font size 20 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}

	public void addShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public void drawFont(Batch batch) {
		font.draw(batch, "Naval Battle 2D", 670, 480);
		font.draw(batch, "Score : 0", 670, 440);
		// chat
		font.draw(batch, "Chat :", 670, 380);
		fontVerySmall.draw(batch, "Player 1: Test Message Placeholder", 670, 320);
		fontVerySmall.draw(batch, "Player 2: Test Message Placeholder", 670, 290);
		fontSmall.draw(batch, "-> Click Here to type message", 670, 40);
		fontSmall.draw(batch, "-> Click Here to type message", 670, 40);
		// players
		font.draw(batch, "Player 1 : ", 100, 500);
		font.draw(batch, "Player 2 : ", 400, 500);
	}

	public void draw() {
		var height = 520;

		renderer.begin(ShapeType.Line);
		renderer.setColor(Color.WHITE);
		renderer.rect(640, 0, 320, height);
		renderer.end();
	}
}
