package hotciv.standard.main;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.broker.GameInvoker;
import hotciv.broker.GameProxy;
import hotciv.framework.Game;

public class HotCivManualClientTest {


    public static void main(String[] args) throws Exception{
        new HotCivManualClientTest(args[0]);
    }

    public HotCivManualClientTest(String hostname) {
        System.out.println("=== HotCiv MANUAL TEST Client (Socket) (host:" + hostname + ") ===");

        ClientRequestHandler crh = new SocketClientRequestHandler();
        crh.setServer(hostname, 37123);
        Requestor requestor = new StandardJSONRequestor(crh);
        callSimpleMethods(new GameProxy(requestor));
    }

    private void callSimpleMethods(Game game) {
        System.out.println("=== Testing simple methods ===");
        System.out.println("-> GAME age   " + game.getAge());
    }

}
