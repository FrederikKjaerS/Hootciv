package hotciv.variants.actionStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;

public interface UnitActionStrategy {
    void performAction(ExtendedGame game, Position p);
}
