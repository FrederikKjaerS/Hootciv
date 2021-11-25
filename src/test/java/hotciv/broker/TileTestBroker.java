package hotciv.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Tile;
import hotciv.standard.TileImpl;
import hotciv.stub.StubCityBroker;
import hotciv.stub.StubUnitBroker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TileTestBroker {
    private TileProxy tileProxy;
    private Tile servant;


    @BeforeEach
    public void setup() {
        this.servant = new TileImpl(GameConstants.MOUNTAINS);

        Invoker invoker = new TileInvoker();

        ClientRequestHandler chr = new LocalMethodClientRequestHandler(invoker);

        Requestor requester = new StandardJSONRequestor(chr);
        tileProxy = new TileProxy(requester);
    }

    @Test
    public void shouldBeMountainTile(){
        assertThat(tileProxy.getTypeString(), is(GameConstants.MOUNTAINS));
    }


}
