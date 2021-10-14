package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.unitProperties.UnitProperties;

import java.util.ArrayList;

public class UnitImpl implements Unit {
    private String typeString;
    private Player owner;
    private int moveCount;
    private final int maxMoveCount;
    private int defense;
    private int attack;
    private boolean isStationary = false;
    private ArrayList<TileImpl> validTiles;

    public UnitImpl(String unit, Player owner, UnitProperties unitProperties) {
        this.isStationary = false;
        this.typeString = unit;
        this.owner = owner;
        this.moveCount= unitProperties.getMoveCount();
        this.maxMoveCount= unitProperties.getMoveCount();
        this.attack = unitProperties.getAttack();
        this.defense = unitProperties.getDefense();
        this.validTiles = unitProperties.getValidTiles();
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
        this.moveCount = this.maxMoveCount;
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
