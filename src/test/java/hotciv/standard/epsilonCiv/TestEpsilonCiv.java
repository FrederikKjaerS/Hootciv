package hotciv.standard.epsilonCiv;

import hotciv.factories.EpsilonCivFactory;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.attackStrategy.AlgoAttackStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.AttackerWinsStrategy;
import hotciv.variants.attackStrategy.dieDecisionStrategy.FixedDieStrategy;
import hotciv.variants.winnerStrategy.ThreeWinStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class TestEpsilonCiv {
    private ExtendedGame game;
    private GameStubForAttackTesting gameStub;
    private ThreeWinStrategy winnerStrategy;

    @BeforeEach
    public void setUp() {
        winnerStrategy = new ThreeWinStrategy();
        gameStub = new GameStubForAttackTesting();
        game = new GameImpl(new EpsilonCivFactory() {
            @Override
            public WorldLayoutStrategy createWorldLayoutStrategy() {
                return new StubLayout();
            }
            @Override
            public AttackStrategy createAttackStrategy() {
                return new AttackerWinsStrategy();
            }
        });
    }

    private void endRound(int n) {
        for (int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    @Test
    public void shouldGet16InAttackWhenDieIs2ForArcherAt3_3(){
        AlgoAttackStrategy as = new AlgoAttackStrategy(new FixedDieStrategy(2));
        assertThat(as.getNewAttackStats(gameStub, new Position(3,3)), is(16));
    }

    @Test
    public void shouldGet18InDefenseWhenDieIs6ForArcherAt4_4(){
        AlgoAttackStrategy as = new AlgoAttackStrategy(new FixedDieStrategy(6));
        assertThat(as.getNewDefenseStats(gameStub, new Position(4,4)), is(18));
    }

    @Test
    public void shouldGet60InDefenseWhenDieIs5ForArcherAt2_3(){
        AlgoAttackStrategy as = new AlgoAttackStrategy(new FixedDieStrategy(5));
        assertThat(as.getNewAttackStats(gameStub, new Position(2,3)), is(60));
    }

    @Test
    public void shouldBeArcherAt4_4WhoWinsInCombatAgainst3_3() {
        AlgoAttackStrategy as = new AlgoAttackStrategy(new FixedDieStrategy(4));
        assertThat(as.attackerWins(gameStub, new Position(3, 3), new Position(4, 4)), is(true));
    }

    @Test
    public void shouldHaveNoWinnerWhenNoPlayerHasWonABattle() {
        assertThat(winnerStrategy.getWinner(gameStub), is(nullValue()));
    }

    @Test
    public void shouldBeRedWinnerWhenRedPlayerHasWon3Battles() {
        winnerStrategy.incrementWin(gameStub, Player.RED);
        winnerStrategy.incrementWin(gameStub, Player.RED);
        winnerStrategy.incrementWin(gameStub, Player.RED);
        assertThat(winnerStrategy.getWinner(gameStub), is(Player.RED));
    }

    @Test
    public void shouldBeBlueWinnerWhenBluePlayerHasWon3Battles() {
        winnerStrategy.incrementWin(gameStub, Player.BLUE);
        winnerStrategy.incrementWin(gameStub, Player.BLUE);
        winnerStrategy.incrementWin(gameStub, Player.BLUE);
        assertThat(winnerStrategy.getWinner(gameStub), is(Player.BLUE));
    }

    @Test
    public void shouldBeNoWinnerAfterBlueArcherAt4_4WinsOverRedArcherAt3_3() {
        game.moveUnit(new Position(4,4), new Position(3,3));
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void shouldBeWinnerAfterBlueArcherWins3Times() {
        game.endOfTurn();
        game.moveUnit(new Position(4,4), new Position(3,3));
        endRound(1);
        game.moveUnit(new Position(3,3), new Position(2,3));
        endRound(1);
        game.moveUnit(new Position(2,3), new Position(3,2));
        assertThat(game.getWinner(), is(Player.BLUE));
    }

}

class StubTile implements Tile {
    private String type;
    public StubTile(String type, int r, int c) { this.type = type; }
    public String getTypeString() { return type; }
}

class StubUnit implements Unit {
    private String type; private Player owner;
    public StubUnit(String type, Player owner) {
        this.type = type; this.owner = owner;
    }
    public String getTypeString() { return type; }
    public Player getOwner() { return owner; }
    public int getMoveCount() { return 0; }
    public int getDefensiveStrength() { return 3; }
    public int getAttackingStrength() { return 2; }
}

class StubLayout implements WorldLayoutStrategy{
    @Override
    public Map<Position,Tile> setupTileLayout() {
        Map<Position, Tile> theWorld = new HashMap<Position,Tile>();
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                theWorld.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
            }
        }
        return theWorld;
    }

    @Override
    public Map<Position, UnitImpl> setupUnitLayout() {
        HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
        units.put(new Position(4, 4), new UnitImpl(GameConstants.ARCHER, Player.BLUE));
        units.put(new Position(3, 2), new UnitImpl(GameConstants.ARCHER, Player.RED));
        units.put(new Position(2, 3), new UnitImpl(GameConstants.ARCHER, Player.RED));
        units.put(new Position(3, 3), new UnitImpl(GameConstants.ARCHER, Player.RED));
        return units;
    }

    @Override
    public Map<Position, CityImpl> setupCityLayout() {
        HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
        return cities;
    }
}


/** A test stub for testing the battle calculation methods in
 * Utility. The terrains are
 * 0,0 - forest;
 * 1,0, 3,3 - hill;
 * 0,1 - plain;
 * 1,1 - city.
 *
 * Red has units on 2,3; 3,2; 3,3; blue one on 4,4
 */
class GameStubForAttackTesting implements Game, ExtendedGame {
    
    public Tile getTileAt(Position p) {
        if ( p.getRow() == 0 && p.getColumn() == 0 ) {
            return new hotciv.standard.epsilonCiv.StubTile(GameConstants.FOREST, 0, 0);
        }
        if ( p.getRow() == 1 && p.getColumn() == 0 || p.getRow() == 3 && p.getColumn() == 3) {
            return new hotciv.standard.epsilonCiv.StubTile(GameConstants.HILLS, 1, 0);
        }
        return new hotciv.standard.epsilonCiv.StubTile(GameConstants.PLAINS, 0, 1);
    }
    public Unit getUnitAt(Position p) {
        if ( p.getRow() == 2 && p.getColumn() == 3 ||
                p.getRow() == 3 && p.getColumn() == 2 ||
                p.getRow() == 3 && p.getColumn() == 3 ) {
            return new hotciv.standard.epsilonCiv.StubUnit(GameConstants.ARCHER, Player.RED);
        }
        if ( p.getRow() == 4 && p.getColumn() == 4 ) {
            return new hotciv.standard.epsilonCiv.StubUnit(GameConstants.ARCHER, Player.BLUE);
        }
        return null;
    }
    public City getCityAt(Position p) {
        if ( p.getRow() == 1 && p.getColumn() == 1 || p.getRow() == 2 && p.getColumn() == 3 ) {
            return new City() {
                public Player getOwner() { return Player.RED; }
                public int getSize() { return 1; }
                public int getTreasury() {
                    return 0;
                }
                public String getProduction() {
                    return null;
                }
                public String getWorkforceFocus() {
                    return null;
                }
            };
        }
        return null;
    }

    // the rest is unused test stub methods...
    public void changeProductionInCityAt(Position p, String unitType) {}
    public void changeWorkForceFocusInCityAt(Position p, String balance) {}
    public void endOfTurn() {}
    public Player getPlayerInTurn() {return null;}
    public Player getWinner() {return null;}
    public int getAge() { return 0; }
    public boolean moveUnit(Position from, Position to) {return false;}
    public void performUnitActionAt( Position p ) {}

    @Override
    public void removeUnit(Position p) {

    }

    @Override
    public void insertCity(Position p, Player owner) {

    }

    @Override
    public Map<Position, CityImpl> getCities() {
        return null;
    }

    @Override
    public int getRounds() {
        return 0;
    }
}


