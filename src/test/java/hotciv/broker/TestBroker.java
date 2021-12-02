package hotciv.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.service.GameNameService;
import hotciv.service.NameService;
import hotciv.stub.StubCityBroker;
import hotciv.stub.StubGameBroker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBroker {
    private Game game;
    private StubGameBroker servant;
    private GameNameService nameService;

    @BeforeEach
    public void setup(){
        this.servant = new StubGameBroker();
        GameObserver nullObserver = new NullObserver();
        servant.addObserver(nullObserver);

        this.nameService = new GameNameService();
        Invoker invoker = new GameInvoker(nameService, servant);

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
        assertThat(servant.endOfTurnCalled, is(true));
    }

    @Test
    public void shouldCallChangeWorkForceFocus(){
        game.changeWorkForceFocusInCityAt(new Position(0,0),GameConstants.foodFocus);
        assertThat(servant.focusPosition, is(new Position(0,0)));
        assertThat(servant.focusBalance, is(GameConstants.foodFocus));

    }

    @Test
    public void shouldCallChangeProductionInCity(){
        game.changeProductionInCityAt(new Position(0,0),GameConstants.ARCHER);
        assertThat(servant.prodPosition, is(new Position(0,0)));
        assertThat(servant.prodUnitType, is(GameConstants.ARCHER));
    }

    @Test
    public void shouldCallPerformUnitAction(){
        game.performUnitActionAt(new Position(0,0));
        assertThat(servant.performPosition, is(new Position(0,0)));
    }

    @Test
    public void shouldReturnFalseWhenMoveUnit(){
        assertThat(game.moveUnit(new Position(0,0),new Position(0,1)), is(false));
    }


    @Test
    public void shouldBeADifferentUNitAt2_0(){
        Unit unit = game.getUnitAt(new Position(2, 0));
        assertThat(unit, is(notNullValue()));
        assertThat(unit.getID(), is("test"));
        assertThat(game.moveUnit(new Position(0,0),new Position(0,1)), is(false));
    }

    @Test
    public void shouldBeADifferentCityAt1_1(){
        City city = game.getCityAt(new Position(1, 1));
        assertThat(city, is(notNullValue()));
        assertThat(city.getID(), is("test"));
    }

    @Test
    public void shouldBeADifferentTileAt0_1(){
        Tile tile = game.getTileAt(new Position(0, 1));
        assertThat(tile, is(notNullValue()));
        assertThat(tile.getID(), is(not("")));
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


