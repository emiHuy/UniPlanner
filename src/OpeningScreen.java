import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.EmptyBorder;

public class OpeningScreen extends JFrame implements ActionListener{
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
        intializeFrame();
        openingScreen();
        loginScreen();
        registerScreen();
        FileOperations.readAccountsList();
    }

    private void intializeFrame(){
        setSize(950, 1100);
        add(startPanel);
        setContentPane(startPanel);
        setVisible(true);
        setTitle("MnM Uni Planner");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void openingScreen(){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 27));
        UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 27));

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    private void loginScreen(){
        toRegisterButton.addActionListener(this);
        loginToMenuButton.addActionListener(this);
        toRegisterButton.setBorder(new EmptyBorder(0,0,0,0));
        toRegisterButton.setContentAreaFilled(false);
        toRegisterButton.setFocusPainted(false);
    }

    private void registerScreen(){
        createAccountButton.addActionListener(this);
        toLoginButton.addActionListener(this);
        toLoginButton.setBorder(new EmptyBorder(0,0,0,0));
        toLoginButton.setContentAreaFilled(false);
        toLoginButton.setFocusPainted(false);
    }

    // Returns false if any registering input is invalid
    private boolean checkRegisterInfo(String name, String username, String password, String confirmPassword){
        for(Account account: Account.getAccountsList()){
            if(username.equals(account.getUsername())){
                JOptionPane.showMessageDialog(this, "Account already exists. Pick a different username.", "Input Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        // Check if any fields are empty
        if(name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
            // Check if name length is invalid
        else if(name.length() > 12 || name.length() < 1 ){
                JOptionPane.showMessageDialog(this, "Name must be from 1 to 12 characters long.", "Input Warning", JOptionPane.WARNING_MESSAGE);
                return false;
        }
        // Check if name is not alphabetical
        else if(!name.matches("[a-zA-Z]+")){
            JOptionPane.showMessageDialog(this, "Name can only consist of alphabetical characters.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // Check if username length is invalid
        else if(username.length() > 12 || username.length() < 6) {
            JOptionPane.showMessageDialog(this, "Username must be from 6 to 12 characters long.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // Check if username contains special characters
        else if(!username.matches("[a-zA-Z0-9]+")){
            JOptionPane.showMessageDialog(this, "Username can only consist of alphabetical and numerical characters.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // Check if password length is invalid
        else if(password.length() > 12 || password.length() < 6){
            JOptionPane.showMessageDialog(this, "Password must be from 6 to 12 characters long.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // Check if password does not match confirmed password
        else if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this, "Password and confirm password fields do not match.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private Account checkLoginInfo(String username, String password){
        ArrayList<Account> accountsList = Account.getAccountsList();
        // Check if login information matched any existing account
        for(Account account: accountsList){
            if(username.equals(account.getUsername()) && password.equals(account.getPassword())){
                return account;
            }
        }
        // At this point, login information did not match any existing accounts
        return null;
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
            // Collect login info from fields
            String username = usernameField.getText();
            String password = passwordField.getText();
            // Check login info
            Account userAccount = checkLoginInfo(username, password);

            if(username.isEmpty() || password.isEmpty()){
                // Display input error message
                JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            }
            // If valid,
            else if(userAccount != null){
                // read and retrieve user's account data from the saved file
                FileOperations.readAccountData(userAccount);
                // Go to home screen
                new HomeScreen(userAccount);
                dispose();
            }
            else{
                // Display input error message
                JOptionPane.showMessageDialog(this, "Incorrect username or password.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(e.getSource() == createAccountButton){
            // Collect register info from fields
            String name = nameField.getText();
            String username = createUsernameField.getText();
            String password = createPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            // Check register info validity
            boolean valid = checkRegisterInfo(name, username, password, confirmPassword);
            if(valid){
                // If valid, create new Account object with register info
                Account userAccount = new Account(username, password, name);
                // Go to home screen
                initializeCalendarData(userAccount);
                new HomeScreen(userAccount);
                dispose();
            }
        }
    }

    public void initializeCalendarData(Account account){
        Map<String, String> calendarData = new HashMap<>();
        calendarData.put("TextArea1", "");
        calendarData.put("TextArea2", "");
        calendarData.put("TextArea3", "");
        calendarData.put("TextArea4", "");
        calendarData.put("TextArea5", "");
        calendarData.put("TextArea6", "");
        calendarData.put("TextArea7", "");
        calendarData.put("TextArea8", "");
        calendarData.put("TextArea9", "");
        calendarData.put("TextArea10", "");
        calendarData.put("TextArea11", "");
        calendarData.put("TextArea12", "");
        calendarData.put("TextArea13", "");
        calendarData.put("TextArea14", "");
        account.updateCalendarData(calendarData);
    }
}