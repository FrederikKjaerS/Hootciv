package hotciv.factories;

import hotciv.variants.actionStrategy.GammaActionStrategy;
import hotciv.variants.actionStrategy.UnitActionStrategy;
import hotciv.variants.agingStrategy.AgingStrategy;
import hotciv.variants.agingStrategy.AlgoAgingStrategy;
import hotciv.variants.attackStrategy.AlgoAttackStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.dieDecisionStrategy.SixSidedDieStrategy;
import hotciv.variants.productionStrategy.ProductionStrategy;
import hotciv.variants.productionStrategy.NormalProductionStrategy;
import hotciv.variants.unitProperties.DefaultUnitProperties;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
import hotciv.variants.winnerStrategy.ThreeWinStrategy;
import hotciv.variants.winnerStrategy.WinnerStrategy;
import hotciv.variants.worldStrategy.DeltaCivLayoutStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;

public class SemiCivFactory implements HotCivFactory{
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new GammaActionStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlgoAgingStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ThreeWinStrategy();
    }

    @Override
    public AttackStrategy createAttackStrategy() {
        return new AlgoAttackStrategy(new SixSidedDieStrategy());
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
