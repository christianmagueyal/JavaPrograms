package model;

class StateHit implements State {

    public StateHit() {
    }
    @Override
    public void nextState(GameFigure f) {
        f.state = new StateDone();
    }
}