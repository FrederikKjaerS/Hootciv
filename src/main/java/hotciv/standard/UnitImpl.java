package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {
    private String typeString;
    private Player owner;
    private int moveCount;
    private int defense;
    private int attack;
    private boolean isStationary = false;

    public UnitImpl(String unit, Player owner) {
        this.isStationary = false;
        this.typeString = unit;
        this.owner = owner;
        this.moveCount = 1;
        switch (typeString) {
            case GameConstants.ARCHER:
                this.defense = GameConstants.archerDefense;
                this.attack = GameConstants.archerAttack;
            case GameConstants.SETTLER:
                this.defense = GameConstants.settlerDefense;
                this.attack = GameConstants.settlerAttack;
                break;
            case GameConstants.LEGION:
                this.defense = GameConstants.legionDefense;
                this.attack = GameConstants.legionAttack;
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
        return attack;
    }

    public void decreaseMoveCount(){
        moveCount -= 1;
    }

    public void resetMoveCount(){
        moveCount = 1;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isStationary() {
        return isStationary;
    }

    public void setStationary(boolean stationary) {
        isStationary = stationary;
    }
}
