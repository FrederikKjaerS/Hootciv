package hotciv.variants.unitProperties;

import hotciv.framework.GameConstants;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DefaultUnitProperties implements UnitPropertiesStrategy {

    private Map<String, UnitProperties> unitProperties;

    public DefaultUnitProperties() {
        this(standardTiles());
    }


    public DefaultUnitProperties(ArrayList<TileImpl> standardTiles) {
        unitProperties = new HashMap<>();
        this.unitProperties.put(GameConstants.ARCHER,
                new UnitProperties(3,2,1, standardTiles));
        this.unitProperties.put(GameConstants.SETTLER,
                new UnitProperties(3,0,1, standardTiles));
        this.unitProperties.put(GameConstants.LEGION,
                new UnitProperties(2,4,1 , standardTiles));
    }



    private static ArrayList<TileImpl> standardTiles() {
        ArrayList<TileImpl> standardTiles = new ArrayList<>();
        standardTiles.add(new TileImpl(GameConstants.PLAINS));
        standardTiles.add(new TileImpl(GameConstants.FOREST));
        standardTiles.add(new TileImpl(GameConstants.HILLS));
        return standardTiles;
    }


    @Override
    public UnitProperties getProperties(String unitType) {
        return unitProperties.get(unitType);
    }

}
