package hotciv.variants.worldStrategy;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
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
        return null;
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
