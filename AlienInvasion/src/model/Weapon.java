package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface Weapon{
    State getState();
    void setState(State s);
    int getDamagePoints();
    int getSize();
    void render(Graphics2D g, int sz);
    void update();
    void stateChange();
    Rectangle2D getCollisionBox();
}