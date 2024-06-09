import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class OpeningScreen extends JFrame implements ActionListener{
    private JFrame display = new JFrame();
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

    // Set up JFrame/Window
    private void intialize(){
        display = new JFrame();
        display.setSize(1000, 1100);
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
        toRegisterButton.setBorder(new EmptyBorder(0,0,0,0));
    }

    private void registerScreen(){
        createAccountButton.addActionListener(this);
        toLoginButton.addActionListener(this);
        toLoginButton.setBorder(new EmptyBorder(0,0,0,0));
    }

    // Returns false if any registering input is invalid
    private boolean checkRegisterInfo(String name, String username, String password, String confirmPassword){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
        UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 24));
        if(name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            JOptionPane.showMessageDialog(display, "Please fill out all fields.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(!name.matches("[a-zA-Z]+")){
            JOptionPane.showMessageDialog(display, "Name can only consist of alphabetical characters.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(!username.matches("[a-zA-Z]+")){
            JOptionPane.showMessageDialog(display, "Username can only consist of alphabetical characters.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(password.length() > 12 || password.length() < 6){
            JOptionPane.showMessageDialog(display, "Password must be from 6 to 12 characters long.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(display, "Password and confirm password fields do not match.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == loginButton || e.getSource() == toLoginButton){
            // Displays login screen
            openingScreen.setVisible(false);
            registerScreen.setVisible(false);
            loginScreen.setVisible(true);
        }
        else if(e.getSource() == registerButton || e.getSource() == toRegisterButton){
            // Displays register screen
            openingScreen.setVisible(false);
            loginScreen.setVisible(false);
            registerScreen.setVisible(true);
        }
        else if(e.getSource() == loginToMenuButton){
            String username = usernameField.getText();
            String password = passwordField.getText();
            new homeScreen();
            display.dispose();
            // proceed to home screen after checking existing files
        }
        else if(e.getSource() == createAccountButton){
            String name = nameField.getText();
            String username = createUsernameField.getText();
            String password = createPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            boolean valid = checkRegisterInfo(name, username, password, confirmPassword);
            if(valid){
                new homeScreen();
                display.dispose();
            }
        }
    }
}
