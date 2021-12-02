package hotciv.factories;

import hotciv.framework.Game;
import hotciv.view.CivDrawing;
import hotciv.view.MapView;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;

import javax.swing.*;

public class SemiCivGUIFactory implements Factory {
    private Game game;
    public SemiCivGUIFactory(Game g) { game = g; }

    public DrawingView createDrawingView(DrawingEditor editor) {
        DrawingView view =
                new MapView(editor, game);
        return view;
    }

    public Drawing createDrawing(DrawingEditor editor) {
        return new CivDrawing(editor, game);
    }

    public JTextField createStatusField(DrawingEditor editor) {
        JTextField f = new JTextField("SWEA template code");
        f.setEditable(false);
        return f;
    }
}
