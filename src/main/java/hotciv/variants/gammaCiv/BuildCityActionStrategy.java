package hotciv.variants.gammaCiv;

import hotciv.framework.Position;
import hotciv.framework.SettlerActionStrategy;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class BuildCityActionStrategy implements SettlerActionStrategy {

    @Override
    public HashMap<Position, UnitImpl> performAction(Position p, HashMap<Position, UnitImpl> units) {
        HashMap<Position, UnitImpl> newUnits = units;
        newUnits.remove(p);
        return newUnits;
    }
}
