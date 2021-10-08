package hotciv.variants.attackStrategy;

import hotciv.framework.Game;
import hotciv.framework.Position;

public interface AttackStrategy {
    boolean attackerWins(Game game, Position from, Position to);
}
