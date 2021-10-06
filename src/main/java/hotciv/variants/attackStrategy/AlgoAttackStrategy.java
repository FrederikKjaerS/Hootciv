package hotciv.variants.attackStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.utility.Utility2;
import hotciv.variants.attackStrategy.dieDecisionStrategy.DieDecisionStrategy;

public class AlgoAttackStrategy implements AttackStrategy {
    private DieDecisionStrategy die;
    private int fromAttack;
    private int toDefense;

    public AlgoAttackStrategy(DieDecisionStrategy dieDecisionStrategy) {
        this.die = dieDecisionStrategy;
    }

    @Override
    public boolean unitWins(ExtendedGame game, Position from, Position to) {
        fromAttack = getNewAttackStats(game,from);
        toDefense = getNewDefenseStats(game,to);
        return fromAttack > toDefense;
    }

    private int roll(){
        return die.getEyes();
    }

    public int getNewAttackStats(ExtendedGame game, Position p) {
        int terrainFactor = Utility2.getTerrainFactor(game, p);
        int friendlySupport = Utility2.getFriendlySupport(game,p, game.getUnitAt(p).getOwner());

        return (game.getUnitAt(p).getAttackingStrength() + terrainFactor + friendlySupport) * roll();
    }
    public int getNewDefenseStats(ExtendedGame game, Position p) {
        int terrainFactor = Utility2.getTerrainFactor(game, p);
        int friendlySupport = Utility2.getFriendlySupport(game,p, game.getUnitAt(p).getOwner());

        return (game.getUnitAt(p).getDefensiveStrength() + terrainFactor + friendlySupport) * roll();
    }
}
