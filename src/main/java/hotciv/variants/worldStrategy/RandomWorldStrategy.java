package hotciv.variants.worldStrategy;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;
import java.util.Map;

public class RandomWorldStrategy implements WorldLayoutStrategy{

    private  Map<Position, Tile> tileLayout;

    @Override
    public Map<Position, Tile> setupTileLayout() {
        String[] layout = getRandomSetup();
        Map<Position, Tile> theWorld = new HashMap<Position,Tile>();
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
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
                theWorld.put( p, new TileImpl(type));
            }
        }
        this.tileLayout = theWorld;
        return theWorld;
    }

    @Override
    public Map<Position, UnitImpl> setupUnitLayout(UnitPropertiesStrategy strategy) {
        HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
        UnitImpl redUnit = new UnitImpl(GameConstants.SETTLER, Player.RED,
                strategy.getProperties(GameConstants.SETTLER));
        UnitImpl blueUnit = new UnitImpl(GameConstants.SETTLER, Player.BLUE,
                strategy.getProperties(GameConstants.SETTLER));

        outerLoop:
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (TileImpl tile: redUnit.getValidTiles()){
                if (tile.getTypeString().equals(this.getTileLayout().get(new Position(0, i))
                        .getTypeString())) {
                    units.put(new Position(0, i), redUnit);
                    break outerLoop;
                }
            }
        }

        outerLoop:
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (TileImpl tile: blueUnit.getValidTiles()){
                if (tile.getTypeString().equals(this.getTileLayout().get(new Position(15, i))
                        .getTypeString())) {
                    units.put(new Position(15, i), blueUnit);
                    break outerLoop;
                }
            }
        }
        return units;
    }

    @Override
    public Map<Position, CityImpl> setupCityLayout() {
        return null;
    }

    private String[] getRandomSetup(){
        ThirdPartyFractalGenerator generator =
                new ThirdPartyFractalGenerator();
        String[] layout = new String[GameConstants.WORLDSIZE];
        String line;
        System.out.println("Demonstration of the fractal landscape generator");
        for ( int r = 0; r < 16; r++ ) {
            line = "";
            for ( int c = 0; c < 16; c++ ) {
                line = line + generator.getLandscapeAt(r,c);
            }
            layout[r] = line;
        }
        return layout;
    }

    public Map<Position, Tile> getTileLayout() {
        return tileLayout;
    }
    public static void main(String[] args) {
        RandomWorldStrategy r = new RandomWorldStrategy();
        System.out.println(r.getRandomSetup());
    }
}
