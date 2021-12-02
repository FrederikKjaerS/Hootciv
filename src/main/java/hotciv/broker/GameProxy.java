package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;
import hotciv.service.GameNameService;

public class GameProxy implements Game, ClientProxy {

    public static final String GAME_OBJECTID = "singleton";

    private final Requestor requestor;

    public GameProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public Tile getTileAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.GET_TILE_AT, String.class, p);
        if(id.isEmpty()){
            return null;
        }
        return new TileProxy(id, requestor);
    }

    @Override
    public Unit getUnitAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.GET_UNIT_AT, String.class, p);
        if(id.isEmpty()){
            return null;
        }
        return new UnitProxy(id, requestor);
    }

    @Override
    public City getCityAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.GET_CITY_AT, String.class, p);
        if(id.isEmpty()){
            return null;
        }
        return new CityProxy(id, requestor);
    }

    @Override
    public Player getPlayerInTurn() {
        return requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.GET_PLAYERINTURN, Player.class);
    }

    @Override
    public Player getWinner() {
        return requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.GET_WINNER, Player.class);
    }

    @Override
    public int getAge() {
        return requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.GET_AGE, int.class);
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.MOVE_UNIT, boolean.class, from, to);
    }

    @Override
    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.END_OF_TURN, void.class);
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.CHANGE_WORKFORCE_FOCUS, void.class, p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.CHANGE_PRODUCTION, void.class, p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.PERFORM_UNIT_ACTION, void.class, p);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
