package hotciv.framework;

import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface ExtendedGame extends Game {
    HashMap<Position, UnitImpl> getUnits();
    HashMap<Position, CityImpl> getCities();

    void removeUnit(Position p);
    void insertCity(Position p, Player owner);

}


