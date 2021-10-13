package hotciv.variants.UnitAndTileStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public interface UnitAndTileStrategy {
    boolean canMoveToTile(Unit unit , String tile);

    void setProduction(ExtendedGame game, Position p, String unitType);

}
