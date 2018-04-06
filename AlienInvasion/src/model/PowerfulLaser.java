
package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class PowerfulLaser extends Upgrade{

    public PowerfulLaser(Weapon g) {
        super(g);
    }
    @Override
    public int getDamagePoints() {
        return super.getDamagePoints() * powerBoost();
    }
    private int powerBoost() {
        return 10;
    }
    @Override
    public Rectangle2D getCollisionBox() {
        return super.getCollisionBox();
    }
    @Override
    public void render(Graphics2D g, int sz) {
        
        super.render(g, this.getSize());
    }
    @Override
    public void update() {
        super.update();
    }
    @Override
    public void stateChange() {
        super.stateChange();
    }
    @Override
    public State getState() {
        return super.getState();
    }
    @Override
    public int getSize(){
        return super.getSize() * sizeBoost();
    }
    private int sizeBoost() {
        return 2;
    }
}
