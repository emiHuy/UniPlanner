import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame implements ActionListener{
    private JPanel homePanel;
    private JButton classesButton;
    private JButton calendarButton;
    private JButton studyGuideButton;
    private JButton xButton;
    private JLabel welcomeLabel;
    private JButton classesIconButton;
    private JButton calendarIconButton;
    private JButton studyGuideIconButton;
    public static Account userAccount;

    public HomeScreen(Account userAccount) {
        this.userAccount = userAccount;
        initializeFrame();
        createButtons();
        addActionListeners();
        welcome();
        windowListener();
    }

    private void initializeFrame(){
        add(homePanel);
        setContentPane(homePanel);
        setVisible(true);
        setTitle("MnM Uni Planner");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void createButtons(){
        classesIconButton.setIcon(new ImageIcon("classes icon.png"));
        calendarIconButton.setIcon(new ImageIcon("calendar icon.png"));
        studyGuideIconButton.setIcon(new ImageIcon("study guide icon.png"));

        classesIconButton.setBorder(new EmptyBorder(0,0,50,0));
        calendarIconButton.setBorder(new EmptyBorder(0,0,50,0));
        studyGuideIconButton.setBorder(new EmptyBorder(0,0,50,0));
        classesButton.setBorder(new EmptyBorder(0,0,0,0));
        calendarButton.setBorder(new EmptyBorder(0,0,0,0));
        studyGuideButton.setBorder(new EmptyBorder(0,0,0,0));

        classesIconButton.setFocusPainted(false);
        calendarIconButton.setFocusPainted(false);
        studyGuideIconButton.setFocusPainted(false);
        classesButton.setFocusPainted(false);
        calendarButton.setFocusPainted(false);
        studyGuideButton.setFocusPainted(false);

        classesIconButton.setContentAreaFilled(false);
        calendarIconButton.setContentAreaFilled(false);
        studyGuideIconButton.setContentAreaFilled(false);
        classesButton.setContentAreaFilled(false);
        calendarButton.setContentAreaFilled(false);
        studyGuideButton.setContentAreaFilled(false);
    }

    private void addActionListeners(){
        classesButton.addActionListener(this);
        calendarButton.addActionListener(this);
        studyGuideButton.addActionListener(this);
        classesIconButton.addActionListener(this);
        calendarIconButton.addActionListener(this);
        studyGuideIconButton.addActionListener(this);
    }

    private void welcome(){
        welcomeLabel.setText("Hello, " + userAccount.getName());
    }

    private void windowListener(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 27));
                UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 27));
                // Ask user to confirm exit when clicking exit button
                int exitResponse = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(exitResponse == JOptionPane.YES_OPTION){
                    // If user confirms exit, save data before closing window
                    FileOperations.saveData();
                    dispose();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == classesButton || e.getSource() == classesIconButton){
            // Go to ClassesScreen
            new ClassesScreen();
            dispose();
        }
        else if(e.getSource() == calendarButton || e.getSource() == calendarIconButton){
            // Go to Calendar screen
            new Calendar(userAccount.getCalendarData());
            dispose();
        }
        else if(e.getSource() == studyGuideButton || e.getSource() == studyGuideIconButton){
            //Go to StudyGuide screen
            new StudyGuide();
            dispose();
        }
    }
}