package hotciv.framework;

import hotciv.standard.UnitImpl;

import java.util.HashMap;

public interface SettlerActionStrategy {
    HashMap<Position, UnitImpl> performAction(Position p, HashMap<Position, UnitImpl> units);
}
