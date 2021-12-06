package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;
import hotciv.service.GameNameService;
import hotciv.stub.SpyGameObserver;

import java.util.ArrayList;
import java.util.List;

public class GameProxy implements Game, ClientProxy {

    public static final String GAME_OBJECTID = "singleton";

    private final Requestor requestor;
    private List<GameObserver> observerList;

    public GameProxy(Requestor requestor) {
        this.requestor = requestor;
        this.observerList = new ArrayList<>();
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
        Boolean valid = requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.MOVE_UNIT, boolean.class, from, to);
        if(valid){
            for(GameObserver g : observerList){
                g.worldChangedAt(from);
                g.worldChangedAt(to);
            }
        }
        return valid;
    }

    @Override
    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.END_OF_TURN, void.class);
        if(!observerList.isEmpty()) {
            for (GameObserver g : observerList) {
                g.turnEnds(getPlayerInTurn(), getAge());
            }
        }
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.CHANGE_WORKFORCE_FOCUS, void.class, p, balance);
        if(!observerList.isEmpty()) {
            for(GameObserver g : observerList){
                g.worldChangedAt(p);
            }
        }
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.CHANGE_PRODUCTION, void.class, p, unitType);
        if(!observerList.isEmpty()) {
            for(GameObserver g : observerList){
                g.worldChangedAt(p);
            }
        }
    }

    @Override
    public void performUnitActionAt(Position p) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MethodConstants.PERFORM_UNIT_ACTION, void.class, p);
        if(!observerList.isEmpty()) {
            for(GameObserver g : observerList){
                g.worldChangedAt(p);
            }
        }
    }

    @Override
    public void addObserver(GameObserver observer) {
        this.observerList.add(observer);
    }

    @Override
    public void setTileFocus(Position position) {
        if (!observerList.isEmpty()) {
            for(GameObserver g : observerList) {
                g.tileFocusChangedAt(position);
            }
        }
    }
}
