package hotciv.standard;

import hotciv.framework.Tile;

import java.util.UUID;

public class TileImpl implements Tile {

    private final String typeString;
    private String ID;

    public TileImpl(String terrain){
        this.ID = UUID.randomUUID().toString();
        this.typeString = terrain;
    }

    @Override
    public String getTypeString() {
        return typeString;
    }

    @Override
    public String getId() {
        return this.ID;
    }
}
