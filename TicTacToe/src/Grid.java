
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Grid extends JFrame {
    boolean win = false;
    byte counter = 0;
    String letter;
    JButton button1, button2, button3, button4,
            button5, button6, button7, button8,
            button9;
    JTextField textField = new JTextField();
    Font font1 = new Font("Courier New", Font.BOLD, 18);
    Font font2 = new Font("Courier New", Font.BOLD, 56);

    public Grid() {
        Container pane = getContentPane();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        pane.add(textField, "North");
        textField.setFont(font1);
        textField.setText("Play Tic-TacToe: O's turn!");
        pane.add(panel, "Center");
        button1 = new JButton("");
        button2 = new JButton("");
        button3 = new JButton("");
        button4 = new JButton("");
        button5 = new JButton("");
        button6 = new JButton("");
        button7 = new JButton("");
        button8 = new JButton("");
        button9 = new JButton("");

        ButtonObserver observer = new ButtonObserver();
        button1.addActionListener(observer);
        button2.addActionListener(observer);
        button3.addActionListener(observer);
        button4.addActionListener(observer);
        button5.addActionListener(observer);
        button6.addActionListener(observer);
        button7.addActionListener(observer);
        button8.addActionListener(observer);
        button9.addActionListener(observer);
        
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);       
    }
    class ButtonObserver implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(counter%2 == 0){
                letter = "O";
            }
            else if(counter%2 == 1){
                letter = "X";
            }
            
            if(letter == "O") textField.setText("X's Turn");
            else if(letter == "X") textField.setText("O's Turn");

            if (source == button1) {
                button1.setFont(font2);
                button1.setText(letter);
                button1.setEnabled(false);
            } else if (source == button2) {
                button2.setFont(font2);
                button2.setText(letter);
                button2.setEnabled(false);
            } else if (source == button3) {
                button3.setFont(font2);
                button3.setText(letter);
                button3.setEnabled(false);
            } else if (source == button4) {
                button4.setFont(font2);
                button4.setText(letter);
                button4.setEnabled(false);
            } else if (source == button5) {
                button5.setFont(font2);
                button5.setText(letter);
                button5.setEnabled(false);
            } else if (source == button6) {
                button6.setFont(font2);
                button6.setText(letter);
                button6.setEnabled(false);
            } else if (source == button7) {
                button7.setFont(font2); 
                button7.setText(letter);
                button7.setEnabled(false);
            } else if (source == button8) {
                button8.setFont(font2);
                button8.setText(letter);
                button8.setEnabled(false);
            } else if (source == button9) {
                button9.setFont(font2);
                button9.setText(letter);
                button9.setEnabled(false);
            } else {
                System.out.println("It's time to buy a new computer!");
            }
            //Checking for horizontal wins.
            if(button1.getText() == button2.getText() && button2.getText() ==
               button3.getText() && button1.getText() != "") {
               win = true;
            } else if(button4.getText() == button5.getText() &&
                      button5.getText() == button6.getText() &&
                      button4.getText() != "") {
               win = true;
            } else if(button7.getText() == button8.getText() &&
                      button8.getText() == button9.getText() &&
                      button7.getText() != "") {
               win = true;
            }
            //Checking for vertical wins.
            else if(button1.getText() == button4.getText() &&
                    button4.getText() == button7.getText() &&
                    button1.getText() != "") {
               win = true;
            } else if(button2.getText() == button5.getText() &&
                      button5.getText() == button8.getText() &&
                      button2.getText() != "") {
               win = true;
            } else if(button3.getText() == button6.getText() &&
                      button6.getText() == button9.getText() &&
                      button3.getText() != "") {
               win = true;
            }
            //Checking for diagonal wins.
            else if(button1.getText() == button5.getText() &&
                    button5.getText() == button9.getText() &&
                    button1.getText() != "") {
               win = true;
            } else if(button3.getText() == button5.getText() &&
                      button5.getText() == button7.getText() &&
                      button3.getText() != "") {
               win = true;
            } else{
               win = false;
            }
            if(win == true){
               textField.setText("Game Over: " + letter + " Wins!!!");
               button1.setEnabled(false);
               button2.setEnabled(false);
               button3.setEnabled(false);
               button4.setEnabled(false);
               button5.setEnabled(false);
               button6.setEnabled(false);
               button7.setEnabled(false);
               button8.setEnabled(false);
               button9.setEnabled(false);
            }
            //checking for draw:
            if(counter == 8 && win != true){
               textField.setText("Game Over: Draw - No Winner");
            }
            counter++;
        }
    }
}
