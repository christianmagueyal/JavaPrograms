package model;

import controller.Main;
import view.GamePanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameData {

    public int mx;
    public int my;
    public boolean upgrading = false;
    public int gamePoints = 0;
    private long lastExecution = 0;
    public int shieldPower = 1300;
    public int totalUfos = 2;
    public int killedUfos = 0;
    public int totalBombs = 0;
    public int killedBombs = 0;
    public int newUfos = 1;
    public int newBombs = 7;
    private final int RADIUS = 6;
    public final List<Weapon> weapons;
    public final List<GameFigure> enemyFigures;
    public Shooter tank;
    public int pointsTillUpgrade = 1000 - gamePoints;
    public int bossHealth = 300;

    public GameData() {
        enemyFigures = Collections.synchronizedList(new ArrayList<GameFigure>());
        weapons = Collections.synchronizedList(new ArrayList<Weapon>());
        // GamePanel.width, height are known when rendered. 
        // Thus, at this moment,
        // we cannot use GamePanel.width and height.
        tank = new Shooter(Main.WIN_WIDTH/2, Main.WIN_HEIGHT - 150);
        enemyFigures.add(new Boss(100, 0));
        enemyFigures.add(new FlyingSaucer(100, 60));
        enemyFigures.add(new FlyingSaucer(400, 40));
    }
    public void addUfo(int n){
        synchronized (enemyFigures){
            totalUfos += n;
            for (int i = 0; i < n; i++){
                enemyFigures.add(new FlyingSaucer((int)Math.floor(Math.random()
                                * (GamePanel.width)), 30 + (int)Math.floor(Math.random()
                                * ((200 - 30) + 1))));
            }
        }
    }
    public void addBomb(int n) {
        synchronized (enemyFigures) {
            totalBombs += n;
            for (int i = 0; i < n; i++) {
                float red = (float) Math.random();
                float green = (float) Math.random();
                float blue = (float) Math.random();
                // adjust if too dark since the background is black
                if (red < 0.5) {
                    red += 0.2;
                }
                if (green < 0.5) {
                    green += 0.2;
                }
                if (blue < 0.5) {
                    blue += 0.2;
                }
                enemyFigures.add(new Bomb(
                        (int) (Math.random() * GamePanel.width),
                        (int) (Math.random() * GamePanel.height) / 2,
                        RADIUS,
                        new Color(red, green, blue)));
            }
        }
    }

    public void update() {
        if(shieldPower < 1 && upgrading){
            Main.animator.running = false;
            enemyFigures.subList(1, enemyFigures.size()).clear();
            enemyFigures.get(0).state.nextState(enemyFigures.get(0));
            upgrading = false;
        }
        if(gamePoints > 1000 && Main.animator.running){
            upgrading = true;
        }
        if(Main.animator.running){
        synchronized (enemyFigures) {
            ArrayList<GameFigure> remove = new ArrayList<>();
            GameFigure f;
            for (int i = 0; i < enemyFigures.size(); i++) {
                f = enemyFigures.get(i);
                if (f.state instanceof StateDone) {
                    remove.add(f);
                    if(f instanceof Bomb)
                        Main.gameData.killedBombs++;
                    else if(f instanceof FlyingSaucer)
                        Main.gameData.killedUfos++;
                    gamePoints += f.pointValue;
                }
            }
            enemyFigures.removeAll(remove);
            if (totalUfos == killedUfos && totalBombs == killedBombs){
                newUfos += 2;
                newBombs += 4;
                addBomb(newBombs);
                addUfo(newUfos);
            }
            for (GameFigure g : enemyFigures) {
                    g.update();
                }
            }
        }
        synchronized (weapons) {
            ArrayList<Weapon> remove = new ArrayList<>();
            Weapon w;
            for (int i = 0; i < weapons.size(); i++) {
                w = weapons.get(i);
                if (w.getState() instanceof StateDone) {
                    remove.add(w);
                }
            }
            weapons.removeAll(remove);
            for (Weapon g : weapons) {
                g.update();
            }
        }
        tank.update();
        pointsTillUpgrade = 1000 - gamePoints;
    }
}
