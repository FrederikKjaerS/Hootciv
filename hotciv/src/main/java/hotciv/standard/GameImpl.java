package hotciv.standard;

import hotciv.framework.*;

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
    private City redCity = new CityImpl(Player.RED);
    private City blueCity = new CityImpl(Player.BLUE);

    @Override
    public City getRedCity() {
        return redCity;
    }

    @Override
    public City getBlueCity() {
        return blueCity;
    }

    public Tile getTileAt(Position p) {
        return null;
    }

    public Unit getUnitAt(Position p) {
        return null;
    }

    public City getCityAt(Position p) {
        if (new Position(1, 1).equals(p)) {
            return new CityImpl(Player.RED);
        } else if (new Position(4, 1).equals(p)) {
            return new CityImpl(Player.BLUE);
        }
        return null;
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
                redCity.addProduction(6);
                blueCity.addProduction(6);
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
