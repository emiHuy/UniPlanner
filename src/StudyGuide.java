import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudyGuide extends JFrame implements ActionListener{
    private JPanel studyGuide;
    private JButton essayButton;
    private JButton projectButton;
    private JButton subjectButton;
    private JButton financeButton;
    private JTabbedPane tabbedPane1;
    private JButton backButton;
    private JLabel imageLabel;

    public StudyGuide() {
        initializeFrame();
        addActionListeners();
        windowListener();
    }

    private void initializeFrame(){
        setSize(1920, 1080);
        setContentPane(studyGuide);
        setTitle("study-guide");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized
    }

    private void addActionListeners(){
        essayButton.addActionListener(this);
        projectButton.addActionListener(this);
        subjectButton.addActionListener(this);
        financeButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    private void windowListener(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
                UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 24));
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
        // Open template screen and take in template type, which is dependent on button clicked, as parameter
        if(e.getSource() == essayButton){
            new Template("essay");
            dispose();
        }
        else if(e.getSource() == projectButton){
            new Template("project");
            dispose();
        }
        else if(e.getSource() == subjectButton){
            new Template("subject");
            dispose();
        }
        else if(e.getSource() == financeButton){
            new Template("finance");
            dispose();
        }
        else if(e.getSource() == backButton){
            dispose();
            new HomeScreen(HomeScreen.userAccount);
        }
    }
}
