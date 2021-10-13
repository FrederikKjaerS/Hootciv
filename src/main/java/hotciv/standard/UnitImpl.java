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
        if(unit.equals(GameConstants.ARCHER)||
                unit.equals(GameConstants.LEGION)||
                unit.equals(GameConstants.SETTLER)){
            moveCount = 1;
        }
        if(unit.equals(GameConstants.SANDWORM)){
            moveCount = 2;
        }
        switch (typeString) {
            case GameConstants.ARCHER:
                this.defense = GameConstants.archerDefense;
                this.attack = GameConstants.archerAttack;
                break;
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
        if(typeString.equals(GameConstants.ARCHER)||
                typeString.equals(GameConstants.LEGION)||
                typeString.equals(GameConstants.SETTLER)){
            moveCount = GameConstants.archerMaxMove;
        }
        if(typeString.equals(GameConstants.SANDWORM)){
            moveCount = GameConstants.sandwormMaxMove;
        }
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
