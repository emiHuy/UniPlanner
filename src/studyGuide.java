import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class studyGuide extends JFrame implements ActionListener{
    private JPanel studyGuide;
    private JButton essayButton;
    private JButton projectButton;
    private JButton subjectButton;
    private JButton financeButton;
    private JTabbedPane tabbedPane1;
    private JButton backButton;
    private JLabel imageLabel;
    private String status= "";

    public studyGuide() {
        //createUIComponents(); // Initialize UI components first
        setSize(1200, 1000);
        setContentPane(studyGuide);
        setTitle("study-guide");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized

        // add action listeners to buttons
        essayButton.addActionListener(this);
        projectButton.addActionListener(this);
        subjectButton.addActionListener(this);
        financeButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    // perform necessary actions when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == essayButton){
            essayPlanner essayPlanner = new essayPlanner("essay");
        }
        else if(e.getSource() == projectButton){
            essayPlanner essayPlanner = new essayPlanner("project");
        }
        else if(e.getSource() == subjectButton){
            essayPlanner essayPlanner = new essayPlanner("subject");
        }
        else if(e.getSource() == financeButton){
            String status="finance";
            essayPlanner essayPlanner = new essayPlanner(status);
        }
        else if(e.getSource() == backButton){
            dispose();
        }
    }

}
