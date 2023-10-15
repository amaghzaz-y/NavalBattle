package dev.amaghzaz.navalbattle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class NavalBattle extends ApplicationAdapter {
	SpriteBatch batch;
	Ship ship;
	@Override
	public void create() {
		batch = new SpriteBatch();
		ship = new Ship(Ship.Type.VerySmall, Ship.Direction.Horizontal, new Vector2(10,10));
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 1, 1);
		batch.begin();
		ship.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		ship.dispose();
	}
}
