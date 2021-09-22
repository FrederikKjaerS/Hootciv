package hotciv.framework;

import hotciv.standard.*;

import java.util.HashMap;


public interface WorldLayoutStrategy<setupWorldLayout> {
    String[] setupTileLayout();
    HashMap<Position, UnitImpl> setupUnitLayout();
    HashMap<Position, CityImpl> setupCityLayout();
}
