package hotciv.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.stub.StubCityBroker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CityTestBroker {
    private CityProxy cityProxy;
    private StubCityBroker servant;


    @BeforeEach
    public void setup() {
        this.servant = new StubCityBroker();

        Invoker invoker = new CityInvoker();

        ClientRequestHandler chr = new LocalMethodClientRequestHandler(invoker);

        Requestor requester = new StandardJSONRequestor(chr);
        cityProxy = new CityProxy(requester);
    }

    @Test
    public void shouldBeGreenThatOwnsCity(){
        assertThat(servant.getOwner(), is(Player.GREEN));
    }

    @Test
    public void shouldBe3InCitySize(){
        assertThat(servant.getSize(), is(3));
    }

    @Test
    public void shouldBe10InTreasury(){
        assertThat(servant.getTreasury(), is(10));
    }

    @Test
    public void shouldProduceArcherInCity(){
        assertThat(servant.getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldFocusOnFood(){
        assertThat(servant.getWorkforceFocus(), is(GameConstants.foodFocus));
    }


}
