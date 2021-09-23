package hotciv.variants.actionStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.standard.UnitImpl;

public class FortifyActionStrategy implements ArcherActionStrategy{

    @Override
    public void performAction(ExtendedGame game, Position p) {
        UnitImpl unit = (UnitImpl) game.getUnitAt(p);
        unit.setDefense(6);
    }
}
