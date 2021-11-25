package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.City;
import hotciv.framework.Player;

public class StubCityBroker implements City, Servant {
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
