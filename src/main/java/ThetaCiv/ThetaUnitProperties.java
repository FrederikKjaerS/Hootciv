package ThetaCiv;

import hotciv.framework.GameConstants;
import hotciv.standard.TileImpl;
import hotciv.variants.unitProperties.DefaultUnitProperties;
import hotciv.variants.unitProperties.UnitProperties;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;

import java.util.ArrayList;

public class ThetaUnitProperties implements UnitPropertiesStrategy {

    private UnitPropertiesStrategy defaultUnitProperties;

    public ThetaUnitProperties() {
        this.defaultUnitProperties = new DefaultUnitProperties(standardTiles());
    }

    private static ArrayList<TileImpl> standardTiles() {
        ArrayList<TileImpl> standardTiles = new ArrayList<>();
        standardTiles.add(new TileImpl(GameConstants.PLAINS));
        standardTiles.add(new TileImpl(GameConstants.FOREST));
        standardTiles.add(new TileImpl(GameConstants.HILLS));
        standardTiles.add(new TileImpl(ThetaCivGameConstants.DESERT));
        return standardTiles;
    }

    @Override
    public UnitProperties getProperties(String unitType) {
        if(unitType.equals(ThetaCivGameConstants.SANDWORM)){
            ArrayList<TileImpl> sandWormTiles = new ArrayList<>();
            sandWormTiles.add(new TileImpl(ThetaCivGameConstants.DESERT));
            return new UnitProperties(10,0,2, sandWormTiles);
        }
       return defaultUnitProperties.getProperties(unitType);
    }
}
