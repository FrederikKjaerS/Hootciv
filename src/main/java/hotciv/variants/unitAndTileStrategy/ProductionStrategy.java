package hotciv.variants.unitAndTileStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public interface ProductionStrategy {

    void setProduction(ExtendedGame game, Position p, String unitType);

}
