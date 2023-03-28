package hotciv.variants.worldStrategy;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;

import java.util.HashMap;
import java.util.Map;

public class AlphaCivLayoutStrategy implements WorldLayoutStrategy {

    @Override
    public Map<Position,Tile> setupTileLayout() {

        Map<Position, Tile> theWorld = new HashMap<Position,Tile>();
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                theWorld.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
            }
        }
        //Setup Oceans
        theWorld.put(new Position(1, 0), new TileImpl(GameConstants.OCEANS));
        //Setup Hills
        theWorld.put(new Position(0, 1), new TileImpl(GameConstants.HILLS));
        //Setup Mountains
        theWorld.put(new Position(2, 2), new TileImpl(GameConstants.MOUNTAINS));

        return theWorld;
    }


    @Override
    public Map<Position, UnitImpl> setupUnitLayout(UnitPropertiesStrategy strategy) {
        HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
        units.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED,
                strategy.getProperties(GameConstants.ARCHER)));
        units.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE,
                strategy.getProperties(GameConstants.LEGION)));
        units.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED,
                strategy.getProperties(GameConstants.SETTLER)));
        return units;
    }

    @Override
    public Map<Position, CityImpl> setupCityLayout() {
        HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
        cities.put(new Position(1, 1), new CityImpl(Player.RED));
        cities.put(new Position(4, 1), new CityImpl(Player.BLUE));
        return cities;
    }
}
