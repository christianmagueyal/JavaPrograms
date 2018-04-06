package view;

import controller.ButtonListener;
import controller.KeyController;
import controller.Main;
import controller.MouseController;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

    JPanel messagePanel = new JPanel( new GridLayout(2,1));
    public static JTextField gameStatistics;
    public static JTextField enemyStatistics;
    public static JButton quitButton;

    public MainWindow() {

        Container c = getContentPane();
        gameStatistics = new JTextField("Score:");
        gameStatistics.setFont(new Font("Courier New", Font.BOLD, 16));
        gameStatistics.setEditable(false);
        messagePanel.add(gameStatistics);

        enemyStatistics = new JTextField("Enemy Stats:");
        enemyStatistics.setFont(new Font("Courier New", Font.BOLD, 16));
        enemyStatistics.setEditable(false);
        messagePanel.add(enemyStatistics);
        
        c.add(messagePanel, "North");
        c.add(Main.gamePanel, "Center");
        
        JPanel southPanel = new JPanel();
        quitButton = new JButton("Quit");
        southPanel.add(quitButton);
        
        c.add(southPanel, "South");
        
        ButtonListener buttonListener = new ButtonListener();
        quitButton.addActionListener(buttonListener);
        
        MouseController mouseController = new MouseController();
        Main.gamePanel.addMouseListener(mouseController);
        Main.gamePanel.addMouseMotionListener(mouseController);
        
        KeyController keyListener = new KeyController();
        Main.gamePanel.addKeyListener(keyListener);
        Main.gamePanel.setFocusable(true);
        
        quitButton.setFocusable(false);
        gameStatistics.setFocusable(false);
        enemyStatistics.setFocusable(false);
    }
}