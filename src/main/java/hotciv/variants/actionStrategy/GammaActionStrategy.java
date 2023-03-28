package hotciv.variants.actionStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

public class GammaActionStrategy implements UnitActionStrategy {

    @Override
    public void performAction(ExtendedGame game, Position p) {
        UnitImpl unit = (UnitImpl) game.getUnitAt(p);
        if (unit.getTypeString() == GameConstants.ARCHER) {
            if (!unit.isStationary()) {
                unit.setStationary(true);
                unit.setDefense(6);
                unit.decreaseMoveCount();
            } else {
                unit.setStationary(false);
                unit.resetMoveCount();
                unit.setDefense(3);
            }
        }
        if (unit.getTypeString() == GameConstants.SETTLER) {
            game.insertCity(p, game.getUnitAt(p).getOwner());
            game.removeUnit(p);
        }
    }
}
