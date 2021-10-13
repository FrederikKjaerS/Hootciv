package hotciv.variants.UnitAndTileStrategy;

import hotciv.framework.*;
import hotciv.utility.NeighborTiles;

public class ThetaUnitAndTileStrategy implements UnitAndTileStrategy {
    private UnitAndTileStrategy normalUnitAndTileStrategy;

    public ThetaUnitAndTileStrategy() {
        this.normalUnitAndTileStrategy = new NormalUnitAndTileStrategy();
    }

    @Override
    public boolean canMoveToTile(Unit unit, String tile) {
        if (unit != null && unit.getTypeString().equals(GameConstants.SANDWORM)
                && !tile.equals(GameConstants.DESERT)) {
            return false;
        }
        return normalUnitAndTileStrategy.canMoveToTile(unit, tile);
    }

    @Override
    public void setProduction(ExtendedGame game, Position position, String unitType) {
        if (unitType.equals(GameConstants.SANDWORM)) {
            for (Position p : NeighborTiles.getCenterAnd8neighborhoodOf(position)) {
                if (game.getTileAt(p).getTypeString().equals(GameConstants.DESERT)) {
                    normalUnitAndTileStrategy.setProduction(game, position, unitType);
                    break;
                }
            }
        } else {
            normalUnitAndTileStrategy.setProduction(game, position, unitType);
        }
    }
}
