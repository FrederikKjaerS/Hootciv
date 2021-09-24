package hotciv.variants.agingStrategy;

public class HundredYearStrategy implements AgingStrategy {

    @Override
    public int incrementAge(int age) {
        return 100;
    }
}
