package hotciv.variants.unitAndTileStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;

public class NormalUnitAndTileStrategy implements ProductionStrategy {

    @Override
    public void setProduction(ExtendedGame game, Position p, String unitType) {
        CityImpl city = game.getCities().get(p);
        city.setProduction(unitType);
    }
}
