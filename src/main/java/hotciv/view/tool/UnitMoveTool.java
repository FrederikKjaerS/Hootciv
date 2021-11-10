package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.ImageFigure;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

import java.awt.event.MouseEvent;

public class UnitMoveTool extends SelectionTool {
    private final DrawingEditor editor;
    private final Game game;
    private Position from;

    public UnitMoveTool(DrawingEditor editor, Game game) {
        super(editor);
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);
        System.out.println();
        Figure unit = draggedFigure;
        if (unit.displayBox().contains(e.getPoint())) {

        }
    }
}
