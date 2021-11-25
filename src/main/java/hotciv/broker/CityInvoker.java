package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.stub.StubCityBroker;
import hotciv.stub.StubGameBroker;

import javax.servlet.http.HttpServletResponse;

public class CityInvoker implements Invoker {

    private final Gson gson;

    public CityInvoker() {
        this.gson = new Gson();

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

        City cityBroker = lookupCity(objectId);
        try {
            switch (requestObject.getOperationName()){
                case MethodConstants.CITY_GET_OWNER:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(cityBroker.getOwner()));
                    break;
                case MethodConstants.GET_SIZE:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(cityBroker.getSize()));
                    break;
                case MethodConstants.GET_TREASURE:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(cityBroker.getTreasury()));
                    break;
                case MethodConstants.GET_PRODUCTION:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(cityBroker.getProduction()));
                    break;
                case MethodConstants.GET_WORKFORCE_FOCUS:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(cityBroker.getWorkforceFocus()));
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

    private City lookupCity(String objectId) {
        City city = new StubCityBroker();
        return city;
    }

}
