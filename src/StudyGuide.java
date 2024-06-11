import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudyGuide extends JFrame implements ActionListener{
    private JPanel studyGuide;
    private JButton essayButton;
    private JButton projectButton;
    private JButton subjectButton;
    private JButton financeButton;
    private JTabbedPane tabbedPane1;
    private JButton backButton;
    private JLabel imageLabel;
    private String status= "";

    public StudyGuide() {
        initializeFrame();
        addActionListeners();
    }

    private void initializeFrame(){
        setSize(1200, 1000);
        setContentPane(studyGuide);
        setTitle("study-guide");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized
    }

    private void addActionListeners(){
        essayButton.addActionListener(this);
        projectButton.addActionListener(this);
        subjectButton.addActionListener(this);
        financeButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == essayButton){
            new Template("essay");
        }
        else if(e.getSource() == projectButton){
            new Template("project");
        }
        else if(e.getSource() == subjectButton){
            new Template("subject");
        }
        else if(e.getSource() == financeButton){
            new Template("finance");
        }
        else if(e.getSource() == backButton){
            new HomeScreen(HomeScreen.userAccount);
            dispose();
        }
    }
}
