package hotciv.factories;

import hotciv.variants.actionStrategy.AlphaActionStrategy;
import hotciv.variants.actionStrategy.UnitActionStrategy;
import hotciv.variants.agingStrategy.AgingStrategy;
import hotciv.variants.agingStrategy.AlgoAgingStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.AttackerWinsStrategy;
import hotciv.variants.UnitAndTileStrategy.UnitAndTileStrategy;
import hotciv.variants.UnitAndTileStrategy.NormalUnitAndTileStrategy;
import hotciv.variants.winnerStrategy.ConquerAllWinnerStrategy;
import hotciv.variants.winnerStrategy.WinnerStrategy;
import hotciv.variants.worldStrategy.AlphaCivLayoutStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;

public class BetaCivFactory implements HotCivFactory{
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaActionStrategy();
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
        return new AlphaCivLayoutStrategy();
    }

    @Override
    public UnitAndTileStrategy createMovingStrategy() {
        return new NormalUnitAndTileStrategy();
    }
}


