package hotciv.stub;

import frds.broker.Servant;
import hotciv.broker.MethodConstants;
import hotciv.framework.*;
import hotciv.standard.CityImpl;

import java.util.List;

public class StubGameBroker implements Game, Servant {

    private String lastMethodCalled;


    public String getLastMethodCalled() {
        return this.lastMethodCalled;
    }

    @Override
    public Tile getTileAt(Position p) {
        return null;
    }

    @Override
    public Unit getUnitAt(Position p) {
        return null;
    }

    Position position_of_green_city = new Position(1,1);
    @Override
    public City getCityAt(Position p) {
        if(p.equals(position_of_green_city)){
            return new StubCity(Player.GREEN, 4);
        }
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return Player.GREEN;
    }

    @Override
    public Player getWinner() {
        return Player.YELLOW;
    }

    @Override
    public int getAge() {
        return 42;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return false;
    }

    @Override
    public void endOfTurn() {
        lastMethodCalled = MethodConstants.END_OF_TURN;
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        this.lastMethodCalled = MethodConstants.CHANGE_WORKFORCE_FOCUS;
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        lastMethodCalled = MethodConstants.CHANGE_PRODUCTION;
    }

    @Override
    public void performUnitActionAt(Position p) {
        lastMethodCalled = MethodConstants.PERFORM_UNIT_ACTION;
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }

    private class StubCity implements City {

        private final Player owner;
        private int size;

        public StubCity(Player player, int i) {
            this.size = i;
            this.owner = player;
        }

        @Override
        public Player getOwner() {
            return this.owner;
        }

        @Override
        public int getSize() {
            return this.size;
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
}
