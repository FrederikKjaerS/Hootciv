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
                    @Override
                    public Map<Position, UnitImpl> setupUnitLayout(UnitPropertiesStrategy strategy){
                        HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
                        UnitImpl redUnit = new UnitImpl(GameConstants.SETTLER, Player.BLUE,
                                strategy.getProperties(GameConstants.SETTLER));
                        UnitImpl blueUnit = new UnitImpl(GameConstants.SETTLER, Player.BLUE,
                                strategy.getProperties(GameConstants.SETTLER));

                        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
                            for (TileImpl tile: redUnit.getValidTiles()){
                                if (tile.getTypeString().equals(this.getTileLayout().get(new Position(0, i))
                                        .getTypeString())) {
                                    units.put(new Position(0, i), redUnit);
                                    break;
                                }
                            }
                        }
                        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
                            for (TileImpl tile: blueUnit.getValidTiles()){
                                if (tile.getTypeString().equals(this.getTileLayout().get(new Position(15, i))
                                        .getTypeString())) {
                                    units.put(new Position(15, i), blueUnit);
                                    break;
                                }
                            }
                        }
                        return units;
                    }
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
}