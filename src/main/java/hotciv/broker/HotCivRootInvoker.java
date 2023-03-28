package hotciv.broker;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.Game;
import hotciv.service.GameNameService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class HotCivRootInvoker implements Invoker {
    private final Map<String, Invoker> invokerMap;
    private Gson gson;
    private final Game game;

    public HotCivRootInvoker(Game game) {
        this.game = game;
        gson = new Gson();

        GameNameService gameNameService = new GameNameService();
        invokerMap = new HashMap<>();

        // Create an invoker for each handled type/class
        // and put them in a map, binding them to the
        // operationName prefixes
        Invoker gameInvoker = new GameInvoker(gameNameService, game);
        invokerMap.put(MethodConstants.GAME_PREFIX, gameInvoker);

        Invoker cityInvoker = new CityInvoker(gameNameService);
        invokerMap.put(MethodConstants.CITY_PREFIX, cityInvoker);

        Invoker tileInvoker = new TileInvoker(gameNameService);
        invokerMap.put(MethodConstants.TILE_PREFIX, tileInvoker);

        Invoker unitInvoker = new UnitInvoker(gameNameService);
        invokerMap.put(MethodConstants.UNIT_PREFIX, unitInvoker);
    }

    @Override
    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        String operationName = requestObject.getOperationName();

        String reply;

        // Identify the invoker to use
        String type = operationName.substring(0, operationName.indexOf(MethodConstants.SEPARATOR));
        Invoker subInvoker = invokerMap.get(type);

        // And do the upcall on the subInvoker
        try {
            reply = subInvoker.handleRequest(request);

        } catch (UnknownServantException e) {
            reply = gson.toJson(
                    new ReplyObject(
                            HttpServletResponse.SC_NOT_FOUND,
                            e.getMessage()));
        }

        return reply;
    }
}

