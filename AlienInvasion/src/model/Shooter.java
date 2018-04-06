package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.GamePanel;

public class Shooter extends GameFigure{

    public Image launcherImage;
    public Weapon gun;
    private final int MISSILE = 0;
    private final int LASER = 1;
    public Rectangle2D.Double barrel;
    public int mouseX;
    public int mouseY;
    public boolean left = false;
    public boolean right = false;
    public int weapon;
    public double angle;
    public int size = 35;
    public int barrelLength = 18;
    public int centerX;
    public int centerY;

    public Shooter(int x, int y) {
        super(x, y);
        this.weapon = 0;
        launcherImage = null;
        try {
            launcherImage = ImageIO.read(getClass().getResource("Tank.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open tank you.png");
            System.exit(-1);
        }
    }
    @Override
    public void render(Graphics2D g) {
        g.drawImage(launcherImage, (int)super.x, (int)super.y, 
                size, size, null);
        barrel = new Rectangle2D.Double(super.x+13, super.y+11,18, 3);
        centerX = (int) barrel.x;
        centerY = (int) barrel.y;
        angle = ( Math.atan2(centerY - mouseY, centerX - mouseX) - Math.PI);
        ((Graphics2D)g).rotate(angle, centerX, centerY);
        g.setColor(Color.green);
        g.fill(barrel);
        ((Graphics2D)g).rotate(-angle, centerX, centerY);
    }
    @Override
    public void update() {
        translate(7, 0);
        mouseX = Main.gameData.mx;
        mouseY = Main.gameData.my;
    }
    public void translate(int dx, int dy) {
        if(left)
            if(super.x > 0)

                super.x -= dx;
        if(right)
            if(super.x < GamePanel.width - size)
                super.x += dx;
    }
    public void toggleWeapon(){
        if(weapon == 0)
            weapon = LASER;
        else if(weapon == 1)
            weapon = MISSILE;
    }
    public float getXofMissileShoot() {
        return (float) (centerX + (barrelLength * Math.cos(angle)));
    }
    
    public float getYofMissileShoot() {
        return (float) (centerY + (barrelLength * Math.sin(angle)));
    }
    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Double collisionBox;
        collisionBox= new Rectangle2D.Double(super.x ,
                                             super.y + 13 ,
                                             size*0.9, size * 0.6);
        return collisionBox;
    }
}