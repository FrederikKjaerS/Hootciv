package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class CityImpl implements City {
    private Player owner;
    private int treasury = 0;

    public CityImpl(Player owner) {
        this.owner = owner;
    }

    /**
     Adds amount to the field treasury.
    */
    public void addProduction(int amount) {
        treasury += amount;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getTreasury() {
        return treasury;
    }

    @Override
    public String getProduction() {
        return GameConstants.ARCHER;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
