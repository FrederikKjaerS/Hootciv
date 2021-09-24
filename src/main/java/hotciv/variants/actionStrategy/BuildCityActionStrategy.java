package hotciv.variants.actionStrategy;

import hotciv.framework.*;

public class BuildCityActionStrategy implements SettlerActionStrategy {

    @Override
    public void performAction(ExtendedGame game, Position p) {
        game.insertCity(p, game.getUnitAt(p).getOwner());
        game.removeUnit(p);
    }
}
