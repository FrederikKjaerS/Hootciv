package hotciv.variants.actionStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;

public interface ArcherActionStrategy {
    void performAction(ExtendedGame game, Position p);
}
