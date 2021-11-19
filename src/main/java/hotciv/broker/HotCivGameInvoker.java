package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import frds.broker.Servant;
import hotciv.framework.Game;
import hotciv.stub.StubGame1;

import javax.servlet.http.HttpServletResponse;

public class HotCivGameInvoker implements Invoker {

    private final Game gameServant;
    private final Gson gson;

    public HotCivGameInvoker(Game game) {
        this.gameServant = new StubGame1();
        this.gson = new Gson();
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
