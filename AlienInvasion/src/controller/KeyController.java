package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Shooter;

public class KeyController implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        Shooter shooter = Main.gameData.tank;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                shooter.left = true;
                break;
            case KeyEvent.VK_RIGHT:
                shooter.right = true;
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_SHIFT:
                shooter.toggleWeapon();
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        Shooter shooter = Main.gameData.tank;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                shooter.left = false;
                break;
            case KeyEvent.VK_RIGHT:
                shooter.right = false;
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
        }
    }
}
