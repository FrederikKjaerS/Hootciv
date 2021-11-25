package hotciv.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.stub.StubGameBroker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBroker {
    private Game game;
    private StubGameBroker servant;

    @BeforeEach
    public void setup(){
        this.servant = new StubGameBroker();
        GameObserver nullObserver = new NullObserver();
        servant.addObserver(nullObserver);

        Invoker invoker = new GameInvoker(servant);

        ClientRequestHandler chr = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(chr);

        game = new GameProxy(requestor);
        game.addObserver(nullObserver);
    }

    @Test
    public void shouldHaveWinner(){
        Player winner = game.getWinner();
        assertThat(winner, is(Player.YELLOW));
    }

    @Test
    public void shouldBeInYear42(){
        int age = game.getAge();
        assertThat(age, is(42));
    }

    @Test
    public void shouldBeGreenPlayerInTurn(){
        Player player = game.getPlayerInTurn();
        assertThat(player, is(Player.GREEN));
    }

    @Test
    public void shouldCallEndOfTurn(){
        game.endOfTurn();
    }

    @Test
    public void shouldCallChangeWorkForceFocus(){
        game.changeWorkForceFocusInCityAt(new Position(0,0),GameConstants.foodFocus);
        //assertThat(servant.getLastMethodCalled(), is(MethodConstants.CHANGE_WORKFORCE_FOCUS));
    }

    @Test
    public void shouldCallChangeProductionInCity(){
        game.changeProductionInCityAt(new Position(0,0),GameConstants.ARCHER);
        //assertThat(servant.getLastMethodCalled(), is(MethodConstants.CHANGE_WORKFORCE_FOCUS));
    }

    @Test
    public void shouldCallPerformUnitAction(){
        game.performUnitActionAt(new Position(0,0));
        //assertThat(servant.getLastMethodCalled(), is(MethodConstants.CHANGE_WORKFORCE_FOCUS));
    }

    @Test
    public void shouldReturnFalseWhenMoveUnit(){
        assertThat(game.moveUnit(new Position(0,0),new Position(0,1)), is(false));
    }




    private class NullObserver implements GameObserver {
        @Override
        public void worldChangedAt(Position pos) {
        }

        @Override
        public void turnEnds(Player nextPlayer, int age) {
        }

        @Override
        public void tileFocusChangedAt(Position position) {
        }
    }
}


