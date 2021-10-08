package hotciv.factories;

import hotciv.variants.actionStrategy.AlphaActionStrategy;
import hotciv.variants.actionStrategy.UnitActionStrategy;
import hotciv.variants.agingStrategy.AgingStrategy;
import hotciv.variants.agingStrategy.HundredYearStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.AttackerWinsStrategy;
import hotciv.variants.winnerStrategy.RedWinnerStrategy;
import hotciv.variants.winnerStrategy.WinnerStrategy;
import hotciv.variants.worldStrategy.DeltaCivLayoutStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;

public class DeltaCivFactory implements HotCivFactory {
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaActionStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new HundredYearStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new RedWinnerStrategy();
    }

    @Override
    public AttackStrategy createAttackStrategy() {
        return new AttackerWinsStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new DeltaCivLayoutStrategy();
    }
}
