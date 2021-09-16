package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class CityImpl implements City {
    private Player owner;
    private int treasury = 0;
    private String production = "";
    private int populationSize = 1;
    private String workForceFocus;

    public CityImpl(Player owner) {
        this.owner = owner;
    }

    public void addProduction(int amount) {
        treasury += amount;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public int getSize() {
        return populationSize;
    }

    @Override
    public int getTreasury() {
        return treasury;
    }

    @Override
    public String getProduction() {
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        return workForceFocus;
    }

    public void setWorkForceFocus(String workForceFocus){
        this.workForceFocus = workForceFocus;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setProduction(String unit) {
        this.production = unit;
    }

    public boolean canProduce() {
        if (production.equals(GameConstants.ARCHER) && treasury >= 10) {
            treasury -= 10;
            return true;
        }
        if (production.equals(GameConstants.LEGION) && treasury >= 15) {
            treasury -= 15;
            return true;
        }
        if (production.equals(GameConstants.SETTLER) && treasury >= 30) {
            treasury -= 30;
            return true;
        }
        return false;
    }
}