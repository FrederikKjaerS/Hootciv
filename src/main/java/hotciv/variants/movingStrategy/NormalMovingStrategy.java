package hotciv.variants.movingStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Unit;

public class NormalMovingStrategy implements MovingStrategy{
    @Override
    public boolean canMoveToTile(Unit unit, String tile) {
        return true;
    }
}
