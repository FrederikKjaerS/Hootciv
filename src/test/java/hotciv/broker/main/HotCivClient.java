/*
 * Copyright (C) 2018-2021. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotciv.broker.main;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.ipc.http.UriTunnelClientRequestHandler;
import frds.broker.ipc.http.UriTunnelServerRequestHandler;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.broker.GameProxy;
import hotciv.broker.HotCivRootInvoker;
import hotciv.factories.SemiCivFactory;
import hotciv.factories.SemiCivGUIFactory;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.stub.StubGameBroker;
import hotciv.view.tool.CompositionTool;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

import java.io.IOException;

public class HotCivClient {
    private String operation;
    private String name;

    public static void main(String[] args) throws IOException {
        new HotCivClient(args);
    }

    public HotCivClient(String[] args) {
        int port = 37123;

        UriTunnelClientRequestHandler crh =
                new UriTunnelClientRequestHandler();
        crh.setServer("localhost", port);
        
        Requestor requestor = new StandardJSONRequestor(crh);
        Game game = new GameProxy(requestor);

        DrawingEditor editor =
                new MiniDrawApplication( "play the game",
                        new SemiCivGUIFactory(game) );
        editor.open();
        editor.showStatus("Play the semiCiv variant - Client");

        editor.setTool(new CompositionTool(editor, game));

        System.out.println("=== HotCiv Socket based client (port:"
                + port + ") ===");
        System.out.println(" Use ctrl-c to terminate!");
    }

}