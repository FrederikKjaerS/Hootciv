package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import hotciv.view.figure.HotCivFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.ImageFigure;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

import java.awt.event.MouseEvent;

public class UnitMoveTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;
    private int fromX;
    private int fromY;
    private int initialX;
    private int initialY;
    private HotCivFigure figureBelowClickPoint;

    public UnitMoveTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;

    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        this.initialX = x;
        this.initialY = y;
        this.fromX = x;
        this.fromY = y;
        figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x, y);
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        if (figureBelowClickPoint != null) {
            if (figureBelowClickPoint.getTypeString().equals(GfxConstants.UNIT_TYPE_STRING)) {
                figureBelowClickPoint.moveBy(x - fromX, y - fromY);
                fromX = x;
                fromY = y;
            }
        }
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        if (figureBelowClickPoint != null) {
            if (figureBelowClickPoint.getTypeString().equals(GfxConstants.UNIT_TYPE_STRING)) {
                if (!game.moveUnit(GfxConstants.getPositionFromXY(initialX, initialY), GfxConstants.getPositionFromXY(x, y))) {
                    figureBelowClickPoint.moveBy(initialX - x, initialY - y);
                }
                game.setTileFocus(GfxConstants.getPositionFromXY(x,y));
            }
            figureBelowClickPoint = null;
        }
    }
}
