package hotciv.variants.attackStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;

public class AttackerWinsStrategy implements AttackStrategy{

        @Override
        public boolean unitWins(ExtendedGame game, Position from, Position to) {
                return true;
        }

        @Override
        public void updateStats(ExtendedGame game, Position from, Position to) {

        }
}
