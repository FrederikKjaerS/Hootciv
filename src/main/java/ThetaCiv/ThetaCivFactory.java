package ThetaCiv;

import hotciv.factories.HotCivFactory;
import hotciv.variants.actionStrategy.UnitActionStrategy;
import hotciv.variants.agingStrategy.AgingStrategy;
import hotciv.variants.agingStrategy.AlgoAgingStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.AttackerWinsStrategy;
import hotciv.variants.productionStrategy.ProductionStrategy;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
import hotciv.variants.winnerStrategy.ConquerAllWinnerStrategy;
import hotciv.variants.winnerStrategy.WinnerStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;

public class ThetaCivFactory implements HotCivFactory {
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new ThetaActionStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlgoAgingStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ConquerAllWinnerStrategy();
    }

    @Override
    public AttackStrategy createAttackStrategy() {
        return new AttackerWinsStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new ThetaCivLayoutStrategy();
    }

    @Override
    public ProductionStrategy createMovingStrategy() {
        return new ThetaProductionStrategy();
    }

    @Override
    public UnitPropertiesStrategy createUnitPropertiesStrategy() {
        return new ThetaUnitProperties();
    }
}
