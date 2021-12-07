package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Tile;

public class TileProxy implements Tile, ClientProxy {

    public final String objectID;

    private final Requestor requestor;

    public TileProxy(String objectId, Requestor requestor) {
        this.objectID = objectId;
        this.requestor = requestor;
    }

    @Override
    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(objectID, MethodConstants.TILE_GET_TYPESTRING, String.class);
    }

    @Override
    public String getID() {
        return objectID;
    }
}
