package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import model.Laser;
import model.Missile;
import model.PowerfulLaser;
import model.Shooter;
import model.Weapon;

public class MouseController extends MouseInputAdapter  {

    public int px;
    public int py;

    @Override
    public void mouseMoved(MouseEvent e) {
        Main.gameData.mx = e.getX();
        Main.gameData.my = e.getY();
    }
    @Override
    public void mousePressed(MouseEvent me) {
        px = me.getX();
        py = me.getY();
        Shooter shooter = Main.gameData.tank;
        if(shooter.weapon == 0){
            Weapon m = new Missile(shooter.getXofMissileShoot(),
                                   shooter.getYofMissileShoot(), px, py);
            synchronized (Main.gameData.weapons) {
                Main.gameData.weapons.add(m);
            }
        }
        if(shooter.weapon == 1){
            Weapon l = new Laser(shooter.getXofMissileShoot(),
                                 shooter.getYofMissileShoot(), px, py);
            if(Main.gameData.upgrading)
                l = new PowerfulLaser(l);
            synchronized (Main.gameData.weapons) {
                Main.gameData.weapons.add(l);
            }
        }
    }
}
