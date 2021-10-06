package hotciv.standard.epsilonCiv;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.variants.actionStrategy.AlphaActionStrategy;
import hotciv.variants.actionStrategy.GammaActionStrategy;
import hotciv.variants.agingStrategy.AlgoAgingStrategy;
import hotciv.variants.agingStrategy.HundredYearStrategy;
import hotciv.variants.attackStrategy.AlgoAttackingStrategy;
import hotciv.variants.attackStrategy.AttackerWinsStrategy;
import hotciv.variants.attackStrategy.dieDecisionStrategy.FixedDieStrategy;
import hotciv.variants.winnerStrategy.ConquerAllWinnerStrategy;
import hotciv.variants.winnerStrategy.RedWinnerStrategy;
import hotciv.variants.worldStrategy.AlphaCivLayoutStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEpsilonCiv {
    private Game game;
    private Position redArhcer = new Position(2, 0);
    private Position redSettler = new Position(4, 3);

    /**
     * Fixture for alphaciv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new RedWinnerStrategy(), new HundredYearStrategy(),
                new AlphaActionStrategy(), new AlphaCivLayoutStrategy(), new AlgoAttackingStrategy(new FixedDieStrategy()));
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
}


