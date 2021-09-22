package hotciv.variants.alphaCiv;

import hotciv.framework.AgingStrategy;

public class hundredYearStrategy implements AgingStrategy {

    @Override
    public int incrementAge() {
        return 100;
    }
}
