package hotciv.standard.gammaCiv;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.variants.alphaCiv.*;
import hotciv.variants.betaCiv.AlgoAgingStrategy;
import hotciv.variants.betaCiv.ConquerAllWinnerStrategy;
import hotciv.variants.gammaCiv.BuildCityActionStrategy;
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
        game = new GameImpl(new ConquerAllWinnerStrategy(), new AlgoAgingStrategy(), new BuildCityActionStrategy(),
                new NoArcherActionStrategy(), new AlphaCivLayout());
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

}


