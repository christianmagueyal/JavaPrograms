package model;

public class StateShielded implements State{

    @Override
    public void nextState(GameFigure f) {
         f.state = new StateVulnerable();
    }
}