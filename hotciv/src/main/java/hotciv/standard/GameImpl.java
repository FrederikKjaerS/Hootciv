package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * Skeleton implementation of HotCiv.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class GameImpl implements Game {
    private Player playerInTurn = Player.RED;
    private int year = -4000;
    private HashMap<Position,Tile> map = new HashMap<Position, Tile>();
    private HashMap<Position,Unit> units = new HashMap<Position, Unit>();
    private HashMap<Position,City> cities = new HashMap<Position, City>();

    public GameImpl() {
        setupGameLayout();
        setupUnits();
        setupCities();
    }

    public void setupGameLayout() {
        //Setup Plains in all Positions
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                this.map.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
            }
        }
        //Setup Oceans
        this.map.put(new Position(1, 0), new TileImpl(GameConstants.OCEANS));
        //Setup Hills
        this.map.put(new Position(0, 1), new TileImpl(GameConstants.HILLS));
        //Setup Mountains
        this.map.put(new Position(2, 2), new TileImpl(GameConstants.MOUNTAINS));
    }

    public void setupUnits() {
        this.units.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER,Player.RED));
        this.units.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION,Player.BLUE));
        this.units.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER,Player.RED));
    }

    public void setupCities(){
        this.cities.put(new Position(1, 1), new CityImpl(Player.RED));
        this.cities.put(new Position(4, 1), new CityImpl(Player.BLUE));
    }

    public Tile getTileAt(Position p) {
        return this.map.get(p);
    }

    public Unit getUnitAt(Position p) {
        return this.units.get(p);
    }

    public City getCityAt(Position p) {
        return cities.get(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        if (getAge() == -3000) {
            return Player.RED;
        }

        return null;
    }

    public int getAge() {
        return year;
    }

    public boolean moveUnit(Position from, Position to) {
        return false;
    }

    public void endOfTurn() {
        switch (playerInTurn) {
            case RED:
                playerInTurn = Player.BLUE;
                break;
            case BLUE:
                playerInTurn = Player.RED;
                year += 100;
                for(City c: cities.values()) {
                    c.addProduction(6);
                }
                break;
        }
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
    }

    public void performUnitActionAt(Position p) {
    }
}
