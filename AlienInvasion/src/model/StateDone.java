package model;

public class StateDone implements State {
    
    public StateDone(){
    }
    @Override
    public void nextState(GameFigure f) {
        f.state = new StateDone();
    }
}