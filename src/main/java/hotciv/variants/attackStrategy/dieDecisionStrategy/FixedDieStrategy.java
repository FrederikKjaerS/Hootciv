package hotciv.variants.attackStrategy.dieDecisionStrategy;

public class FixedDieStrategy implements DieDecisionStrategy{

    private int eyes;

    public FixedDieStrategy() {
        this.eyes = 1;
    }

    @Override
    public int getEyes() {
        return eyes;
    }

    public void setEyes(int i) {
        if(i > 0 && i <=6) {
            eyes = i;
        }
    }
}
