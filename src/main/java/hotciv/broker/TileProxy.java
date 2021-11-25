package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Tile;

public class TileProxy implements Tile, ClientProxy {

    public static final String TILE_OBJECTID = "singleton";

    private final Requestor requestor;

    public TileProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(TILE_OBJECTID, MethodConstants.TILE_GET_TYPESTRING, String.class);
    }
}
