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
    private Map<Position, Tile> map = new HashMap<Position, Tile>();
    private Map<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
    private Map<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
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

    public CityImpl getCityAt(Position p) {
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
        makeActualMoveForUnit(from, to);
        handleCityConquering(to);
        return true;
    }

    private void makeActualMoveForUnit(Position from, Position to) {
        UnitImpl fromUnit = getUnitAt(from);
        units.put(to, fromUnit);
        units.remove(from);
        getUnitAt(to).decreaseMoveCount();
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

        if (! isPassableTerrain(toTile)) return false;

        return true;
    }

    private boolean isPassableTerrain(Tile toTile) {
        boolean isPassableTerrain = ! (toTile.getTypeString().equals(GameConstants.OCEANS)
                || toTile.getTypeString().equals(GameConstants.MOUNTAINS));
        if (! isPassableTerrain) return false;
        return true;
    }

    private void handleCityConquering(Position to) {
        boolean isMovingToCity = getCityAt(to) != null;
        if (isMovingToCity) {
            getCityAt(to).setOwner(playerInTurn);
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
        getCityAt(p).setWorkForceFocus(balance);
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        if(getCityAt(p).getOwner() == playerInTurn) {
            getCityAt(p).setProduction(unitType);
        }
    }

    public void performUnitActionAt(Position p) {
        unitActionStrategy.performAction(this, p);
    }


    private void defineWorld() {
        this.map = worldLayoutStrategy.setupTileLayout();
    }

    private void setupUnits() {
        this.units = (HashMap<Position, UnitImpl>) worldLayoutStrategy.setupUnitLayout();
    }

    private void setupCities() {
        this.cities = (HashMap<Position, CityImpl>) worldLayoutStrategy.setupCityLayout();
    }

    private void endOfRound() {
        incrementYear();
        incrementProduction();
        resetUnitsMoveCount();
        produceUnits();
    }

    private void resetUnitsMoveCount() {
        for(UnitImpl unit : units.values()){
            if(!unit.isStationary()) {
                unit.resetMoveCount();
            }
        }
    }

    private void produceUnits() {
        for (Position cityP : getCities().keySet()) {
            boolean canCityProduce = getCityAt(cityP).canProduce();
            if(! canCityProduce) continue;
            for(Position p : NeighborTiles.getCenterAnd8neighborhoodOf(cityP)) {
                boolean isUnitOnTile = getUnitAt(p) != null;
                if ( isUnitOnTile ) continue;
                if(! isPassableTerrain(getTileAt(p))) continue;
                createNewUnit(cityP, p);
                break;
            }
        }
    }


    private void createNewUnit(Position cityP, Position p) {
        String unitType = getCities().get(cityP).getProduction();
        Player owner = getCities().get(cityP).getOwner();
        UnitImpl newUnit = new UnitImpl(unitType, owner);
        this.units.put(p, newUnit);
    }

    private void incrementProduction() {
        for (Position cityP : getCities().keySet()) {
            getCities().get(cityP).addProduction(6); //Magic constant!
        }
    }

    private void incrementYear() {
        this.year += agingStrategy.incrementAge(this.year);
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
