package hotciv.variants.alphaCiv;

import hotciv.framework.WorldLayoutStrategy;
import hotciv.standard.GameImpl;

public class AlphaCivLayout implements WorldLayoutStrategy {

    @Override
    public String[] setupWorldLayout(GameImpl game) {
        String[] layout =
                new String[] {
                        "ohoooooooooooooo",
                        ".ooooooooooooooo",
                        "ooMooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                };
        return layout;
    }
}
