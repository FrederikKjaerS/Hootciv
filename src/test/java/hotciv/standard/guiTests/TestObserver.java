package hotciv.standard.guiTests;

import hotciv.factories.AlphaCivFactory;
import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.stub.SpyGameObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestObserver {
    private Game game;
    private SpyGameObserver spy;

    /**
     * Fixture for alphaciv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlphaCivFactory());
        this.spy = new SpyGameObserver();
        game.addObserver(spy);

    }

    private void endRound(int n) {
        for (int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void shouldCallWorldChangedAtTwiceWhenMovingUnit() {
        game.moveUnit(new Position(2,0), new Position(2,1));
        assertThat(spy.positions.contains(new Position(2,0)), is(true));
        assertThat(spy.positions.contains(new Position(2,1)), is(true));
        assertThat(spy.positions.size(), is(2));
    }

    @Test
    public void shouldCallTurnEndsOnceWhenEndOfTurnIsCalled() {
        game.endOfTurn();
        assertThat(spy.nextPlayerInTurn, is(Player.BLUE));
        assertThat(spy.age, is(-4000));
    }

    @Test
    public void shouldCallTurnEndsTwiceWhenEndOfTurnIsCalledTwice() {
        game.endOfTurn();
        game.endOfTurn();
        assertThat(spy.nextPlayerInTurn, is(Player.RED));
        assertThat(spy.age, is(-3900));
    }



}
