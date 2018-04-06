package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import view.GamePanel;

public class Laser implements Weapon{
    private float x;
    private float y;
    private static final int SIZE = 5;
    private float dx;
    private float dy;
    private final double shootAngle;
    private final double angle;
    private static final int UNIT_TRAVEL_DISTANCE = 10;
    public Color color = Color.blue;
    public Point2D.Float target;
    public Ellipse2D.Double laser;
    public State state;
    public int damagePoints = 10;
    public int size = SIZE;
    public Laser(float sx, float sy, float tx, float ty) {
        x = Main.gameData.tank.getXofMissileShoot();
        y = Main.gameData.tank.getYofMissileShoot();
        state = new StateShot();
        target = new Point2D.Float(tx, ty);
        this.angle = Main.gameData.tank.angle;
        shootAngle = Math.atan2(Math.abs(ty - y), Math.abs(tx - x));
        dx = (float) (UNIT_TRAVEL_DISTANCE * Math.cos(shootAngle));
        dy = (float) (UNIT_TRAVEL_DISTANCE * Math.sin(shootAngle));
        if (tx > x && ty < y) { // target is upper-right side
            dy = -dy; // dx > 0, dx < 0
        } else if (tx < x && ty < y) { // target is upper-left side
            dx = -dx;
            dy = -dy;
        } else if (tx < x && ty > y) { // target is lower-left side
            dx = -dx;
        } else { // target is lower-right side
            // dx > 0 , dy > 0
        }
    }
    @Override
    public void stateChange(){
        if(state instanceof StateShot)
            state = new StateHit();
        if(state instanceof StateHit)
            state = new StateDone();
    }    
    @Override
    public void render(Graphics2D g, int sz) {
        g.setColor(color);
        g.fillOval((int) (x - sz / 2), (int) (y - sz / 2),
                    sz, sz);
        g.setColor(Color.yellow);
    }
    @Override
    public void update() {
        if (state instanceof StateShot) {
            updateLocation();
        } else if (state instanceof StateHit) {
            stateChange();
        }
        if(y < 0 || x <0 || x > GamePanel.width)
            state = new StateDone();
    }
    public void updateLocation() {
        x += dx;
        y += dy;
    }
    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Double collisionBox;
        collisionBox = new Rectangle2D.Double(x - getSize()*0.9 / 2.0 , y
                                              - getSize()*0.9 / 2.0,
                                             getSize()*0.9, getSize()*0.9);
        return collisionBox;
    }
    @Override
    public int getDamagePoints() {
        return this.damagePoints;
    }
    @Override
    public State getState() {
        return state;
    }
    @Override
    public void setState(State s) {
        this.state = s;
    }
    @Override
    public int getSize() {
        return size;
    }
}