package dev.amaghzaz.navalbattle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class NavalBattle extends ApplicationAdapter {
	SpriteBatch batch;
	Array<Ship> ships = new Array<>();
	Sea sea;
	@Override
	public void create() {
		batch = new SpriteBatch();
		ships.add(new Ship(Ship.Type.VerySmall, Ship.Direction.Horizontal, new Vector2(20,20)));
		ships.add(new Ship(Ship.Type.Small, Ship.Direction.Vertical, new Vector2(100,100)));
		ships.add(new Ship(Ship.Type.Medium, Ship.Direction.Horizontal, new Vector2(200,100)));
		ships.add(new Ship(Ship.Type.Big, Ship.Direction.Vertical, new Vector2(300,400)));
		sea = new Sea();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 1, 1);
		batch.begin();
		sea.draw(batch);
		for(int i=0;i<ships.size;i++){
			ships.get(i).draw(batch);
		}

		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
