package hotciv.variants.actionStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public interface SettlerActionStrategy {
    void performAction(ExtendedGame game, Position p);
}
