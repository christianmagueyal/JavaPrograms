package controller;

import javax.swing.JFrame;
import model.GameData;
import view.GamePanel;
import view.MainWindow;

public class Main {

    public static GamePanel gamePanel;
    public static GameData gameData;
    public static Animator animator;
    public static int WIN_WIDTH = 500;
    public static int WIN_HEIGHT = 400;
    
    public static void main(String[] args) {
        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel();
        JFrame game = new MainWindow();
        game.setTitle("Term_Project_Christian_M");
        game.setSize(WIN_WIDTH, WIN_HEIGHT);
        game.setLocation(100, 0);
        game.setResizable(false);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        new Thread(animator).start();
    }
}