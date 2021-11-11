package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import hotciv.view.figure.HotCivFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ActionTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;
    private HotCivFigure figureBelowClickPoint;


    public ActionTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);
        figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x, y);
        if (figureBelowClickPoint != null) {
            if (figureBelowClickPoint.getTypeString().equals(GfxConstants.UNIT_TYPE_STRING)
                    && e.isShiftDown()) {
                game.performUnitActionAt(GfxConstants.getPositionFromXY(x, y));
            }
        }
    }
}

