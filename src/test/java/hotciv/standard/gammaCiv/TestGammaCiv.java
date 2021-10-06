package hotciv.standard.gammaCiv;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.variants.actionStrategy.GammaActionStrategy;
import hotciv.variants.agingStrategy.AlgoAgingStrategy;
import hotciv.variants.attackStrategy.AttackerWinsStrategy;
import hotciv.variants.winnerStrategy.ConquerAllWinnerStrategy;
import hotciv.variants.worldStrategy.AlphaCivLayoutStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGammaCiv {
    private Game game;
    private Position redArhcer = new Position(2, 0);
    private Position redSettler = new Position(4, 3);

    /**
     * Fixture for alphaciv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ConquerAllWinnerStrategy(), new AlgoAgingStrategy(),
                new GammaActionStrategy(), new AlphaCivLayoutStrategy(), new AttackerWinsStrategy());
    }

    private void endRound(int n) {
        for (int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    @Test
    public void shouldRemoveSettlerIn4_3AfterAction() {
        game.performUnitActionAt(redSettler);
        assertThat(game.getUnitAt(redSettler), is(nullValue()));
    }

    @Test
    public void shouldBuildCityInSettlersPlace4_3AfterAction() {
        game.performUnitActionAt(redSettler);
        assertThat(game.getCityAt(redSettler), is(notNullValue()));
    }

    // Red owns new city after red's settler's (4,3) action
    @Test
    public void shouldBeRedWhoOwnsNewCityIn4_3() {
        game.performUnitActionAt(redSettler);
        Position newCity = new Position(4, 3);
        assertThat(game.getCityAt(newCity).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBePopulationSize1InNewCity() {
        game.performUnitActionAt(redSettler);
        Position newCity = new Position(4, 3);
        assertThat(game.getCityAt(newCity).getSize(), is(1));
    }

    @Test
    public void shouldBeDefense6AfterRedArcherActionAt2_0() {
        game.performUnitActionAt(redArhcer);
        assertThat(game.getUnitAt(redArhcer).getDefensiveStrength(), is(6));
    }
    @Test
    public void shouldHaveMoveCount0AfterRedArcherActionAt2_0() {
        game.performUnitActionAt(redArhcer);
        assertThat(game.getUnitAt(redArhcer).getMoveCount(), is(0));
    }

    @Test
    public void shouldHaveMoveCount0AfterRedArcherActionAt2_0AndOneRound() {
        game.performUnitActionAt(redArhcer);
        endRound(1);
        assertThat(game.getUnitAt(redArhcer).getMoveCount(), is(0));
    }

    @Test
    public void shouldHaveMoveCount1AfterRedArcherAt2_0Action2Times() {
        game.performUnitActionAt(redArhcer);
        endRound(1);
        game.performUnitActionAt(redArhcer);
        assertThat(game.getUnitAt(redArhcer).getMoveCount(), is(1));
    }

    @Test
    public void shouldBeDefense3AfterRedArcherAt2_0Action2Times() {
        game.performUnitActionAt(redArhcer);
        endRound(1);
        game.performUnitActionAt(redArhcer);
        assertThat(game.getUnitAt(redArhcer).getDefensiveStrength(), is(3));
    }



}


