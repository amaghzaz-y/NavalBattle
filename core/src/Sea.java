
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Sea extends Sprite {

    float stateTime;
    Animation<Texture> animation;

    public Sea() {
        super();
        this.animation = Assets.Sea();
    }

    @Override
    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        Texture currentFrame = animation.getKeyFrame(stateTime, true);
        for (int x = 0; x < 640; x += 64) {
            for (int y = 0; y < 520; y += 64) {
                batch.draw(currentFrame, x, y);
            }
        }
    }

    public void dispose() {
    }
}
