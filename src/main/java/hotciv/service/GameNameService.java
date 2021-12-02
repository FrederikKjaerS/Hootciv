package hotciv.service;/*
 * Copyright (C) 2018-2021. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import hotciv.framework.City;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import java.util.HashMap;
import java.util.Map;

/** An NameService that keeps all servant objects in memory.
 * Only suitable for single server solutions.
 *
 * @author Frederik & Mads
 */
public class GameNameService implements NameService {
    private Map<String, City> cityMap;
    private Map<String, Unit> unitMap;
    private Map<String, Tile> tileMap;

    public GameNameService() {
        this.cityMap = new HashMap<>();
        this.unitMap = new HashMap<>();
        this.tileMap = new HashMap<>();
    }

    @Override
    public void putUnit(String objectId, Unit unit) {
        this.unitMap.put(objectId, unit);
    }

    @Override
    public Unit getUnit(String objectId) {
        return this.unitMap.get(objectId);
    }

    @Override
    public void putCity(String objectId, City city) {
        this.cityMap.put(objectId, city);
    }

    @Override
    public City getCity(String objectId) {
        return this.cityMap.get(objectId);
    }

    @Override
    public void putTile(String objectId, Tile tile) {
        this.tileMap.put(objectId, tile);
    }

    @Override
    public Tile getTile(String objectId) {
        return this.tileMap.get(objectId);
    }
}

