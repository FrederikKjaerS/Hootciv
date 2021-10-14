package ThetaCiv;

import hotciv.framework.*;
import hotciv.utility.NeighborTiles;
import hotciv.variants.unitAndTileStrategy.NormalUnitAndTileStrategy;
import hotciv.variants.unitAndTileStrategy.ProductionStrategy;

public class ThetaUnitAndTileStrategy implements ProductionStrategy {
    private ProductionStrategy normalUnitAndTileStrategy;

    public ThetaUnitAndTileStrategy() {
        this.normalUnitAndTileStrategy = new NormalUnitAndTileStrategy();
    }

    @Override
    public void setProduction(ExtendedGame game, Position position, String unitType) {
        if (unitType.equals(ThetaCivGameConstants.SANDWORM)) {
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
