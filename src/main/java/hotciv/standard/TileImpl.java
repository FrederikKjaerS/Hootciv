package hotciv.standard;

import hotciv.framework.Tile;

public class TileImpl implements Tile {

    private final String typeString;

    public TileImpl(String terrain){
        this.typeString = terrain;
    }

    @Override
    public String getTypeString() {
        return typeString;
    }
}
