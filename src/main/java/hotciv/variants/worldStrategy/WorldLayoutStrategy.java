package hotciv.variants.worldStrategy;

import hotciv.framework.Position;
import hotciv.standard.*;

import java.util.Map;


public interface WorldLayoutStrategy<setupWorldLayout> {
    /** We use a 'data driven' approach - the layout is a simple semi-visual representation, and is being
     converted to a String array representation. */
    String[] setupTileLayout();
    Map<Position, UnitImpl> setupUnitLayout();
    Map<Position, CityImpl> setupCityLayout();
}
