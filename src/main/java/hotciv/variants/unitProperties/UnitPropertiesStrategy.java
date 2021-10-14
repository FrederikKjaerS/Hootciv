package hotciv.variants.unitProperties;

import hotciv.standard.UnitImpl;

import java.util.Map;

public interface UnitPropertiesStrategy {

    UnitProperties getProperties(String unitType);
}
