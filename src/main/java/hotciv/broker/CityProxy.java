package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;

public class CityProxy implements City, ClientProxy {

    public final String objectId;

    private final Requestor requestor;

    public CityProxy(String objectId, Requestor requestor) {
        this.objectId = objectId;
        this.requestor = requestor;
    }


    @Override
    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(objectId, MethodConstants.CITY_GET_OWNER, Player.class);
    }

    @Override
    public int getSize() {
        return requestor.sendRequestAndAwaitReply(objectId, MethodConstants.GET_SIZE, int.class);
    }

    @Override
    public int getTreasury() {
        return requestor.sendRequestAndAwaitReply(objectId, MethodConstants.GET_TREASURE, int.class);
    }

    @Override
    public String getProduction() {
        return requestor.sendRequestAndAwaitReply(objectId, MethodConstants.GET_PRODUCTION, String.class);
    }

    @Override
    public String getWorkforceFocus() {
        return requestor.sendRequestAndAwaitReply(objectId, MethodConstants.GET_WORKFORCE_FOCUS, String.class);
    }

    @Override
    public String getID() {
        return objectId;
    }
}
