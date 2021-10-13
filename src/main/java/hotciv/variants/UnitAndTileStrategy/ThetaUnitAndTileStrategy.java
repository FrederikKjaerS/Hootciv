package hotciv.variants.UnitAndTileStrategy;

import hotciv.framework.GameConstants;
import hotciv.framework.Unit;

public class ThetaUnitAndTileStrategy implements UnitAndTileStrategy {
    private UnitAndTileStrategy normalUnitAndTileStrategy;

    public ThetaUnitAndTileStrategy() {
        this.normalUnitAndTileStrategy = new NormalUnitAndTileStrategy();
    }

    @Override
    public boolean canMoveToTile(Unit unit, String tile) {
        if(unit != null && unit.getTypeString().equals(GameConstants.SANDWORM)
                && !tile.equals(GameConstants.DESERT)) {
            return false;
        }
        return normalUnitAndTileStrategy.canMoveToTile(unit,tile);
    }
}
