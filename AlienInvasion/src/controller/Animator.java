package controller;

import model.Boss;
import model.GameFigure;
import model.StateBouncing;
import model.StateFlying;
import model.StateShielded;
import model.StateShot;
import model.Weapon;

public class Animator implements Runnable {

    public boolean running = true;
    public boolean start = false;
    private final int FRAMES_PER_SECOND = 20;

    @Override
    public void run() {
        while (running) {
            long startTime = System.currentTimeMillis();
            processCollisions();
            Main.gameData.update();
            Main.gamePanel.gameRender();
            Main.gamePanel.printScreen();
            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); // ms
                } catch (InterruptedException e) {}
            }
        }
        while (!running && Main.gameData.shieldPower < 1) {
            long startTime = System.currentTimeMillis();
            Main.gameData.update(); 
            Main.gamePanel.gameRender();
            Main.gamePanel.printScreen();
            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); // ms
                }
                catch (InterruptedException e) {}
            }
        }
        if (!running && Main.gameData.bossHealth > 1) {
            long startTime = System.currentTimeMillis();
            Main.gamePanel.gameRender();
            Main.gamePanel.printScreen();
            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); // ms
                }
                catch (InterruptedException e) {}
            }
        }
    }
    private synchronized void processCollisions() {
        for (GameFigure enemyFigure : Main.gameData.enemyFigures) {
                    if(enemyFigure.getCollisionBox().intersects(Main.gameData.tank.getCollisionBox())){
                        enemyFigure.state.nextState(enemyFigure);
                        running = false;
                    }
            for (Weapon w : Main.gameData.weapons) {
                if (w.getCollisionBox().intersects(enemyFigure.getCollisionBox())) {
                    if(!(enemyFigure instanceof Boss) &&
                         enemyFigure.state instanceof StateBouncing ||
                         enemyFigure.state instanceof StateFlying)
                    {
                        enemyFigure.state.nextState(enemyFigure);
                        if(w.getState() instanceof StateShot)
                            w.stateChange();
                    }
                    if(enemyFigure instanceof Boss){
                        Main.gameData.shieldPower -= w.getDamagePoints();
                        w.stateChange();
                        w.stateChange();
                        if(Main.gameData.shieldPower <= 300 && enemyFigure.state
                           instanceof StateShielded)
                            enemyFigure.state.nextState(enemyFigure);
                        else if(Main.gameData.shieldPower <=300){
                            Main.gameData.bossHealth -= w.getDamagePoints();
                        }
                    }    
                }
            }
        }
    }
}