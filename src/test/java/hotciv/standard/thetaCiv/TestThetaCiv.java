package hotciv.standard.thetaCiv;

import ThetaCiv.ThetaCivGameConstants;
import ThetaCiv.ThetaCivFactory;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestThetaCiv {
    private GameImpl game;
    private Position sandworm = new Position(9,6);
    private Position redCity = new Position(8,12);
    private Position blueCity = new Position(4,5);

    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ThetaCivFactory());
    }

    private void endRound(int n) {
        for (int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    @Test
    public void shouldBeDesertAtPosition2_3() {
        assertThat(game.getTileAt(new Position(2,3)).getTypeString(), is(ThetaCivGameConstants.DESERT));
    }

    @Test
    public void shouldBeDesertAtPosition0_5() {
        assertThat(game.getTileAt(new Position(0,5)).getTypeString(), is(ThetaCivGameConstants.DESERT));
    }

    @Test
    public void shouldBeOceantAtPosition1_1() {
        assertThat(game.getTileAt(new Position(1,1)).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBePlainAtPosition0_3() {
        assertThat(game.getTileAt(new Position(0,3)).getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void shouldBeHillAtPosition1_3() {
        assertThat(game.getTileAt(new Position(1,3)).getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void shouldBeArcherAt3_8() {
        assertThat(game.getUnitAt(new Position(3,8)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldBeSandwormAt9_6() {
        assertThat(game.getUnitAt(new Position(9,6)).getTypeString(), is(ThetaCivGameConstants.SANDWORM));
    }

    @Test
    public void shouldBeBlueCityAt4_5() {
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeRedCityAt8_12() {
        assertThat(game.getCityAt(new Position(8,12)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldMoveSandWormToOtherDessertTile() {
        game.endOfTurn();
        assertThat(game.moveUnit(sandworm, new Position(9,5)), is(true));
    }

    @Test
    public void shouldNotMoveSandWormToPlainTile() {
        game.endOfTurn();
        assertThat(game.moveUnit(sandworm, new Position(9,7)), is(false));
    }

    @Test
    public void shouldNotProduceSandwormInFirstRoundForRedCityAt8_12() {
        game.changeProductionInCityAt(redCity, ThetaCivGameConstants.SANDWORM);
        assertThat(game.getUnitAt(redCity), is(nullValue()));
    }

    @Test
    public void shouldProduceSandwormIn5RoundForRedCityAt8_12() {
        game.changeProductionInCityAt(redCity, ThetaCivGameConstants.SANDWORM);
        endRound(1);
        endRound(1);
        endRound(1);
        endRound(1);
        endRound(1);
        assertThat(game.getUnitAt(redCity).getTypeString(), is(ThetaCivGameConstants.SANDWORM));
    }

    @Test
    public void shouldProduceSandwormIn5RoundForBlueCityAt4_5() {
        game.endOfTurn();
        game.changeProductionInCityAt(blueCity, ThetaCivGameConstants.SANDWORM);
        endRound(5);
        assertThat(game.getUnitAt(new Position(3,5)).getTypeString(), is(ThetaCivGameConstants.SANDWORM));
    }

    @Test
    public void shouldNotProduceSandwormIn5RoundForRedCityAt5_1() {
        game.moveUnit(new Position(5,5),new Position(5,4));
        endRound(1);
        game.moveUnit(new Position(5,4),new Position(5,3));
        endRound(1);
        game.moveUnit(new Position(5,3),new Position(5,2));
        endRound(1);
        game.moveUnit(new Position(5,2),new Position(5,1));
        game.performUnitActionAt(new Position(5,1));
        game.changeProductionInCityAt(new Position(5,1), ThetaCivGameConstants.SANDWORM);
        endRound(5);
        assertThat(game.getUnitAt(new Position(5,1)), is(nullValue()));
    }

    @Test
    public void shouldNotProduceArcherIn2RoundForRedCityAt5_1() {
        game.moveUnit(new Position(5,5),new Position(5,4));
        endRound(1);
        game.moveUnit(new Position(5,4),new Position(5,3));
        endRound(1);
        game.moveUnit(new Position(5,3),new Position(5,2));
        endRound(1);
        game.moveUnit(new Position(5,2),new Position(5,1));
        game.performUnitActionAt(new Position(5,1));
        game.changeProductionInCityAt(new Position(5,1), GameConstants.ARCHER);
        endRound(2);
        assertThat(game.getUnitAt(new Position(5,1)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldMoveSandworm2TimesInSameRound() {
        game.endOfTurn();
        game.moveUnit(sandworm, new Position(8,6));
        assertThat(game.moveUnit(new Position(8,6), new Position(7,6)), is(true));
        assertThat(game.getUnitAt(new Position(7,6)).getTypeString(), is(ThetaCivGameConstants.SANDWORM));

    }





}


