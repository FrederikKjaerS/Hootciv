package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class StubUnitBroker implements Unit, Servant {
    @Override
    public String getTypeString() {
        return GameConstants.ARCHER;
    }

    @Override
    public Player getOwner() {
        return Player.GREEN;
    }

    @Override
    public int getMoveCount() {
        return 10;
    }

    @Override
    public int getDefensiveStrength() {
        return 5;
    }

    @Override
    public int getAttackingStrength() {
        return 8;
    }

    @Override
    public String getID() {
        return "test";
    }
}
