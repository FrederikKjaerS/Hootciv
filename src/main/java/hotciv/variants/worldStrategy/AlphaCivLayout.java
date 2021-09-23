package hotciv.variants.worldStrategy;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class AlphaCivLayout implements WorldLayoutStrategy {

    @Override
    public String[] setupTileLayout() {
        String[] layout =
                new String[] {
                        "ohoooooooooooooo",
                        ".ooooooooooooooo",
                        "ooMooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                };
        return layout;
    }

    @Override
    public HashMap<Position, UnitImpl> setupUnitLayout() {
        HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
        units.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        units.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED));
        return units;
    }

    @Override
    public HashMap<Position, CityImpl> setupCityLayout() {
        HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
        cities.put(new Position(1, 1), new CityImpl(Player.RED));
        cities.put(new Position(4, 1), new CityImpl(Player.BLUE));
        return cities;
    }
}
