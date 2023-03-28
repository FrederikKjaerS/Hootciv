package hotciv.standard;

import ThetaCiv.ThetaCivGameConstants;
import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

import java.util.UUID;

public class CityImpl implements City {
    private Player owner;
    private int treasury = 0;
    private String production = "";
    private int populationSize = 1;
    private String workForceFocus;
    private String ID;

    public CityImpl(Player owner) {
        this.owner = owner;
        this.ID = UUID.randomUUID().toString();
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

    @Override
    public String getID() {
        return this.ID;
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

    public void decreaseTreasury(int amount) {
        this.treasury -= amount;
    }

}