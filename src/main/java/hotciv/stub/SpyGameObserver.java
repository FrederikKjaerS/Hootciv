package hotciv.stub;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.ArrayList;

public class SpyGameObserver implements GameObserver {

    public ArrayList<Position> positions;

    public SpyGameObserver() {
        this.positions = new ArrayList<>();
    }

    @Override
    public void worldChangedAt(Position pos) {
        this.positions.add(pos);
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {

    }

    @Override
    public void tileFocusChangedAt(Position position) {

    }


}
