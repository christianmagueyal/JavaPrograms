package model;

public class StateShot implements State{
    
    public StateShot(){}
    @Override
    public void nextState(GameFigure f) {
        f.state = new StateHit();
    }
}