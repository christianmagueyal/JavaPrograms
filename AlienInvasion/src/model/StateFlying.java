package model;

public class StateFlying implements State {

    @Override
    public void nextState(GameFigure f) {
        f.state = new StateFalling();
    }
}