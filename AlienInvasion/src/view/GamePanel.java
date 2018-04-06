package view;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import model.GameFigure;
import model.StateShielded;
import model.Weapon;

public class GamePanel extends JPanel {
    // size of the canvas - determined at runtime once rendered
    public static int width;
    public static int height;
    // off screen rendering
    private Graphics2D g2;
    private Image dbImage = null; // double buffer image

    public void gameRender() {
        width = getSize().width;
        height = getSize().height;
        if (dbImage == null) {
            // Creates an off-screen drawable image to be used for double buffering
            dbImage = createImage(width, height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            } else {
                g2 = (Graphics2D) dbImage.getGraphics();
            }
        }
        g2.clearRect(0, 0, width, height);
        g2.setBackground(Color.BLACK);
        if (Main.animator.running) {
            synchronized (Main.gameData.enemyFigures) {
                for (GameFigure f : Main.gameData.enemyFigures) 
                    f.render(g2);
            }
            synchronized(Main.gameData.weapons){
                for (Weapon w : Main.gameData.weapons)
                    w.render(g2,w.getSize());
                Main.gameData.tank.render(g2);
            }
        
        }
        if (!Main.animator.running && Main.gameData.shieldPower < 1) {
            synchronized (Main.gameData.weapons) {
                for (Weapon w : Main.gameData.weapons) {
                    w.render(g2, w.getSize());
                }
                
            }
            synchronized(Main.gameData.enemyFigures){
                for(GameFigure e : Main.gameData.enemyFigures){
                    e.render(g2);
                }
            }
            Main.gameData.tank.render(g2);
        }
        if (!Main.animator.running && Main.gameData.shieldPower > 1) {
            synchronized (Main.gameData.enemyFigures) {
                for (GameFigure f : Main.gameData.enemyFigures) {
                    f.render(g2);
                }
            }
        }
    }
    public void printScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
        if(Main.animator.running){
            if(Main.gameData.pointsTillUpgrade >=0 )
                MainWindow.gameStatistics.setText("Score: " + Main.gameData.gamePoints + 
                                           "  Upgrading in: "+ Main.gameData.pointsTillUpgrade);
            else
                MainWindow.gameStatistics.setText("Score: " + Main.gameData.gamePoints);
            if(Main.gameData.enemyFigures.get(0).state instanceof StateShielded)
                MainWindow.enemyStatistics.setText("Shield Power: " + (Main.gameData.shieldPower - 300)
                                                   + " Boss Health: " + Main.gameData.bossHealth);
            else
                MainWindow.enemyStatistics.setText("Shield Destroyed! Boss Health: "
                                                   + Main.gameData.bossHealth);

        }
        if(!Main.animator.running){
            if(Main.gameData.shieldPower > 1)
                MainWindow.gameStatistics.setText("Game Over.    Final Score = " + Main.gameData.gamePoints);
            else if(Main.gameData.shieldPower < 1){
                MainWindow.gameStatistics.setText("You Won!!!!!  Final Score = " + Main.gameData.gamePoints);
                MainWindow.enemyStatistics.setText("Boss destroyed!");
            }
        }
    }
}