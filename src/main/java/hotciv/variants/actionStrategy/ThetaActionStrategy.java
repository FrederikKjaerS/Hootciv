package hotciv.variants.actionStrategy;

import ThetaCiv.ThetaCivGameConstants;
import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.utility.NeighborTiles;

public class ThetaActionStrategy implements UnitActionStrategy{
    private GammaActionStrategy gammaActionStrategy;

    public ThetaActionStrategy() {
        this.gammaActionStrategy = new GammaActionStrategy();
    }

    @Override
    public void performAction(ExtendedGame game, Position p) {
        if(game.getUnitAt(p).getTypeString().equals(ThetaCivGameConstants.SANDWORM)){
            for (Position killPosition : NeighborTiles.get8neighborhoodOf(p)) {
                if (game.getUnitAt(killPosition) != null){
                    if(game.getUnitAt(killPosition).getOwner() != game.getUnitAt(p).getOwner()){
                        game.removeUnit(killPosition);
                    }
                }
            }
        }
        else{
            gammaActionStrategy.performAction(game, p);
        }

    }
}
