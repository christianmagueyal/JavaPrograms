package model;

public class StateExploding implements State{
    public StateExploding(){}

    @Override
    public void nextState(GameFigure f) {
        f.state = new StateDone();
    }
}