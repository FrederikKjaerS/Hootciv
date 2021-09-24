package hotciv.variants.worldStrategy;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;
import java.util.Map;

public class DeltaCivLayoutStrategy implements WorldLayoutStrategy{
    @Override
    public String[] setupTileLayout() {
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        return layout;
    }

    @Override
    public Map<Position, UnitImpl> setupUnitLayout() {
        HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
        units.put(new Position(3, 8), new UnitImpl(GameConstants.ARCHER, Player.RED));
        units.put(new Position(4, 4), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(5, 5), new UnitImpl(GameConstants.SETTLER, Player.RED));
        return units;
    }

    @Override
    public Map<Position, CityImpl> setupCityLayout() {
        HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
        cities.put(new Position(8, 12), new CityImpl(Player.RED));
        cities.put(new Position(4, 5), new CityImpl(Player.BLUE));
        return cities;
    }
}
