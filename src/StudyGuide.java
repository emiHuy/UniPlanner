import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.EmptyBorder;

public class StudyGuide extends JFrame implements ActionListener{
    private JPanel studyGuide;
    private JButton essayButton;
    private JButton projectButton;
    private JButton subjectButton;
    private JButton financeButton;
    private JButton backButton;
    private JButton projectIcon;
    private JButton financeIcon;
    private JButton subjectIcon;
    private JButton essayIcon;

    public StudyGuide() {
        initializeFrame();
        setupButtons();
        windowListener();
    }

    private void initializeFrame(){
        setSize(1920, 1080);
        setContentPane(studyGuide);
        setTitle("Study-Guide");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized
    }

    private void setupButton(JButton button, int bottomSpacing){
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(this);
        button.setBorder(new EmptyBorder(bottomSpacing/2, 0, bottomSpacing, 0));
    }

    private void setupButtons(){
        projectIcon.setIcon(new ImageIcon("project planner icon.png"));
        essayIcon.setIcon(new ImageIcon("essay planner icon.png"));
        financeIcon.setIcon(new ImageIcon("finance planner icon.png"));
        subjectIcon.setIcon(new ImageIcon("subject planner icon.png"));
        backButton.setIcon(new ImageIcon("back button icon.png"));

        JButton[] icons = {projectIcon, essayIcon, financeIcon, subjectIcon, backButton};
        for(JButton icon : icons){
            setupButton(icon, 0);
        }

        JButton[] buttons = {projectButton, essayButton, financeButton, subjectButton};
        for(JButton button : buttons){
            setupButton(button, 50);
        }
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
        if(e.getSource() == essayButton || e.getSource() == essayIcon){
            new Template("Essay");
            dispose();
        }
        else if(e.getSource() == projectButton || e.getSource() == projectIcon){
            new Template("Project");
            dispose();
        }
        else if(e.getSource() == subjectButton || e.getSource() == subjectIcon){
            new Template("Subject");
            dispose();
        }
        else if(e.getSource() == financeButton || e.getSource() == financeIcon){
            new Template("Finance");
            dispose();
        }
        else if(e.getSource() == backButton){
            dispose();
            new HomeScreen(HomeScreen.userAccount);
        }
    }
}
