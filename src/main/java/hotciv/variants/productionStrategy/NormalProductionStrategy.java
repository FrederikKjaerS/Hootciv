package hotciv.variants.productionStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;

public class NormalProductionStrategy implements ProductionStrategy {

    @Override
    public void setProduction(ExtendedGame game, Position p, String unitType) {
        CityImpl city = game.getCities().get(p);
        city.setProduction(unitType);
    }
}
