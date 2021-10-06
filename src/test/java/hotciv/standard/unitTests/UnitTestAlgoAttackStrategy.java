package hotciv.standard.unitTests;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.variants.agingStrategy.AlgoAgingStrategy;
import hotciv.variants.attackStrategy.AlgoAttackStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.dieDecisionStrategy.DieDecisionStrategy;
import hotciv.variants.attackStrategy.dieDecisionStrategy.FixedDieStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UnitTestAlgoAttackStrategy {

    @Test
    public void shouldReturn10WhenGiven5() {
        //Given that die returns 2
        AlgoAttackStrategy as = new AlgoAttackStrategy(new FixedDieStrategy(2));
        assertThat(as.getStatsAfterRoll(5),is(10));
    }


}
