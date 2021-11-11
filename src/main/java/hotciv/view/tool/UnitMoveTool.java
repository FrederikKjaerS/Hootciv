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

public class UnitMoveTool extends SelectionTool {
    private final DrawingEditor editor;
    private final Game game;
    private int fromX;
    private int fromY;
    private HotCivFigure figureBelowClickPoint;

    public UnitMoveTool(DrawingEditor editor, Game game) {
        super(editor);
        this.editor = editor;
        this.game = game;

    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);
        this.fromX = x;
        this.fromY = y;
        figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x, y);
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        if(figureBelowClickPoint != null) {
            if (figureBelowClickPoint.getTypeString().equals(GfxConstants.UNIT_TYPE_STRING)) {
                super.mouseDrag(e, x, y);
            }
        }
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        super.mouseUp(e, x, y);
        if (figureBelowClickPoint != null) {
            if(!game.moveUnit(GfxConstants.getPositionFromXY(fromX, fromY), GfxConstants.getPositionFromXY(x,y))){
                figureBelowClickPoint.moveBy(fromX - x, fromY - y);
            }
        }
    }
}
