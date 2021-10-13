package hotciv.variants.UnitAndTileStrategy;

import hotciv.framework.GameConstants;
import hotciv.framework.Unit;

public class NormalUnitAndTileStrategy implements UnitAndTileStrategy {
    @Override
    public boolean canMoveToTile(Unit unit, String tile) {
        return !(tile.equals(GameConstants.OCEANS)
                || tile.equals(GameConstants.MOUNTAINS));
    }
}
