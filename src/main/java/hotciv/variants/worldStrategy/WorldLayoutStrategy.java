package hotciv.variants.worldStrategy;

import hotciv.framework.Position;
import hotciv.standard.*;

import java.util.HashMap;


public interface WorldLayoutStrategy<setupWorldLayout> {
    String[] setupTileLayout();
    HashMap<Position, UnitImpl> setupUnitLayout();
    HashMap<Position, CityImpl> setupCityLayout();
}
