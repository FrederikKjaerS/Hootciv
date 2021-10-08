package hotciv.variants.winnerStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

public class Alternating20RoundWinnerStrategy implements WinnerStrategy {
    private ConquerAllWinnerStrategy conquerAllWinnerStrategy;
    private ThreeWinStrategy threeWinStrategy;

    public Alternating20RoundWinnerStrategy() {
        this.conquerAllWinnerStrategy = new ConquerAllWinnerStrategy();
        this.threeWinStrategy = new ThreeWinStrategy();
    }

    @Override
    public Player getWinner(ExtendedGame game) {
        if (game.getRounds() > 20) {
            return threeWinStrategy.getWinner(game);
        }
        return conquerAllWinnerStrategy.getWinner(game);
    }

    @Override
    public void incrementWin(ExtendedGame game, Player p) {
        if (game.getRounds() > 20) {
            threeWinStrategy.incrementWin(game, p);
        }
    }
}
