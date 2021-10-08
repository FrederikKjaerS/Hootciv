package hotciv.variants.attackStrategy;

import hotciv.framework.Game;
import hotciv.framework.Position;

public class AttackerWinsStrategy implements AttackStrategy{

        @Override
        public boolean attackerWins(Game game, Position from, Position to) {
                return true;
        }

}
