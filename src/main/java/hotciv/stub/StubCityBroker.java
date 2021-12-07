package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class StubCityBroker implements City, Servant {
    @Override
    public Player getOwner() {
        return Player.GREEN;
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public int getTreasury() {
        return 10;
    }

    @Override
    public String getProduction() {
        return GameConstants.ARCHER;
    }

    @Override
    public String getWorkforceFocus() {
        return GameConstants.foodFocus;
    }

    @Override
    public String getID() {
        return "test";
    }
}
