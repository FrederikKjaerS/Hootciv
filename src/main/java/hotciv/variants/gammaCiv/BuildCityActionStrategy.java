package hotciv.variants.gammaCiv;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.SettlerActionStrategy;

import java.util.HashMap;

public class BuildCityActionStrategy implements SettlerActionStrategy {

    @Override
    public void performAction(ExtendedGame game, Position p) {
        game.insertCity(p, game.getUnitAt(p).getOwner());
        game.removeUnit(p);
    }
}
