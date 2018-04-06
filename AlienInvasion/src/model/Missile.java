package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Missile implements Weapon {

    boolean targetReached = false;
    private final int damagePoints = 10;
    private static final int UNIT_TRAVEL_DISTANCE = 4; // per frame move
    private int size = SIZE;
    private static final int SIZE = 5;
    private float dx;
    private float dy;
    public float x;
    public float y;
    public State state;
    private final Color color = Color.red;
    public Point2D.Float target;
    public Missile(float sx, float sy, float tx, float ty) {
        x = sx;
        y = sy;
        state = new StateShot();
        target = new Point2D.Float(tx, ty);
        double angle = Math.atan2(Math.abs(ty - sy), Math.abs(tx - sx));
        dx = (float) (UNIT_TRAVEL_DISTANCE * Math.cos(angle));
        dy = (float) (UNIT_TRAVEL_DISTANCE * Math.sin(angle));
        if (tx > sx && ty < sy) { // target is upper-right side
            dy = -dy; // dx > 0, dx < 0
        } else if (tx < sx && ty < sy) { // target is upper-left side
            dx = -dx;
            dy = -dy;
        } else if (tx < sx && ty > sy) { // target is lower-left side
            dx = -dx;
        } else { // target is lower-right side
            // dx > 0 , dy > 0
        }
    }
    @Override
    public void stateChange(){
        if (state instanceof StateShot)
            state = new StateHit();
        if (size >= SIZE * 10){
            state = new StateDone();
        }
    }
    @Override
    public void render(Graphics2D g, int sz) {
        g.setColor(color);
        g.drawOval((int) (x - size / 2),
                (int) (y - size / 2),
                size, size);
    }
    @Override
    public void update() {
        updateState();
        if (state instanceof StateShot) {
            updateLocation();
        } else if (state instanceof StateHit) {
            updateSize();
        }
    }
    public void updateLocation() {
        x += dx;
        y += dy;
    }
    public void updateSize() {
        size += 2;
    }
    public void updateState() {
        if (state instanceof StateShot) {
            double distance = target.distance(x,y);
            targetReached = distance <= 2.0;
            if (targetReached) {
                stateChange();
            }
        } else if (state instanceof StateHit) {
            if (size >= SIZE * 10) {
                stateChange();
            }
        }
    }
    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Double collisionBox;
        collisionBox = new Rectangle2D.Double(x - (0.9 * size) / 2.0 , y - (0.9 * size) / 2.0,
                                             (0.9 * size), (0.9 * size));
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