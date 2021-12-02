package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.service.GameNameService;
import hotciv.standard.TileImpl;
import hotciv.stub.StubUnitBroker;

import javax.servlet.http.HttpServletResponse;

public class TileInvoker implements Invoker {

    private final Gson gson;
    private GameNameService gameNameService;

    public TileInvoker(GameNameService service) {
        this.gson = new Gson();
        this.gameNameService = service;
    }

    @Override
    public String handleRequest(String request) {
        // Do the demarshalling
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);
        String objectId = requestObject.getObjectId();
        JsonArray array =
                JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply;

        Tile tileBroker = lookupTile(objectId);
        try {
            switch (requestObject.getOperationName()){
                case MethodConstants.TILE_GET_TYPESTRING:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(tileBroker.getTypeString()));
                    break;
                default:
                    reply = new ReplyObject(HttpServletResponse.SC_NOT_IMPLEMENTED,
                            "The method requestObject.getOperationName() is not implemented");

            }

        } catch( Exception e ) {
            reply =
                    new ReplyObject(
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            e.getMessage());
        }
        return gson.toJson(reply);
    }

    private Tile lookupTile(String objectId) {
        Tile tile = new TileImpl(GameConstants.MOUNTAINS);
        return tile;
    }

}
