package hotciv.framework;

import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface ExtendedGame extends Game {
    void removeUnit(Position p);
    void insertCity(Position p, Player owner);
    Map<Position, CityImpl> getCities();
}


