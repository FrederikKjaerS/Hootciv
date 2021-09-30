package hotciv.standard;

import hotciv.framework.*;
import hotciv.utility.NeighborTiles;
import hotciv.variants.actionStrategy.UnitActionStrategy;
import hotciv.variants.agingStrategy.AgingStrategy;
import hotciv.variants.winnerStrategy.WinnerStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;

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

public class GameImpl implements Game, ExtendedGame {
    private Player playerInTurn = Player.RED;
    private int year = -4000;
    private HashMap<Position, Tile> map = new HashMap<Position, Tile>();
    private HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
    private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
    private WinnerStrategy winnerStrategy;
    private AgingStrategy agingStrategy;
    private UnitActionStrategy unitActionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;

    public GameImpl(WinnerStrategy winnerStrategy, AgingStrategy agingStrategy,
                     UnitActionStrategy unitActionStrategy,
                    WorldLayoutStrategy worldLayoutStrategy) {
        this.winnerStrategy = winnerStrategy;
        this.agingStrategy = agingStrategy;
        this.unitActionStrategy = unitActionStrategy;
        this.worldLayoutStrategy = worldLayoutStrategy;
        defineWorld();
        setupUnits();
        setupCities();
    }

    public Tile getTileAt(Position p) {
        return this.map.get(p);
    }

    public UnitImpl getUnitAt(Position p) {
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
        if (! isMoveValid(from, to)) return false;
        makeActualMove(from, to);
        handleCityConquering(to);
        getUnitAt(to).decreaseMoveCount();
        return true;
    }

    private void makeActualMove(Position from, Position to) {
        UnitImpl fromUnit = getUnitAt(from);
        units.put(to, fromUnit);
        units.remove(from);
    }

    private boolean isMoveValid(Position from, Position to) {
        UnitImpl fromUnit = getUnitAt(from);
        Tile toTile = getTileAt(to);

        boolean isMoveInValidRange = Math.abs(to.getRow() - from.getRow()) <= 1
                && Math.abs(to.getColumn() - from.getColumn()) <= 1;
        if (! isMoveInValidRange) return false;

        boolean isMovingToOwnUnit = getUnitAt(to) != null && fromUnit.getOwner() == getUnitAt(to).getOwner();
        if (isMovingToOwnUnit) return false;

        boolean hasPositiveMoveCount = fromUnit.getMoveCount() > 0;
        if (! hasPositiveMoveCount) return false;

        boolean isPlayerInTurn = fromUnit.getOwner() == playerInTurn;
        if (! isPlayerInTurn) return false;

        boolean isPassableTerrain = ! (toTile.getTypeString().equals(GameConstants.OCEANS)
                || toTile.getTypeString().equals(GameConstants.MOUNTAINS));
        if (! isPassableTerrain) return false;

        return true;
    }

    private void handleCityConquering(Position to) {
        boolean isMovingToCity = getCityAt(to) != null;
        if (isMovingToCity) {
            cities.get(to).setOwner(playerInTurn);
        }
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
        unitActionStrategy.performAction(this, p);
    }


    private void defineWorld() {
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
        this.units = (HashMap<Position, UnitImpl>) worldLayoutStrategy.setupUnitLayout();
    }

    private void setupCities() {
        this.cities = (HashMap<Position, CityImpl>) worldLayoutStrategy.setupCityLayout();
    }

    private void endOfRound() {
        year += agingStrategy.incrementAge(year);
        for(UnitImpl u : units.values()){
            if(!u.isStationary()) {
                u.resetMoveCount();
            }
        }
        for (Position cityP : cities.keySet()) {
            cities.get(cityP).addProduction(6);
            if(cities.get(cityP).canProduce()) {
                for(Position p : NeighborTiles.getCenterAnd8neighborhoodOf(cityP)) {
                    if (getUnitAt(p) == null && ! (getTileAt(p).getTypeString().equals(GameConstants.OCEANS)
                            || getTileAt(p).getTypeString().equals(GameConstants.MOUNTAINS))) {
                        this.units.put(p, new UnitImpl(cities.get(cityP).getProduction(), cities.get(cityP).getOwner()));
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void removeUnit(Position p) {
        units.remove(p);
    }

    @Override
    public void insertCity(Position p, Player owner) {
        CityImpl city = new CityImpl(owner);
        cities.put(p, city);
    }

    @Override
    public Map<Position, CityImpl> getCities() {
        return cities;
    }


}
