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

    @Test
    public void shouldCallWorldChangedAtOnceWhenChangedProductionInCityIsCalled() {
        game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
        assertThat(spy.positions.contains(new Position(1, 1)), is(true));
        assertThat(spy.positions.size(), is(1));
    }

    @Test
    public void shouldCallWorldChangedAtOnceWhenChangedWorkForceFocusInCityAtIsInvoked() {
        game.changeWorkForceFocusInCityAt(new Position(4,1), GameConstants.productionFocus);
        assertThat(spy.positions.contains(new Position(4,1)), is(true));
        assertThat(spy.positions.size(), is(1));

    }

    @Test
    public void shouldCallTileFocusChangedAtWhenSetTileFocusIsInvoked() {
        game.setTileFocus(new Position(1,1));
        assertThat(spy.focusedTile, is(new Position(1,1)));
    }

    @Test
    public void shouldCallTileFocusChangedAtWhenPerformUnitActionAtIsInvoked() {
        game.performUnitActionAt(new Position(4,3));
        assertThat(spy.positions.contains(new Position(4,3)), is(true));
        assertThat(spy.positions.size(), is(1));
    }


}
