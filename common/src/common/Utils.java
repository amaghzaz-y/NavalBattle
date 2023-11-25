package common;

import com.badlogic.gdx.math.Vector2;

import common.ShipBase.Direction;

public class Utils {
	public static Vector2 normalizeVector2(Vector2 click) {
		int rX = (int) click.x % 40; // 40 is the cell size
		int rY = (int) click.y % 40;
		return new Vector2(click.x - rX, click.y - rY);
	}

	public static Vector2 serializeClick(Vector2 click) {
		var npos = normalizeVector2(click);
		return new Vector2(npos.x / 40, npos.y / 40);
	}

	public static ShipBase.Type getTypeFromInt(int number) {
		switch (number) {
			case 0:
				return ShipBase.Type.VerySmall;
			case 1:
				return ShipBase.Type.Small;
			case 2:
				return ShipBase.Type.Medium;
			case 3:
				return ShipBase.Type.Big;
		}
		return ShipBase.Type.VerySmall;
	}

	public static int getDirectionInt(Direction direction) {
		return direction == Direction.Vertical ? 0 : 1;
	}

	public static Direction getDirectionFromInt(int number) {
		return number == 0 ? Direction.Vertical : Direction.Horizontal;
	}
}
