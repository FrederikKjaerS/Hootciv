package hotciv.variants.betaCiv;

import hotciv.framework.*;
import hotciv.variants.AgingStrategy;

public class AlgoAgingStrategy implements AgingStrategy {
    @Override
    public int incrementAge(Game game) {
        if (game.getAge() < -100) {
            return 100;
        }
        if (game.getAge() == -100) {
            return 99;
        }
        if (game.getAge() == -1) {
            return 2;
        }
        if (game.getAge() == 1) {
            return 49;
        }
        if (game.getAge() >= 50 && game.getAge() < 1750) {
            return 50;
        }
        if (game.getAge() >= 1750 && game.getAge() < 1900) {
            return 25;
        }
        if (game.getAge() >= 1900  && game.getAge() < 1970) {
            return 5;
        }
        if (game.getAge() >= 1970) {
            return 1;
        }
        return 0;
    }
}
