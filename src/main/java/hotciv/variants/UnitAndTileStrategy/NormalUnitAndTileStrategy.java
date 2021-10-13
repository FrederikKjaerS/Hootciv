package hotciv.variants.UnitAndTileStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.CityImpl;

public class NormalUnitAndTileStrategy implements UnitAndTileStrategy {
    @Override
    public boolean canMoveToTile(Unit unit, String tile) {
        return !(tile.equals(GameConstants.OCEANS)
                || tile.equals(GameConstants.MOUNTAINS));
    }

    @Override
    public void setProduction(ExtendedGame game, Position p, String unitType) {
        CityImpl city = game.getCities().get(p);
        city.setProduction(unitType);
    }
}
