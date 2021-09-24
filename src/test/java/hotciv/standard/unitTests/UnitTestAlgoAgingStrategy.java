package hotciv.standard.unitTests;


import hotciv.variants.agingStrategy.AlgoAgingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UnitTestAlgoAgingStrategy {
    private AlgoAgingStrategy strategy;

    @BeforeEach
    public void setUp() {
        strategy = new AlgoAgingStrategy();
    }

    @Test
    public void shouldReturn100WhenGiven4000BC() {
        assertThat(strategy.incrementAge(-4000), is(100));
    }

    @Test
    public void shouldReturn99WhenGiven100BC() {
        assertThat(strategy.incrementAge(-100), is(99));
    }

    @Test
    public void shouldReturn2WhenGiven1BC() {
        assertThat(strategy.incrementAge(-1), is(2));
    }

    @Test
    public void shouldReturn49WhenGiven1() {
        assertThat(strategy.incrementAge(1), is(49));
    }

    @Test
    public void shouldReturn50WhenGiven50() {
        assertThat(strategy.incrementAge(50), is(50));
    }

    @Test
    public void shouldReturn25WhenGiven1750() {
        assertThat(strategy.incrementAge(1750), is(25));
    }

    @Test
    public void shouldReturn5WhenGiven1900() {
        assertThat(strategy.incrementAge(1900), is(5));
    }

    @Test
    public void shouldReturn1WhenGiven1970() {
        assertThat(strategy.incrementAge(1970), is(1));
    }
}
