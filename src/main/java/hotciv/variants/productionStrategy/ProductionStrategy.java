package hotciv.variants.productionStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;

public interface ProductionStrategy {

    void setProduction(ExtendedGame game, Position p, String unitType);

}
