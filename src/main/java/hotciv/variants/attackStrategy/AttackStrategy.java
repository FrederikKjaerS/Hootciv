package hotciv.variants.attackStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;

public interface AttackStrategy {
    boolean unitWins(ExtendedGame game, Position from, Position to);
}
