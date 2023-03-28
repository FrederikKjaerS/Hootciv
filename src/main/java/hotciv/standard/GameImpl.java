package hotciv.standard;

import hotciv.factories.HotCivFactory;
import hotciv.framework.*;
import hotciv.stub.SpyGameObserver;
import hotciv.utility.NeighborTiles;
import hotciv.variants.actionStrategy.UnitActionStrategy;
import hotciv.variants.agingStrategy.AgingStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.productionStrategy.ProductionStrategy;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
import hotciv.variants.winnerStrategy.WinnerStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private Map<Position, TileImpl> map = new HashMap<Position, TileImpl>();
    private Map<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
    private Map<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
    private WinnerStrategy winnerStrategy;
    private AgingStrategy agingStrategy;
    private UnitActionStrategy unitActionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private AttackStrategy attackStrategy;
    private ProductionStrategy unitAndTileStrategy;
    private UnitPropertiesStrategy unitPropertiesStrategy;

    private List<GameObserver> gameObserverList;
    private int round;

    public GameImpl(HotCivFactory hotCivFactory) {
        this.gameObserverList = new ArrayList<>();
        this.winnerStrategy = hotCivFactory.createWinnerStrategy();
        this.agingStrategy = hotCivFactory.createAgingStrategy();
        this.attackStrategy = hotCivFactory.createAttackStrategy();
        this.unitActionStrategy = hotCivFactory.createUnitActionStrategy();
        this.worldLayoutStrategy = hotCivFactory.createWorldLayoutStrategy();
        this.unitAndTileStrategy = hotCivFactory.createProductionStrategy();
        this.unitPropertiesStrategy = hotCivFactory.createUnitPropertiesStrategy();
        this.round = 1;
        defineWorld();
        setupUnits();
        setupCities();
    }

    public TileImpl getTileAt(Position p) {
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

    @Override
    public boolean moveUnit(Position from, Position to) {
        if (! isMoveValid(from, to)) return false;
        makeActualMoveForUnit(from, to);
        handleCityConquering(to);
        return true;
    }

    private void makeActualMoveForUnit(Position from, Position to) {
        UnitImpl fromUnit = getUnitAt(from);
        if ( getUnitAt(to) != null ){
            boolean attackerWinsBattle = attackStrategy.attackerWins(this, from, to);

            if(! attackerWinsBattle) {
                units.remove(from);
                for(GameObserver g : gameObserverList){
                    g.worldChangedAt(from);
                }
            } else {
                winnerStrategy.incrementWin(this, getUnitAt(from).getOwner());
                units.remove(from);
                units.put(to, fromUnit);
            }
        }else {
            units.remove(from);
            if (!gameObserverList.isEmpty()) {
                for (GameObserver g : gameObserverList) {
                    g.worldChangedAt(from);
                }
            }
            units.put(to, fromUnit);
            if (!gameObserverList.isEmpty()) {
                for (GameObserver g : gameObserverList) {
                    g.worldChangedAt(to);
                }
            }
            getUnitAt(to).decreaseMoveCount();
        }
    }

    private boolean isMoveValid(Position from, Position to) {
        UnitImpl fromUnit = getUnitAt(from);
        TileImpl toTile = getTileAt(to);

        boolean isMoveInValidRange = Math.abs(to.getRow() - from.getRow()) <= 1
                && Math.abs(to.getColumn() - from.getColumn()) <= 1;
        if (! isMoveInValidRange) return false;

        boolean isMovingToOwnUnit = getUnitAt(to) != null && fromUnit.getOwner() == getUnitAt(to).getOwner();
        if (isMovingToOwnUnit) return false;

        boolean hasPositiveMoveCount = fromUnit.getMoveCount() > 0;
        if (! hasPositiveMoveCount) return false;

        boolean isPlayerInTurn = fromUnit.getOwner() == playerInTurn;
        if (! isPlayerInTurn) return false;

        if (! isPassableTerrain(getUnitAt(from), toTile)) return false;

        return true;
    }

    private boolean isPassableTerrain(UnitImpl u, TileImpl toTile) {
        for(TileImpl t :u.getValidTiles() ) {
            if (t.getTypeString().equals(toTile.getTypeString())) {
                return true;
            }
        }
        return false;
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
        if(!gameObserverList.isEmpty()){
            for(GameObserver g : gameObserverList){
                g.turnEnds(playerInTurn, getAge());
            }
        }
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        getCityAt(p).setWorkForceFocus(balance);
        if(!gameObserverList.isEmpty()) {
            for(GameObserver g : gameObserverList){
                g.worldChangedAt(p);
            }
        }
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        if(getCityAt(p).getOwner() == playerInTurn) {
            unitAndTileStrategy.setProduction(this, p, unitType);
            if(!gameObserverList.isEmpty()) {
                for(GameObserver g : gameObserverList){
                    g.worldChangedAt(p);
                }
            }
        }
    }

    public void performUnitActionAt(Position p) {
        unitActionStrategy.performAction(this, p);
        if(!gameObserverList.isEmpty()) {
            for(GameObserver g : gameObserverList){
                g.worldChangedAt(p);
            }
        }
    }




    private void defineWorld() {
        this.map = worldLayoutStrategy.setupTileLayout();
    }

    private void setupUnits() {
        this.units = (HashMap<Position, UnitImpl>) worldLayoutStrategy.setupUnitLayout(unitPropertiesStrategy);
    }

    private void setupCities() {
        this.cities = (HashMap<Position, CityImpl>) worldLayoutStrategy.setupCityLayout();
    }

    private void endOfRound() {
        incrementYear();
        incrementProduction();
        resetUnitsMoveCount();
        produceUnits();
        round++;
    }

    private void resetUnitsMoveCount() {
        for(UnitImpl unit : units.values()){
            if(!unit.isStationary()) {
                unit.resetMoveCount();
            }
        }
    }

    private void produceUnits() {
        for (Position cityPosition : getCities().keySet()) {
            CityImpl city = getCities().get(cityPosition);
            String unitType = city.getProduction();
            if(unitType.equals("")) {
                continue;
            }
            Player owner = city.getOwner();
            UnitImpl tempUnit = new UnitImpl(unitType, owner, unitPropertiesStrategy.getProperties(unitType));
            int costOfUnit = tempUnit.getCost();
            boolean canCityProduce = city.getTreasury() >= costOfUnit;
            if(! canCityProduce) continue;
            for(Position p : NeighborTiles.getCenterAnd8neighborhoodOf(cityPosition)) {
                boolean isUnitOnTile = getUnitAt(p) != null;
                if ( isUnitOnTile ) continue;
                if(! isPassableTerrain(tempUnit, getTileAt(p))) continue;
                this.units.put(p, tempUnit);
                city.decreaseTreasury(costOfUnit);
                break;
            }
        }
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

    @Override
    public int getRounds() {
        return round;
    }

    @Override
    public void addObserver(GameObserver observer) {
        this.gameObserverList.add(observer);
    }

    @Override
    public void setTileFocus(Position position) {
        if (!gameObserverList.isEmpty()) {
            if(position.getRow() < GameConstants.WORLDSIZE && position.getColumn() < GameConstants.WORLDSIZE){
                for(GameObserver g : gameObserverList){
                    g.tileFocusChangedAt(position);
                }
            }
        }
    }
}
