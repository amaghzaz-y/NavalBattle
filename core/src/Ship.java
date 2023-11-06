import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Ship extends Sprite {
	private ShipBase base;
	float stateTime;
	Animation<Texture> animation;

	public Ship(Vector2 position, ShipBase.Direction direction, ShipBase.Type type) {
		super();
		this.base = new ShipBase(position, direction, type);
		switch (type) {
			case VerySmall:
				this.animation = Assets.Ships.VerySmall();
				break;
			case Small:
				this.animation = Assets.Ships.Small();
				break;
			case Medium:
				this.animation = Assets.Ships.Medium();
				break;
			case Big:
				this.animation = Assets.Ships.Big();
				break;
		}
		this.stateTime = 0.0F;
	}

	@Override
	public void draw(Batch batch) {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		Texture currentFrame = animation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, base.getPosition().x, base.getPosition().y);
	}

	public void dispose() {

	}
}