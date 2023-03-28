package hotciv.variants.attackStrategy.dieDecisionStrategy;

import java.util.Random;

public class SixSidedDieStrategy implements DieDecisionStrategy{
    Random rand = new Random();

    @Override
    public int getEyes() {
        return rand.nextInt(6) + 1;
    }
}
