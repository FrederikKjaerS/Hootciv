package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitProxy implements Unit, ClientProxy {

    public final String UNIT_OBJECTID;

    private final Requestor requestor;

    public UnitProxy(String objectId, Requestor requestor) {
        this.UNIT_OBJECTID = objectId;
        this.requestor = requestor;
    }

    @Override
    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, MethodConstants.GET_TYPE_STRING, String.class);
    }

    @Override
    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, MethodConstants.UNIT_GET_OWNER, Player.class);
    }

    @Override
    public int getMoveCount() {
        return requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, MethodConstants.GET_MOVE_COUNT, int.class);
    }

    @Override
    public int getDefensiveStrength() {
        return requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, MethodConstants.GET_DEFENSIVE_STRENGTH, int.class);
    }

    @Override
    public int getAttackingStrength() {
        return requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, MethodConstants.GET_ATTACKING_STRENGTH, int.class);
    }

    @Override
    public String getID() {
        return this.UNIT_OBJECTID;
    }
}
