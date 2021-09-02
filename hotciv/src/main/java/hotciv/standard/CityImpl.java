package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

public class CityImpl implements City {
    private Player owner;
    private int treasury = 0;

    @Override
    public void addProduction(int i) {
        treasury += i;
    }

    public CityImpl(Player owner) {
        this.owner = owner;
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
        return null;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
