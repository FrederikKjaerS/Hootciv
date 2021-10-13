package hotciv.standard.thetaCiv;

import hotciv.factories.GammaCivFactory;
import hotciv.factories.ThetaCivFactory;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestThetaCiv {
    private Game game;


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
        assertThat(game.getTileAt(new Position(2,3)).getTypeString(), is(GameConstants.DESERT));
    }

    @Test
    public void shouldBeDesertAtPosition0_5() {
        assertThat(game.getTileAt(new Position(0,5)).getTypeString(), is(GameConstants.DESERT));
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

}


