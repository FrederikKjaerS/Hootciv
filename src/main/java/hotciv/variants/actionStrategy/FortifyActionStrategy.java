package hotciv.variants.actionStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.standard.UnitImpl;

public class FortifyActionStrategy implements ArcherActionStrategy{

    @Override
    public void performAction(ExtendedGame game, Position p) {
        UnitImpl unit = (UnitImpl) game.getUnitAt(p);
        if(!unit.isStationary()){
            unit.setStationary(true);
            unit.setDefense(6);
            unit.decreaseMoveCount();
        } else {
            unit.setStationary(false);
            unit.resetMoveCount();
            unit.setDefense(3);
        }
    }
}
