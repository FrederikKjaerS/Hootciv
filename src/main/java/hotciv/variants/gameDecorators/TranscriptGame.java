package hotciv.variants.gameDecorators;

import hotciv.framework.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TranscriptGame implements Game {

    private Game game;
    private PrintStream ps;
    public TranscriptGame(Game game, PrintStream ps) {
        this.ps = ps;
        this.game = game;
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        Player player = game.getPlayerInTurn();
        String unit = game.getUnitAt(from).getTypeString();
        ps.println(player + " moves " + unit + " from " + from + " to " + to);
        return game.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        Player player = game.getPlayerInTurn();
        ps.println(player + " ends turn ");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        Player player = game.getPlayerInTurn();
        ps.println(player + " changes work force in city at " + p + " to " + balance);
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        Player player = game.getPlayerInTurn();
        ps.println(player + " changes production in city at " + p + " to " + unitType);
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        Player player = game.getPlayerInTurn();
        String unit = game.getUnitAt(p).getTypeString();
        ps.println(player + " performs " + unit + "'s action at " + p);
        game.performUnitActionAt(p);
    }
}
