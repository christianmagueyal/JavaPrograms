
package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Boss extends GameFigure{
    private Image launcherImage;
    private Image shield;
    public Animate animate =  new TeleportNormal();
    private int size = 40; 

    public Boss(float x, float y){
        super(x, y);
        super.state = new StateShielded();
        super.pointValue = 0;
        launcherImage = null;
        try {
            launcherImage = ImageIO.read(getClass().getResource("UFO.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open UFO.png");
            System.exit(-1);
        }
        super.pointValue = 0;
        shield = null;
        try {
            shield = ImageIO.read(getClass().getResource("Ring.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open Ring.png");
            System.exit(-1);
        }
    }
    @Override
    public void render(Graphics2D g) {
        if(!(state instanceof StateDestroyed))
            g.drawImage(launcherImage, (int)super.x, (int)super.y, size, size, null);
        else if(size >0){
            g.rotate(size, x+size/2, y+size/2);
            g.drawImage(launcherImage, (int)super.x, (int)super.y, size, size, null);
            g.rotate(-size, x+size/2, y+size/2);
            size-- ;
        }
        if(state instanceof StateShielded)
            g.drawImage(shield, (int)super.x, (int)super.y, size, size, null);
    }
    @Override
    public void update() {
        super.x = animate.teleport(super.x);
            updateAnimate();
    }
    public void updateAnimate(){
        if(state instanceof StateVulnerable && animate instanceof TeleportNormal)
            animate = new TeleportFast();
    }
    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Double collisionBox;
        if(state instanceof StateShielded)
            collisionBox = new Rectangle2D.Double(super.x +2, super.y+ 3, size* 0.9, 37 * 0.9);
        else
            collisionBox = new Rectangle2D.Double(super.x +5, super.y + 11, 35* 0.9, 20 * 0.8);
        return collisionBox;   
    }
}