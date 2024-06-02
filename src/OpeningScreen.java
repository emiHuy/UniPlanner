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
    private JButton toRegisterButton;
    private JTextField createUsernameField;
    private JPasswordField passwordField;
    private JPasswordField createPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton createAccountButton;
    private JButton loginToMenuButton;
    private JTextField usernameField;
    private JTextField nameField;
    private JButton toLoginButton;

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
        toRegisterButton.addActionListener(this);
        loginToMenuButton.addActionListener(this);
    }

    private void registerScreen(){
        createAccountButton.addActionListener(this);
        toLoginButton.addActionListener(this);
    }

    private boolean checkRegisterInfo(String name, String username, String password, String confirmPassword){
        if(!name.matches("[a-zA-Z]+")){
            System.out.println("Name can only consist of alphabetical characters.");
            return false;
        }
        else if(!username.matches("[a-zA-Z]+")){
            System.out.println("Username can only consist of alphabetical characters.");
            return false;
        }
        else if(password.length() > 12 || password.length() < 6){
            System.out.println("Password must be from 6 to 12 characters long.");
            return false;
        }
        else if(!password.equals(confirmPassword)){
            System.out.println("Password and confirm password entries do not match.");
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == loginButton || e.getSource() == toLoginButton){
            openingScreen.setVisible(false);
            registerScreen.setVisible(false);
            loginScreen.setVisible(true);
        }
        else if(e.getSource() == registerButton || e.getSource() == toRegisterButton){
            openingScreen.setVisible(false);
            loginScreen.setVisible(false);
            registerScreen.setVisible(true);
        }
        else if(e.getSource() == loginToMenuButton){
            String username = usernameField.getText();
            String password = passwordField.getText();
        }
        else if(e.getSource() == createAccountButton){
            String name = nameField.getText();
            String username = createUsernameField.getText();
            String password = createPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            boolean valid = checkRegisterInfo(name, username, password, confirmPassword);
        }
    }
}
