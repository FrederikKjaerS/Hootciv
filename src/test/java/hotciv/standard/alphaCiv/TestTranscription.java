package hotciv.standard.alphaCiv;

import hotciv.factories.AlphaCivFactory;
import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.variants.gameDecorators.TranscriptGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestTranscription {
    private ByteArrayOutputStream out;
    private Game actualGame;
    private Game game;

    @BeforeEach
    public void setUp() {
        actualGame = new GameImpl(new AlphaCivFactory());
        out = new ByteArrayOutputStream();
        game = new TranscriptGame(actualGame, new PrintStream(out));
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void shouldPrintForRedWhenEndsTurn() {
        game.endOfTurn();
        assertThat(out.toString(), containsString("RED ends turn"));
    }

    @Test
    public void shouldPrintForBlueWhenEndsTurnTwice() {
        game.endOfTurn();
        game.endOfTurn();
        assertThat(out.toString(),
                containsString("BLUE ends turn"));
    }

    @Test
    public void shouldPrintForRedMoveWhenMoving2_0To3_1() {
        game.moveUnit(new Position(2, 0), new Position(3, 1));
        assertThat(out.toString(),
                containsString("RED moves archer from [2,0] to [3,1]"));
    }

    @Test
    public void shouldPrintForBlueMoveWhenMoving3_2To3_3() {
        game.endOfTurn();
        game.moveUnit(new Position(3, 2), new Position(3, 3));
        assertThat(out.toString(),
                containsString("BLUE moves legion from [3,2] to [3,3]"));
    }

    @Test
    public void shouldPrintForChangingWorkFocusToFoodInCityAt1_1() {
        Position redCity = new Position(1,1);
        game.changeWorkForceFocusInCityAt(redCity,GameConstants.foodFocus);
        assertThat(out.toString(),
                containsString("RED changes work force in city at [1,1] to apple"));
    }

    @Test
    public void shouldPrintForChangingWorkFocusToProductionInCityAt1_1() {
        Position redCity = new Position(1,1);
        game.changeWorkForceFocusInCityAt(redCity,GameConstants.productionFocus);
        assertThat(out.toString(),
                containsString("RED changes work force in city at [1,1] to hammer"));
    }

    @Test
    public void shouldPrintForChangingProductionToArcherInCityAt1_1() {
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.ARCHER));
        assertThat(out.toString(),
                containsString("RED changes production in city at [1,1] to archer"));
    }

    @Test
    public void shouldPrintForChangingProductionToSettlerInCityAt1_1() {
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.SETTLER));
        assertThat(out.toString(),
                containsString("RED changes production in city at [1,1] to settler"));
    }

    @Test
    public void shouldPrintForPerformingUnitActionForSettlerAt4_3() {
        Position settler = new Position(4,3);
        game.performUnitActionAt(settler);
        assertThat(out.toString(),
                containsString("RED performs settler's action at [4,3]"));
    }

    @Test
    public void shouldNotPrintForPerformingUnitActionForSettlerAt4_3WhenDecoratorIsOff() {
        this.game = this.actualGame;
        Position settler = new Position(4,3);
        game.performUnitActionAt(settler);
        assertThat(out.toString(),
                containsString(""));
    }
}
