package hotciv.factories;

import hotciv.variants.actionStrategy.UnitActionStrategy;
import hotciv.variants.agingStrategy.AgingStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.unitAndTileStrategy.UnitAndTileStrategy;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
import hotciv.variants.winnerStrategy.WinnerStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;

public interface HotCivFactory {
    public UnitActionStrategy createUnitActionStrategy();
    public AgingStrategy createAgingStrategy();
    public WinnerStrategy createWinnerStrategy();
    public AttackStrategy createAttackStrategy();
    public WorldLayoutStrategy createWorldLayoutStrategy();
    public UnitAndTileStrategy createMovingStrategy();
    public UnitPropertiesStrategy createUnitPropertiesStrategy();
}
