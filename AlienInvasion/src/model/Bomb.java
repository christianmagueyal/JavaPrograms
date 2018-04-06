package model;

import view.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Bomb extends GameFigure {

    private int radius;
    private Color color;
    private int dx = 3;
    private int dy = 3;

    public Bomb(float x, float y, int radius, Color color) {
        super(x, y);
        super.state = new StateBouncing();
        this.radius = radius;
        this.color = color;
        super.pointValue = 10;
    }
    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)(x - radius), (int)(y - radius), radius * 2, radius * 2);
    }
    @Override
    public void update() {
        if(state instanceof StateExploding){
            this.color = Color.WHITE;
            this.radius += 4;
            state.nextState(this);
        }
        else if(state instanceof StateBouncing){
            super.x += dx;
            super.y += dy;
            if (super.x + radius > GamePanel.width) {
                dx = -dx;
                super.x = GamePanel.width - radius;
            }
            else if (super.x - radius < 0) {
                dx = -dx;
                super.x = radius;
            }
            if (super.y + radius > GamePanel.height) {
                dy = -dy;
                super.y = GamePanel.height - radius;
            }
            else if (super.y - radius < 0) {
                dy = -dy;
                super.y = radius;
            }
        }
    }
    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Double collisionBox;
        collisionBox = new Rectangle2D.Double(super.x - (0.9 * radius * 2.0) / 2.0 ,
                                              super.y - (0.9 * radius * 2.0) / 2.0 ,
                                             (0.9 * (radius * 2)), (0.9 * (radius * 2)));
        return collisionBox;
    }
}
