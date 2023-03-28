package hotciv.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.service.GameNameService;
import hotciv.stub.StubCityBroker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CityTestBroker {
    private CityProxy cityProxy;
    private GameNameService gameNameService;


    @BeforeEach
    public void setup() {
        this.gameNameService = new GameNameService();
        City city = new StubCityBroker();
        gameNameService.putCity(city.getID(), city);
        Invoker invoker = new CityInvoker(gameNameService);

        ClientRequestHandler chr = new LocalMethodClientRequestHandler(invoker);

        Requestor requester = new StandardJSONRequestor(chr);
        cityProxy = new CityProxy(city.getID(), requester);
    }

    @Test
    public void shouldBeGreenThatOwnsCity(){
        assertThat(cityProxy.getOwner(), is(Player.GREEN));
    }

    @Test
    public void shouldBe3InCitySize(){
        assertThat(cityProxy.getSize(), is(3));
    }

    @Test
    public void shouldBe10InTreasury(){
        assertThat(cityProxy.getTreasury(), is(10));
    }

    @Test
    public void shouldProduceArcherInCity(){
        assertThat(cityProxy.getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldFocusOnFood(){
        assertThat(cityProxy.getWorkforceFocus(), is(GameConstants.foodFocus));
    }


}
