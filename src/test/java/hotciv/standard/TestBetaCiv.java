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
}
