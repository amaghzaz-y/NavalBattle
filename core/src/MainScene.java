// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.scenes.scene2d.Actor;
// import com.badlogic.gdx.scenes.scene2d.Group;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.Table;
// import com.badlogic.gdx.utils.Array;

// public class MainScene extends Group {
// public Array<Actor> actors;
// private Stage stage;
// private Table table;

// public MainScene() {

// }

// public void create() {
// stage = new Stage();
// Gdx.input.setInputProcessor(stage);
// table = new Table();
// table.setFillParent(true);
// Ship ship = new Ship(new Vector2(100, 100), ShipBase.Direction.Horizontal,
// ShipBase.Type.Big);
// stage.addActor(table);
// table.addActor(ship.actor);
// table.setDebug(true);
// // This is optional, but enables debug lines for
// // Add widgets to the table here.
// }

// public void render() {
// stage.act(Gdx.graphics.getDeltaTime());
// stage.draw();
// }

// public void dispose() {
// stage.dispose();
// }
// }
