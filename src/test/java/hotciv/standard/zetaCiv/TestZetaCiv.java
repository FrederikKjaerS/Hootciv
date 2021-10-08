package hotciv.standard.zetaCiv;

import hotciv.factories.ZetaCivFactory;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.AttackerWinsStrategy;
import hotciv.variants.winnerStrategy.Alternating20RoundWinnerStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestZetaCiv {
    private ExtendedGame game;

    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ZetaCivFactory() {
            @Override
            public WorldLayoutStrategy createWorldLayoutStrategy() {
                return new StubLayoutForZeta();
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
    public void shouldBeNoWinnerAfterNoAttacksOrConquers() {
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void shouldBeRedWinnerAfterConqueringAllCitiesBefore20Rounds() {
        game.moveUnit(new Position(2,3), new Position(2,4));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueWinnerInRound23After3Attacks(){
        endRound(20);
        game.endOfTurn();
        game.moveUnit(new Position(4,4), new Position(3,3));
        endRound(1);
        game.moveUnit(new Position(3,3), new Position(2,3));
        endRound(1);
        game.moveUnit(new Position(2,3), new Position(3,2));
        assertThat(game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeNoWinnerInRound22IfRedOwnsAllCities() {
        endRound(20);
        game.moveUnit(new Position(2,3), new Position(2,4));
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void shouldBeNoWinnerInRound3AfterBlueWins3Times() {
        game.endOfTurn();
        game.moveUnit(new Position(4,4), new Position(3,3));
        endRound(1);
        game.moveUnit(new Position(3,3), new Position(2,3));
        endRound(1);
        game.moveUnit(new Position(2,3), new Position(3,2));
        assertThat(game.getWinner(), is(nullValue()));
    }
}

class StubLayoutForZeta implements WorldLayoutStrategy{
    @Override
    public Map<Position,Tile> setupTileLayout() {
        Map<Position, Tile> theWorld = new HashMap<>();
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
        HashMap<Position, CityImpl> cities = new HashMap<>();
        cities.put(new Position(4, 5), new CityImpl(Player.RED));
        cities.put(new Position(2, 4), new CityImpl(Player.BLUE));
        return cities;
        }
    }


