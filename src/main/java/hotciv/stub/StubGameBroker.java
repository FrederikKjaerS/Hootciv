package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.*;

public class StubGameBroker implements Game, Servant {

    public Position focusPosition;
    public String focusBalance;
    public boolean endOfTurnCalled = false;
    public Position prodPosition;
    public String prodUnitType;
    public Position performPosition;

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
        this.endOfTurnCalled = true;
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        this.focusPosition = p;
        this.focusBalance = balance;
        System.out.println(p + balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        this.prodPosition = p;
        this.prodUnitType = unitType;
    }

    @Override
    public void performUnitActionAt(Position p) {
        this.performPosition = p;
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
