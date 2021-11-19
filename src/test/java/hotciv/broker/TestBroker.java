package hotciv.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.stub.StubGame1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBroker {
    private Game game;

    @BeforeEach
    public void setup(){
        Game servant = new StubGame1();
        GameObserver nullObserver = new NullObserver();
        servant.addObserver(nullObserver);

        Invoker invoker = new HotCivGameInvoker(servant);

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


