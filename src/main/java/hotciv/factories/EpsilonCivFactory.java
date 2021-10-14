package hotciv.factories;

import hotciv.variants.actionStrategy.AlphaActionStrategy;
import hotciv.variants.actionStrategy.UnitActionStrategy;
import hotciv.variants.agingStrategy.AgingStrategy;
import hotciv.variants.agingStrategy.HundredYearStrategy;
import hotciv.variants.attackStrategy.AlgoAttackStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.dieDecisionStrategy.SixSidedDieStrategy;
import hotciv.variants.unitAndTileStrategy.UnitAndTileStrategy;
import hotciv.variants.unitAndTileStrategy.NormalUnitAndTileStrategy;
import hotciv.variants.unitProperties.DefaultUnitProperties;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
import hotciv.variants.winnerStrategy.ThreeWinStrategy;
import hotciv.variants.winnerStrategy.WinnerStrategy;
import hotciv.variants.worldStrategy.AlphaCivLayoutStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;

public class EpsilonCivFactory implements HotCivFactory{
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
        return new ThreeWinStrategy();
    }

    @Override
    public AttackStrategy createAttackStrategy() {
        return new AlgoAttackStrategy(new SixSidedDieStrategy());
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaCivLayoutStrategy();
    }

    @Override
    public UnitAndTileStrategy createMovingStrategy() {
        return new NormalUnitAndTileStrategy();
    }

    @Override
    public UnitPropertiesStrategy createUnitPropertiesStrategy() {
        return new DefaultUnitProperties();
    }
}
