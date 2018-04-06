package model;

public class StateVulnerable implements State{

    @Override
    public void nextState(GameFigure f) {
        f.state = new StateDestroyed();
    }
}