package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import hotciv.view.figure.HotCivFigure;
import minidraw.framework.DrawingEditor;
import minidraw.standard.SelectionTool;

import java.awt.event.MouseEvent;

public class SetFocusTool extends SelectionTool {
    private final DrawingEditor editor;
    private final Game game;
    private HotCivFigure figureBelowClickPoint;

    public SetFocusTool(DrawingEditor editor, Game game) {
        super(editor);
        this.editor = editor;
        this.game = game;

    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);
        figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x, y);
        if (figureBelowClickPoint != null) {
            if (figureBelowClickPoint.getTypeString().equals(GfxConstants.UNIT_TYPE_STRING)
                || figureBelowClickPoint.getTypeString().equals(GfxConstants.CITY_TYPE_STRING)) {
                game.setTileFocus(GfxConstants.getPositionFromXY(x,y));
            }
        }
    }
}
