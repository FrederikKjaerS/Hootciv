package hotciv.variants.UnitAndTileStrategy;

import hotciv.framework.Unit;

public interface UnitAndTileStrategy {
    boolean canMoveToTile(Unit unit , String tile);
}
