package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.alphaCiv.*;
import hotciv.variants.betaCiv.AlgoAgingStrategy;
import hotciv.variants.betaCiv.ConquerAllWinnerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBetaCiv {
    private Game game;
    private Position redCity = new Position(1,1);
    private Position blueCity = new Position(4,1);
    private Position redArhcer = new Position(2, 0);
    private Position blueLegion = new Position(3, 2);

    /**
     * Fixture for alphaciv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ConquerAllWinnerStrategy(), new AlgoAgingStrategy(), new NoSettlerActionStrategy(),
                new NoArcherActionStrategy(), new AlphaCivLayout());
    }

    private void endRound(int n) {
        for (int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    @Test
    public void shouldBeRedWinnerWhenItConquersBlueCity() {
        game.moveUnit(redArhcer, new Position(3,0));
        endRound(1);
        game.moveUnit(new Position(3,0), blueCity);
        assertThat(game.getWinner(), is(Player.RED));
    }

    /**
    @Test
    public void shouldBeBlueWinnerWhenItConquersRedCity() {
        game.endOfTurn();
        game.moveUnit(blueLegion, new Position(2,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(2,1), redCity);
        assertThat(game.getWinner(), is(Player.BLUE));
    }
    */

    @Test
    public void shouldBeYear3900BCAfterOneRound() {
        endRound(1);
        assertThat(game.getAge(),is(-3900));
    }

    @Test
    public void shouldBeYear3800BCAfterTwoRound() {
        endRound(2);
        assertThat(game.getAge(),is(-3800));
    }

    @Test
    public void shouldBeYear1BCAfterAge100BC() {
        endRound(40);
        assertThat(game.getAge(), is(-1));
    }

    @Test
    public void shouldBeYear1AfterAge1BC() {
        endRound(41);
        assertThat(game.getAge(), is(1));
    }

    @Test
    public void shouldBeYear50AfterAge1() {
        endRound(42);
        assertThat(game.getAge(), is(50));
    }

    @Test
    public void shouldBeYear150AfterAge100() {
        endRound(44);
        assertThat(game.getAge(), is(150));
    }

    @Test
    public void shouldBeAge1775After1750() {
        endRound(77);
        assertThat(game.getAge(), is(1775));
    }

    @Test
    public void shouldBeAge1850After1825() {
        endRound(80);
        assertThat(game.getAge(), is(1850));
    }

    @Test
    public void shouldBeAge1905After1900() {
        endRound(83);
        assertThat(game.getAge(), is(1905));
    }

    @Test
    public void shouldBeAge1965After1960() {
        endRound(95);
        assertThat(game.getAge(), is(1965));
    }

    @Test
    public void shouldBeAge1971After1970() {
        endRound(97);
        assertThat(game.getAge(), is(1971));
    }

}


