package hotciv.variants.attackStrategy.dieDecisionStrategy;

public class FixedDieStrategy implements DieDecisionStrategy{

    private int eyes;

    public FixedDieStrategy(int i) {
        if(i > 0 && i <=6) {
            eyes = i;
        }
    }

    @Override
    public int getEyes() {
        return eyes;
    }


}
