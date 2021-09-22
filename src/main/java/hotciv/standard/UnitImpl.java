package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {
    private String typeString;
    private Player owner;
    private int moveCount;
    private int defense;

    public UnitImpl(String unit, Player owner) {
        this.typeString = unit;
        this.owner = owner;
        this.moveCount = 1;
        switch (typeString) {
            case GameConstants.ARCHER:
                this.defense = GameConstants.archerDefense;
            case GameConstants.SETTLER:
                this.defense = GameConstants.settlerDefense;
                break;
            case GameConstants.LEGION:
                this.defense = GameConstants.legionDefense;
                break;
        }
    }

    @Override
    public String getTypeString() {
        return typeString;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        return defense;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }

    public void decreaseMoveCount(){
        moveCount -= 1;
    }

    public void resetMoveCount(){
        moveCount = 1;
    }
}
