package hotciv.broker.main;

import frds.broker.Invoker;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.broker.GameInvoker;
import hotciv.framework.Game;
import hotciv.stub.StubGameBroker;

public class HotCivServer {

    public static void main(String[] args) throws Exception {
        new HotCivServer(args[0]); // No error handling!
    }


    public HotCivServer(String type) throws Exception {
        int port = 37123;

        Game gameServant = new StubGameBroker();
        Invoker invoker = new GameInvoker(gameServant);

        // Configure a socket based server request handler
        SocketServerRequestHandler ssrh =
                new SocketServerRequestHandler();
        ssrh.setPortAndInvoker(port, invoker);

        System.out.println("=== HotCiv Socket based Server Request Handler (port:"
                + port + ") ===");
        System.out.println(" Use ctrl-c to terminate!");
        ssrh.start();
    }
}

