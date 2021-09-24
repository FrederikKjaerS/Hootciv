package hotciv.variants.actionStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;

public interface SettlerActionStrategy {
    void performAction(ExtendedGame game, Position p);
}
