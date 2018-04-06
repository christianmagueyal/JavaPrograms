package model;

public class StateDestroyed implements State{

    @Override
    public void nextState(GameFigure f) {
        f.state = new StateDestroyed();
    }
}