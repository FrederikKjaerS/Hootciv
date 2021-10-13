package hotciv.variants.movingStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.GameConstants;
import hotciv.framework.Unit;
import hotciv.standard.TileImpl;

public class SandWormMovingStrategy implements MovingStrategy{
    @Override
    public boolean canMoveToTile(Unit unit, String tile) {
        if(unit.getTypeString().equals(GameConstants.SANDWORM)
                && !tile.equals(GameConstants.DESERT)) {
            return false;
        }
        return true;
    }
}
