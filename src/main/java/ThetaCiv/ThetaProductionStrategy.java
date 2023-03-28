package ThetaCiv;

import hotciv.framework.*;
import hotciv.utility.NeighborTiles;
import hotciv.variants.productionStrategy.NormalProductionStrategy;
import hotciv.variants.productionStrategy.ProductionStrategy;

public class ThetaProductionStrategy implements ProductionStrategy {
    private ProductionStrategy normalUnitAndTileStrategy;

    public ThetaProductionStrategy() {
        this.normalUnitAndTileStrategy = new NormalProductionStrategy();
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
