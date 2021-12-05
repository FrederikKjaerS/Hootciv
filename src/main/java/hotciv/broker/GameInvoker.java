package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.*;
import hotciv.service.NameService;

import javax.servlet.http.HttpServletResponse;

public class GameInvoker implements Invoker {

    private final Game gameServant;
    private final Gson gson;
    private final NameService nameService;

    public GameInvoker(NameService service, Game game) {
        this.gameServant = game;
        this.gson = new Gson();
        this.nameService = service;
    }

    @Override
    public String handleRequest(String request) {
        // Do the demarshalling
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);
        JsonArray array =
                JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply;

        try {
            switch (requestObject.getOperationName()){
                case MethodConstants.GET_WINNER:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(gameServant.getWinner()));
                    break;
                case MethodConstants.GET_AGE:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(gameServant.getAge()));
                    break;
                case MethodConstants.GET_PLAYERINTURN:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(gameServant.getPlayerInTurn()));
                    break;
                case MethodConstants.END_OF_TURN:
                    gameServant.endOfTurn();
                    reply = new ReplyObject(HttpServletResponse.SC_OK,null);
                    break;
                case MethodConstants.CHANGE_WORKFORCE_FOCUS:
                    Position p = gson.fromJson(array.get(0), Position.class);
                    String balance = gson.fromJson(array.get(1), String.class);
                    gameServant.changeWorkForceFocusInCityAt(p,balance);
                    reply = new ReplyObject(HttpServletResponse.SC_OK, null);
                    break;
                case MethodConstants.CHANGE_PRODUCTION:
                    Position pos = gson.fromJson(array.get(0), Position.class);
                    String unitType = gson.fromJson(array.get(1), String.class);
                    gameServant.changeProductionInCityAt(pos,unitType);
                    reply = new ReplyObject(HttpServletResponse.SC_OK, null);
                    break;
                case MethodConstants.PERFORM_UNIT_ACTION:
                    Position position = gson.fromJson(array.get(0), Position.class);
                    gameServant.performUnitActionAt(position);
                    reply = new ReplyObject(HttpServletResponse.SC_OK, null);
                    break;
                case MethodConstants.MOVE_UNIT:
                    Position pFrom = gson.fromJson(array.get(0), Position.class);
                    Position pTo = gson.fromJson(array.get(0), Position.class);
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(gameServant.moveUnit(pFrom,pTo)));
                    break;
                case MethodConstants.GET_CITY_AT:
                    Position cityPosition = gson.fromJson(array.get(0), Position.class);
                    City city = gameServant.getCityAt(cityPosition);
                    if(city != null){
                        nameService.putCity(city.getID(), city);
                        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(city.getID()));
                    }else {
                        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(""));
                    }
                    break;
                case MethodConstants.GET_UNIT_AT:
                    Position unitPosition = gson.fromJson(array.get(0), Position.class);
                    Unit unit = gameServant.getUnitAt(unitPosition);
                    if(unit != null) {
                        nameService.putUnit(unit.getID(), unit);
                        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(unit.getID()));
                    }else {
                        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(""));
                    }
                    break;
                case MethodConstants.GET_TILE_AT:
                    Position tilePosition = gson.fromJson(array.get(0), Position.class);
                    Tile tile = gameServant.getTileAt(tilePosition);
                    if(tile != null){
                        nameService.putTile(tile.getID(), tile);
                        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(tile.getID()));
                    }else {
                        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(""));
                    }
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

        // And marshall the reply
        //return gson.toJson(reply);
        return gson.toJson(reply);
    }

}
