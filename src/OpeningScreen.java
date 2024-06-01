import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpeningScreen extends JFrame implements ActionListener{
    private JFrame display;
    private JPanel startPanel;
    private JPanel openingScreen;
    private JPanel loginScreen;
    private JPanel registerScreen;
    private JButton loginButton;
    private JButton registerButton;
    private JButton registerButton2;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPasswordField passwordField3;
    private JButton createAccountButton;

    public OpeningScreen(){
        intialize();
        openingScreen();
        loginScreen();
        registerScreen();
    }

    private void intialize(){
        display = new JFrame();
        display.setSize(900, 1000);
        display.add(startPanel);
        display.setContentPane(startPanel);
        display.setVisible(true);
        display.setTitle("MnM Uni Planner");
        display.setLocationRelativeTo(null);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void openingScreen(){
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    private void loginScreen(){
        registerButton2.addActionListener(this);
    }

    private void registerScreen(){
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == loginButton){
            openingScreen.setVisible(false);
            loginScreen.setVisible(true);
        }
        else if(e.getSource() == registerButton || e.getSource() == registerButton2){
            openingScreen.setVisible(false);
            loginScreen.setVisible(false);
            registerScreen.setVisible(true);
        }
    }
}
