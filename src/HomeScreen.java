import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class HomeScreen implements ActionListener{
    private JFrame window;
    private JPanel homePanel;
    private JButton classesButton;
    private JButton calendarButton;
    private JButton studyGuideButton;
    private JButton xButton;
    private JLabel welcomeLabel;
    public static Account userAccount;

    public HomeScreen(Account userAccount) {
        this.userAccount = userAccount;
        initializeFrame();
        addActionListeners();
        welcome();
        windowListener();
    }

    private void initializeFrame(){
        window = new JFrame();
        window.add(homePanel);
        window.setContentPane(homePanel);
        window.setVisible(true);
        window.setTitle("MnM Uni Planner");
        window.setSize(1920, 1080);
        window.setLocationRelativeTo(null);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void addActionListeners(){
        classesButton.addActionListener(this);
        calendarButton.addActionListener(this);
        studyGuideButton.addActionListener(this);
    }

    private void welcome(){
        welcomeLabel.setText("Hello, " + userAccount.getName());
    }

    private void windowListener(){
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
                UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 24));
                // Ask user to confirm exit when clicking exit button
                int exitResponse = JOptionPane.showConfirmDialog(window, "Are you sure you want to quit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(exitResponse == JOptionPane.YES_OPTION){
                    // If user confirms exit, save data before closing window
                    FileOperations.saveData();
                    window.dispose();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == classesButton){
            // Go to ClassesScreen
            new ClassesScreen();
            window.dispose();
        }
        else if(e.getSource() == calendarButton){
            // Go to Calendar screen
            new Calendar(userAccount.getCalendarData());
            window.dispose();
        }
        else if(e.getSource() == studyGuideButton){
            //Go to StudyGuide screen
            new StudyGuide();
            window.dispose();
        }
    }
}


