package hotciv.framework;

import hotciv.standard.GameImpl;

public interface WorldLayoutStrategy<setupWorldLayout> {
    String[] setupWorldLayout(GameImpl game);
}
