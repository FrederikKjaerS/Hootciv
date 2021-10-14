package ThetaCiv;

import hotciv.framework.*;
import hotciv.utility.NeighborTiles;
import hotciv.variants.unitAndTileStrategy.NormalUnitAndTileStrategy;
import hotciv.variants.unitAndTileStrategy.UnitAndTileStrategy;

public class ThetaUnitAndTileStrategy implements UnitAndTileStrategy {
    private UnitAndTileStrategy normalUnitAndTileStrategy;

    public ThetaUnitAndTileStrategy() {
        this.normalUnitAndTileStrategy = new NormalUnitAndTileStrategy();
    }

    @Override
    public boolean canMoveToTile(Unit unit, String tile) {
        if (unit != null && unit.getTypeString().equals(GameConstants.SANDWORM)
                && !tile.equals(ThetaCivGameConstants.DESERT)) {
            return false;
        }
        return normalUnitAndTileStrategy.canMoveToTile(unit, tile);
    }

    @Override
    public void setProduction(ExtendedGame game, Position position, String unitType) {
        if (unitType.equals(GameConstants.SANDWORM)) {
            for (Position p : NeighborTiles.getCenterAnd8neighborhoodOf(position)) {
                if (game.getTileAt(p).getTypeString().equals(ThetaCivGameConstants.DESERT)) {
                    normalUnitAndTileStrategy.setProduction(game, position, unitType);
                    break;
                }
            }
        } else {
            normalUnitAndTileStrategy.setProduction(game, position, unitType);
        }
    }
}
