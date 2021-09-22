package hotciv.standard;

import hotciv.framework.*;
import hotciv.utility.NeighborTiles;

import java.util.HashMap;
import java.util.Map;

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
    private HashMap<Position, Tile> map = new HashMap<Position, Tile>();
    private HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
    private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
    private WinnerStrategy winnerStrategy;
    private AgingStrategy agingStrategy;
    private SettlerActionStrategy settlerActionStrategy;
    private ArcherActionStrategy archerActionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;

    public GameImpl(WinnerStrategy winnerStrategy, AgingStrategy agingStrategy,
                    SettlerActionStrategy settlerActionStrategy, ArcherActionStrategy archerActionStrategy,
                    WorldLayoutStrategy worldLayoutStrategy) {
        this.winnerStrategy = winnerStrategy;
        this.agingStrategy = agingStrategy;
        this.settlerActionStrategy = settlerActionStrategy;
        this.archerActionStrategy = archerActionStrategy;
        this.worldLayoutStrategy = worldLayoutStrategy;
        defineWorld();
        setupUnits();
        setupCities();
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
        return winnerStrategy.getWinner(this);
    }

    public int getAge() {
        return year;
    }

    public boolean moveUnit(Position from, Position to) {
        UnitImpl fromUnit = units.get(from);
        Tile toTile = getTileAt(to);

        if (getCityAt(to) != null) {
            cities.get(to).setOwner(fromUnit.getOwner());
        }
        if(Math.abs(to.getRow() - from.getRow()) > 1 ) {
            return false;
        }
        if(Math.abs(to.getColumn() - from.getColumn()) > 1 ) {
            return false;
        }
        if (getUnitAt(to) != null) {
            if (fromUnit.getOwner() == getUnitAt(to).getOwner()) {
                return false;
            }
        }
        if (fromUnit.getMoveCount() < 1) {
            return false;
        }
        if (fromUnit.getOwner() != playerInTurn) {
            return false;
        }
        if (toTile.getTypeString().equals(GameConstants.OCEANS) || toTile.getTypeString().equals(GameConstants.MOUNTAINS)){
            return false;
        }
        units.put(to, fromUnit);
        units.remove(from);
        fromUnit.decreaseMoveCount();
        return true;
    }

    public void endOfTurn() {
        switch (playerInTurn) {
            case RED:
                playerInTurn = Player.BLUE;
                break;
            case BLUE:
                playerInTurn = Player.RED;
                endOfRound();
                break;
        }
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        cities.get(p).setWorkForceFocus(balance);
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        if(cities.get(p).getOwner() == playerInTurn) {
            cities.get(p).setProduction(unitType);
        }
    }

    public void performUnitActionAt(Position p) {
        if(units.get(p).getTypeString() == GameConstants.SETTLER) {
            settlerActionStrategy.performAction(p);
        }
        if(units.get(p).getTypeString() == GameConstants.ARCHER) {
            archerActionStrategy.performAction(p);
        }
    }


    private void defineWorld() {
        // Basically we use a 'data driven' approach - code the
        // layout in a simple semi-visual representation, and
        // convert it to the actual Game representation.
        // Conversion...
        String[] layout = worldLayoutStrategy.setupTileLayout();
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                this.map.put( p, new TileImpl(type));
            }
        }
    }

    private void setupUnits() {
        this.units = worldLayoutStrategy.setupUnitLayout();
    }

    private void setupCities() {
        this.cities = worldLayoutStrategy.setupCityLayout();
    }

    private void endOfRound() {
        year += agingStrategy.incrementAge(this);
        for(UnitImpl u : units.values()){
            u.resetMoveCount();
        }
        for (Position cityP : cities.keySet()) {
            cities.get(cityP).addProduction(6);
            if(cities.get(cityP).canProduce()) {
                for(Position p : NeighborTiles.getCenterAnd8neighborhoodOf(cityP)) {
                    if (getUnitAt(p) == null) {
                        this.units.put(p, new UnitImpl(cities.get(cityP).getProduction(), cities.get(cityP).getOwner()));
                        break;
                    }
                }
            }
        }
    }
}
