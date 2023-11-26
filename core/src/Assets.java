
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Assets {
    public static final float FRAME_DURATION = 0.1f;

    static class Ships {
        public static Animation<Texture> VerySmall() {
            Texture frame_1 = new Texture(Gdx.files.internal("Boats/Boat1_water_frame1.png"));
            Texture frame_2 = new Texture(Gdx.files.internal("Boats/Boat1_water_frame2.png"));
            Texture frame_3 = new Texture(Gdx.files.internal("Boats/Boat1_water_frame3.png"));
            Texture frame_4 = new Texture(Gdx.files.internal("Boats/Boat1_water_frame4.png"));
            Texture[] animations = new Texture[] { frame_1, frame_2, frame_3, frame_4 };
            return new Animation<>(FRAME_DURATION, animations);
        }

        public static Animation<Texture> Small() {
            Texture frame_1 = new Texture(Gdx.files.internal("Boats/Boat2_water_frame1.png"));
            Texture frame_2 = new Texture(Gdx.files.internal("Boats/Boat2_water_frame2.png"));
            Texture frame_3 = new Texture(Gdx.files.internal("Boats/Boat2_water_frame3.png"));
            Texture frame_4 = new Texture(Gdx.files.internal("Boats/Boat2_water_frame4.png"));
            Texture[] animations = new Texture[] { frame_1, frame_2, frame_3, frame_4 };
            return new Animation<>(FRAME_DURATION, animations);
        }

        public static Animation<Texture> Medium() {
            Texture frame_1 = new Texture(Gdx.files.internal("Boats/Boat3_water_frame1.png"));
            Texture frame_2 = new Texture(Gdx.files.internal("Boats/Boat3_water_frame2.png"));
            Texture frame_3 = new Texture(Gdx.files.internal("Boats/Boat3_water_frame3.png"));
            Texture frame_4 = new Texture(Gdx.files.internal("Boats/Boat3_water_frame4.png"));
            Texture[] animations = new Texture[] { frame_1, frame_2, frame_3, frame_4 };
            return new Animation<>(FRAME_DURATION, animations);
        }

        public static Animation<Texture> Big() {
            Texture frame_1 = new Texture(Gdx.files.internal("Boats/Boat4_water_frame1.png"));
            Texture frame_2 = new Texture(Gdx.files.internal("Boats/Boat4_water_frame2.png"));
            Texture frame_3 = new Texture(Gdx.files.internal("Boats/Boat4_water_frame3.png"));
            Texture frame_4 = new Texture(Gdx.files.internal("Boats/Boat4_water_frame4.png"));
            Texture[] animations = new Texture[] { frame_1, frame_2, frame_3, frame_4 };
            return new Animation<>(FRAME_DURATION, animations);
        }
    }

    public static Animation<Texture> Fire() {
        Texture frame_1 = new Texture(Gdx.files.internal("Fire/Fire1_1.png"));
        Texture frame_2 = new Texture(Gdx.files.internal("Fire/Fire1_2.png"));
        Texture frame_3 = new Texture(Gdx.files.internal("Fire/Fire1_3.png"));
        Texture frame_4 = new Texture(Gdx.files.internal("Fire/Fire2_1.png"));
        Texture frame_5 = new Texture(Gdx.files.internal("Fire/Fire2_2.png"));
        Texture frame_6 = new Texture(Gdx.files.internal("Fire/Fire2_3.png"));
        Texture frame_7 = new Texture(Gdx.files.internal("Fire/Fire3_1.png"));
        Texture frame_8 = new Texture(Gdx.files.internal("Fire/Fire3_2.png"));
        Texture frame_9 = new Texture(Gdx.files.internal("Fire/Fire3_3.png"));
        Texture frame_10 = new Texture(Gdx.files.internal("Fire/Fire4_1.png"));
        Texture frame_11 = new Texture(Gdx.files.internal("Fire/Fire4_2.png"));
        Texture frame_12 = new Texture(Gdx.files.internal("Fire/Fire4_3.png"));
        Texture[] animations = new Texture[] { frame_1, frame_2, frame_3, frame_4, frame_5, frame_6, frame_7,
                frame_8, frame_9, frame_10, frame_11, frame_12 };
        return new Animation<>(FRAME_DURATION, animations);
    }

    public static Animation<Texture> Sea() {
        Texture frame_1 = new Texture(Gdx.files.internal("Water/water_0.png"));
        Texture frame_2 = new Texture(Gdx.files.internal("Water/water_1.png"));
        Texture frame_3 = new Texture(Gdx.files.internal("Water/water_2.png"));
        Texture[] animations = new Texture[] { frame_1, frame_2, frame_3 };
        return new Animation<>(FRAME_DURATION, animations);
    }
}
