package hotciv.standard.layoutTests;

import hotciv.factories.AlphaCivFactory;
import hotciv.framework.*;

import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
import hotciv.variants.worldStrategy.RandomWorldStrategy;
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
                return new RandomWorldStrategy() {
                };
            }
        });
    }

    private void endRound(int n) {
        for (int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void shouldNotBeSameTileAt0_0For25DifferentGames() {
        ArrayList<String> tiles = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            setUp();
            String tileString = game.getTileAt(new Position(0,0)).getTypeString();
            tiles.add(tileString);
        }
        assertThat(tiles.stream().distinct().allMatch(tiles.get(0)::equals), is(false));
    }

    @Test
    public void shouldExistOnly1RedSettlerInRow0() {
        ArrayList<Unit> units = new ArrayList<>();
        for(int i = 0; i < GameConstants.WORLDSIZE; i++) {
            if(game.getUnitAt(new Position(0,i)) != null){
                units.add(game.getUnitAt(new Position(0,i)));
            }
        }
        assertThat(units.size(), is(1));
    }

    @Test
    public void shouldExistOnly1BlueSettlerInRow15() {
        ArrayList<Unit> units = new ArrayList<>();
        for(int i = 0; i < GameConstants.WORLDSIZE; i++) {
            if(game.getUnitAt(new Position(15,i)) != null){
                units.add(game.getUnitAt(new Position(15,i)));
            }
        }
        assertThat(units.size(), is(1));
    }

}
