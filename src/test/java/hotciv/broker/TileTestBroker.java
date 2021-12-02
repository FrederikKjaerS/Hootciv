package hotciv.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Tile;
import hotciv.service.GameNameService;
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
    private GameNameService gameNameService;


    @BeforeEach
    public void setup() {
        this.servant = new TileImpl(GameConstants.MOUNTAINS);

        this.gameNameService = new GameNameService();
        Invoker invoker = new TileInvoker(gameNameService);
        Tile tile = new TileImpl(GameConstants.MOUNTAINS);
        gameNameService.putTile(tile.getId(), tile);


        ClientRequestHandler chr = new LocalMethodClientRequestHandler(invoker);

        Requestor requester = new StandardJSONRequestor(chr);
        tileProxy = new TileProxy(tile.getId(), requester);
    }

    @Test
    public void shouldBeMountainTile(){
        assertThat(tileProxy.getTypeString(), is(GameConstants.MOUNTAINS));
    }


}
