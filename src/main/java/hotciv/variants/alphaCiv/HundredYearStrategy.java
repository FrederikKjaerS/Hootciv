package hotciv.variants.alphaCiv;

import hotciv.variants.AgingStrategy;
import hotciv.framework.Game;

public class HundredYearStrategy implements AgingStrategy {

    @Override
    public int incrementAge(Game game) {
        return 100;
    }
}
