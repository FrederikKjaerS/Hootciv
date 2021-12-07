package hotciv.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.service.GameNameService;
import hotciv.service.NameService;
import hotciv.stub.StubCityBroker;
import hotciv.stub.StubUnitBroker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UnitTestBroker {
    private Unit unitProxy;
    private GameNameService nameService;


    @BeforeEach
    public void setup() {
        this.nameService = new GameNameService();
        Invoker invoker = new UnitInvoker(nameService);
        Unit unit = new StubUnitBroker();
        nameService.putUnit(unit.getID(), unit);

        ClientRequestHandler chr = new LocalMethodClientRequestHandler(invoker);

        Requestor requester = new StandardJSONRequestor(chr);
        unitProxy = new UnitProxy(unit.getID(), requester);
    }

    @Test
    public void shouldBeGreenThatOwnsCity(){
        Player owner = unitProxy.getOwner();
        assertThat(owner, is(Player.GREEN));
    }

    @Test
    public void shouldBe3InCitySize(){
        assertThat(unitProxy.getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldBe10InTreasury(){
        assertThat(unitProxy.getMoveCount(), is(10));
    }

    @Test
    public void shouldProduceArcherInCity(){
        assertThat(unitProxy.getDefensiveStrength(), is(5));
    }

    @Test
    public void shouldFocusOnFood(){
        assertThat(unitProxy.getAttackingStrength(), is(8));
    }


}
