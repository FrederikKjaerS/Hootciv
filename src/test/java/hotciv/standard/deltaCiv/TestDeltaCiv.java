package hotciv.standard.deltaCiv;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.variants.actionStrategy.AlphaActionStrategy;
import hotciv.variants.agingStrategy.HundredYearStrategy;
import hotciv.variants.winnerStrategy.RedWinnerStrategy;
import hotciv.variants.worldStrategy.DeltaCivLayoutStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeltaCiv {
    private Game game;
    private Position redCity = new Position(8, 12);
    private Position blueCity = new Position(4, 5);

    /**
     * Fixture for alphaciv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new RedWinnerStrategy(), new HundredYearStrategy(), new AlphaActionStrategy()
                , new DeltaCivLayoutStrategy());
    }

    private void endRound(int n) {
        for (int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    //there is a ocean tile at position (0,0) --

    @Test
    public void shouldBeOceanAt0_0() {
        assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBeForestAt5_2() {
        assertThat(game.getTileAt(new Position(5,2)).getTypeString(), is(GameConstants.FOREST));
    }

    @Test
    public void shouldBeMountainAt0_5() {
        assertThat(game.getTileAt(new Position(0,5)).getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void shouldBeHillsAt1_3() {
        assertThat(game.getTileAt(new Position(1,3)).getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void shouldBeRedArcherAt3_8() {
        assertThat(game.getUnitAt(new Position(3,8)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(3,8)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBeRedSettlerArchersAt5_5() {
        assertThat(game.getUnitAt(new Position(5,5)).getTypeString(), is(GameConstants.SETTLER));
        assertThat(game.getUnitAt(new Position(5,5)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueLegionAt4_4() {
        assertThat(game.getUnitAt(new Position(4,4)).getTypeString(), is(GameConstants.LEGION));
        assertThat(game.getUnitAt(new Position(4,4)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeRedCityAt8_12() {
        assertThat(game.getCityAt(new Position(8,12)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBeRedCityA4_5() {
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), is(Player.BLUE));
    }
}