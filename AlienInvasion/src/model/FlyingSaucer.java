
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import view.GamePanel;

public class FlyingSaucer extends GameFigure {
    
    private final int WIDTH = 40;
    private final int HEIGHT = 10;
    private final Color color = Color.yellow;
    private final int UNIT_TRAVEL = 5;
    private int direction = 1;
    
    public FlyingSaucer(float x, float y) {
        super(x, y);
        super.state = new StateFlying();
        super.pointValue = 50;
    }
    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)(super.x - WIDTH/2), (int)(super.y - HEIGHT/2), WIDTH, HEIGHT);
    }
    @Override
    public void update() {
        if (state instanceof StateFalling){
            super.y += (UNIT_TRAVEL * 2);
            if(super.y > GamePanel.height)
                state.nextState(this);
        }
        else if (direction > 0) {
            // moving to the right
            super.x += UNIT_TRAVEL;
            if (super.x + WIDTH/2 > GamePanel.width) {
                direction = -1;
            }
        } else {
            // moving to the left
            super.x -= UNIT_TRAVEL;
            if (super.x - WIDTH/2 <= 0) {
                direction = 1;
            }
        }
    }
    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Double collisionBox;
        collisionBox = new Rectangle2D.Double(super.x - (0.9*WIDTH)  / 2.0 ,
                                              super.y - (0.9*HEIGHT) / 2.0 ,
                                             (0.9*WIDTH),
                                             (0.9*HEIGHT));
        return collisionBox;    
    }
}