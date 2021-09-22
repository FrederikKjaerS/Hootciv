package hotciv.variants.alphaCiv;

import hotciv.framework.AgingStrategy;

public class HundredYearStrategy implements AgingStrategy {

    @Override
    public int incrementAge() {
        return 100;
    }
}
