package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.*;
import hotciv.standard.TileImpl;

public class StubGameBroker implements Game, Servant {

    public Position focusPosition;
    public String focusBalance;
    public boolean endOfTurnCalled = false;
    public Position prodPosition;
    public String prodUnitType;
    public Position performPosition;
    public Player playerInTurn = Player.GREEN;

    Position position_of_mountain_tile = new Position(0,1);
    @Override
    public Tile getTileAt(Position p) {
        if(p.equals(position_of_mountain_tile)){
            return new TileImpl(GameConstants.FOREST);
        }
        return null;
    }

    Position position_of_unit = new Position(2,0);
    @Override
    public Unit getUnitAt(Position p) {
        if(p.equals(position_of_unit)){
            return new StubUnitBroker();
        }
        return null;
    }

    Position position_of_green_city = new Position(1,1);
    @Override
    public City getCityAt(Position p) {
        if(p.equals(position_of_green_city)){
            return new StubCityBroker();
        }
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return playerInTurn;
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
        playerInTurn = Player.YELLOW;
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

}
