
package model;

public class StateFalling implements State{

    @Override
    public void nextState(GameFigure f) {
        f.state = new StateDone();
    }
}