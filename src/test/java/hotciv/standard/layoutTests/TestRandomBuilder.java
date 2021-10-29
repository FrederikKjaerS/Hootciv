package hotciv.standard.layoutTests;

import hotciv.factories.AlphaCivFactory;
import hotciv.framework.*;

import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
import hotciv.variants.worldStrategy.RandomBuilder;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestRandomBuilder {
    private Game game;


    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlphaCivFactory(){
            @Override
            public WorldLayoutStrategy createWorldLayoutStrategy() {
                return new RandomBuilder(){
                    @Override
                    public Map<Position, UnitImpl> setupUnitLayout(UnitPropertiesStrategy strategy){
                        HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
                        for(int i = 0; i < 15; i++){
                            units.put(new Position(5, i), new UnitImpl(GameConstants.SETTLER, Player.BLUE,
                                    strategy.getProperties(GameConstants.SETTLER)));
                        }
/*
                        units.put(new Position(4, 4), new UnitImpl(GameConstants.SETTLER, Player.BLUE,
                                strategy.getProperties(GameConstants.SETTLER)));
                        units.put(new Position(3, 2), new UnitImpl(GameConstants.SETTLER, Player.RED,
                                strategy.getProperties(GameConstants.SETTLER)));
*/

                        return units;
                        }
                    };
                };

        } );
    }

    private void endRound(int n) {
        for (int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void shouldBeRedAsStartingPlayer() {
        //Red is the starting player
        for(int i = 0; i < 15; i++){
            System.out.println(game.getTileAt(new Position(2,i)).getTypeString());
        }
        assertThat(game.getUnitAt(new Position(5,0)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,1)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,2)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,3)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,4)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,5)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,6)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,7)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,8)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,9)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,10)), is(notNullValue()));


    }

    @Test
    public void tile() {
        //Red is the starting player
        for(int i = 0; i < 15; i++){
            System.out.println(game.getTileAt(new Position(5,i)).getTypeString());
        }
    }

}