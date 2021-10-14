package hotciv.standard.thetaCiv;

import ThetaCiv.ThetaCivFactory;
import ThetaCiv.ThetaCivGameConstants;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDevourAction {
    private Game game;


    @BeforeEach
    public void setUp() {
        this.game = new GameImpl(new ThetaCivFactory(){
            @Override
            public WorldLayoutStrategy createWorldLayoutStrategy() {
                return new hotciv.standard.thetaCiv.StubLayout();
            }
        });
    }

    @Test
    public void shouldKillAllRedUnitsWhenBluesSandwormPerformsAction(){
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,2)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(3,3)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(2,2)), is(notNullValue()));
        game.performUnitActionAt(new Position(2,3));
        assertThat(game.getUnitAt(new Position(3,2)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(3,3)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(2,2)), is(nullValue()));
    }

    @Test
    public void shouldNotKillAllBlueUnitWhenBluesSandwormPerformsAction(){
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(1,3)), is(notNullValue()));
        game.performUnitActionAt(new Position(2,3));
        assertThat(game.getUnitAt(new Position(1,3)), is(notNullValue()));

    }

}

class StubLayout implements WorldLayoutStrategy{

    @Override
    public Map<Position,Tile> setupTileLayout() {
        Map<Position, Tile> theWorld = new HashMap<Position,Tile>();
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                theWorld.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
            }
        }
        return theWorld;
    }

    @Override
    public Map<Position, UnitImpl> setupUnitLayout(UnitPropertiesStrategy strategy) {
        HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
        units.put(new Position(1, 3), new UnitImpl(GameConstants.ARCHER, Player.BLUE,
                strategy.getProperties(GameConstants.ARCHER)));
        units.put(new Position(3, 2), new UnitImpl(GameConstants.ARCHER, Player.RED,
                strategy.getProperties(GameConstants.ARCHER)));
        units.put(new Position(2, 3), new UnitImpl(ThetaCivGameConstants.SANDWORM, Player.BLUE,
                strategy.getProperties(ThetaCivGameConstants.SANDWORM)));
        units.put(new Position(3, 3), new UnitImpl(GameConstants.ARCHER, Player.RED,
                strategy.getProperties(GameConstants.ARCHER)));
        units.put(new Position(2, 2), new UnitImpl(GameConstants.ARCHER, Player.RED,
                strategy.getProperties(GameConstants.ARCHER)));
        return units;
    }

    @Override
    public Map<Position, CityImpl> setupCityLayout() {
        HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
        return cities;
    }
}

