package hotciv.standard.thetaCiv;

import hotciv.factories.GammaCivFactory;
import hotciv.framework.Game;
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
        game = new GameImpl(new GammaCivFactory());
    }

    private void endRound(int n) {
        for (int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }


}


