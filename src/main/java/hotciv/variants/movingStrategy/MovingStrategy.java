package hotciv.variants.movingStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Unit;

public interface MovingStrategy {
    boolean canMoveToTile(Unit unit , String tile);
}
