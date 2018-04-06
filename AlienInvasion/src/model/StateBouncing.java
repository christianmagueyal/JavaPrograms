
package model;

public class StateBouncing implements State {

    public StateBouncing() {}
    @Override
    public void nextState(GameFigure f) {
        f.state = new StateExploding();
    }
}