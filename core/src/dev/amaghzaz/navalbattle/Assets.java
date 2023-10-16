package dev.amaghzaz.navalbattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;
import org.w3c.dom.Text;

public class Assets {
    static class  Ships{
        public static Animation<Texture> VerySmall(){
            Texture frame_1 =  new Texture(Gdx.files.internal("Boats/Boat1_water_frame1.png"));
            Texture frame_2 =  new Texture(Gdx.files.internal("Boats/Boat1_water_frame2.png"));
            Texture frame_3 =  new Texture(Gdx.files.internal("Boats/Boat1_water_frame3.png"));
            Texture frame_4 =  new Texture(Gdx.files.internal("Boats/Boat1_water_frame4.png"));
            Texture[] animations = new Texture[]{frame_1, frame_2, frame_3, frame_4};
            return new Animation<>(0.025f, animations);
        }
        public Animation<Texture> Small(){
            Texture frame_1 =  new Texture(Gdx.files.internal("Boats/Boat2_water_frame1.png"));
            Texture frame_2 =  new Texture(Gdx.files.internal("Boats/Boat2_water_frame2.png"));
            Texture frame_3 =  new Texture(Gdx.files.internal("Boats/Boat2_water_frame3.png"));
            Texture frame_4 =  new Texture(Gdx.files.internal("Boats/Boat2_water_frame4.png"));
            Texture[] animations = new Texture[]{frame_1, frame_2, frame_3, frame_4};
            return new Animation<>(0.025f, animations);
        }
        public Animation<Texture> Medium(){
            Texture frame_1 =  new Texture(Gdx.files.internal("Boats/Boat3_water_frame1.png"));
            Texture frame_2 =  new Texture(Gdx.files.internal("Boats/Boat3_water_frame2.png"));
            Texture frame_3 =  new Texture(Gdx.files.internal("Boats/Boat3_water_frame3.png"));
            Texture frame_4 =  new Texture(Gdx.files.internal("Boats/Boat3_water_frame4.png"));
            Texture[] animations = new Texture[]{frame_1, frame_2, frame_3, frame_4};
            return new Animation<>(0.025f, animations);
        }
        public Animation<Texture> Big(){
            Texture frame_1 =  new Texture(Gdx.files.internal("Boats/Boat4_water_frame1.png"));
            Texture frame_2 =  new Texture(Gdx.files.internal("Boats/Boat4_water_frame2.png"));
            Texture frame_3 =  new Texture(Gdx.files.internal("Boats/Boat4_water_frame3.png"));
            Texture frame_4 =  new Texture(Gdx.files.internal("Boats/Boat4_water_frame4.png"));
            Texture[] animations = new Texture[]{frame_1, frame_2, frame_3, frame_4};
            return new Animation<>(0.025f, animations);
        }
    }
    public Animation<Texture> Sea() {
        Texture frame_1 =  new Texture(Gdx.files.internal("Water/water_0.png"));
        Texture frame_2 =  new Texture(Gdx.files.internal("Water/water_1.png"));
        Texture frame_3 =  new Texture(Gdx.files.internal("Water/water_2.png"));
        Texture[] animations = new Texture[]{frame_1, frame_2, frame_3};
        return new Animation<>(0.025f, animations);
    }
}

