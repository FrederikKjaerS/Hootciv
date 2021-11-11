package hotciv.visual;

import hotciv.factories.SemiCivFactory;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.stub.FakeObjectGame;
import hotciv.view.tool.CompositionTool;
import hotciv.view.tool.UnitMoveTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class ShowSemiCiv {
    public static void main(String[] args) {
        Game game = new GameImpl(new SemiCivFactory());

        DrawingEditor editor =
                new MiniDrawApplication( "play the game",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Play the semiCiv variant");

        // TODO: Replace the setting of the tool with your UnitMoveTool implementation.
        editor.setTool(new CompositionTool(editor, game));
    }
}
