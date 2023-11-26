import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Gui {
	private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
	private FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	private BitmapFont font;
	private Player player;
	private Player opponent;

	public Gui() {
		parameter.size = 20;
		font = generator.generateFont(parameter); // font size 36 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setOpponent(Player player) {
		this.opponent = player;
	}

	public void draw(Batch batch) {
		font.draw(batch, "Enemy: " + opponent.getPlayerName() + " - score :" + opponent.getScore(), 100, 500);
		font.draw(batch, "Player: " + player.getPlayerName() + " - score :" + player.getScore(), 400, 500);
	}
}
