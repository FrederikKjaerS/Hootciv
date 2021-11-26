package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;

import hotciv.framework.Unit;
import hotciv.stub.StubUnitBroker;

import javax.servlet.http.HttpServletResponse;

public class UnitInvoker implements Invoker {

    private final Gson gson;

    public UnitInvoker() {
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

        Unit unitBroker = lookupUnit(objectId);
        try {
            switch (requestObject.getOperationName()){
                case MethodConstants.UNIT_GET_OWNER:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(unitBroker.getOwner()));
                    break;
                case MethodConstants.GET_TYPE_STRING:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(unitBroker.getTypeString()));
                    break;
                case MethodConstants.GET_MOVE_COUNT:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(unitBroker.getMoveCount()));
                    break;
                case MethodConstants.GET_DEFENSIVE_STRENGTH:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(unitBroker.getDefensiveStrength()));
                    break;
                case MethodConstants.GET_ATTACKING_STRENGTH:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(unitBroker.getAttackingStrength()));
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

    private Unit lookupUnit(String objectId) {
        Unit unit = new StubUnitBroker();
        return unit;
    }

}
