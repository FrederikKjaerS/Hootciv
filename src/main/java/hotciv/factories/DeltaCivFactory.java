package hotciv.factories;

import hotciv.variants.actionStrategy.AlphaActionStrategy;
import hotciv.variants.actionStrategy.UnitActionStrategy;
import hotciv.variants.agingStrategy.AgingStrategy;
import hotciv.variants.agingStrategy.HundredYearStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.AttackerWinsStrategy;
import hotciv.variants.productionStrategy.ProductionStrategy;
import hotciv.variants.productionStrategy.NormalProductionStrategy;
import hotciv.variants.unitProperties.DefaultUnitProperties;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
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

    @Override
    public ProductionStrategy createMovingStrategy() {
        return new NormalProductionStrategy();
    }

    @Override
    public UnitPropertiesStrategy createUnitPropertiesStrategy() {
        return new DefaultUnitProperties();
    }
}
