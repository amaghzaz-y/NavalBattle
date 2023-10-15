package dev.amaghzaz.navalbattle;

import com.badlogic.gdx.utils.Array;

public class GameInstance {
    public static GameInstance instance;
    private Array<Ship> ships = new Array<Ship>();
    public static GameInstance getInstance() {
        if (instance == null) {
            instance = new GameInstance();
        }
        return instance;
    }

}
