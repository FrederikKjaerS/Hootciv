package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;

public class CityProxy implements City, ClientProxy {

    public static final String GAME_OBJECTID = "singleton";

    private final Requestor requestor;

    public CityProxy(Requestor requestor) {
        this.requestor = requestor;
    }


    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getTreasury() {
        return 0;
    }

    @Override
    public String getProduction() {
        return null;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
