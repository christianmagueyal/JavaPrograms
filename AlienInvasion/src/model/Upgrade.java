package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class Upgrade implements  Weapon{

    protected Weapon gun;
    
    public Upgrade(Weapon g){
        this.gun = g;
    }
    @Override
    public Rectangle2D getCollisionBox(){
        return this.gun.getCollisionBox();
    }
    @Override
    public int getDamagePoints(){
        return this.gun.getDamagePoints();
    }
    @Override
    public int getSize(){
        return this.gun.getSize();
    }
    @Override
    public void render(Graphics2D g, int sz) {
        this.gun.render(g, this.getSize());
    }
    @Override
    public void update() {
        this.gun.update();
    }
    @Override
    public void stateChange() {
        this.gun.stateChange();
    }
    @Override
    public void setState(State s){
        this.gun.setState(s);
    }
    @Override
    public State getState() {
        return this.gun.getState();
    }
}
