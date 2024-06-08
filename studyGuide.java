import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class studyGuide extends JFrame {
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
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized

        essayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status="essay";
                essayPlanner essayPlanner = new essayPlanner(status);
                System.out.println("Essay button clicked"); // Debugging statement
                essayPlanner.updateLabel(status);


            }
        });
        projectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status="project";
                essayPlanner essayPlanner = new essayPlanner(status);
                essayPlanner.updateLabel(status);
            }
        });
        subjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status="subject";
                essayPlanner essayPlanner = new essayPlanner(status);
                essayPlanner.updateLabel(status);

            }
        });
        financeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status="finance";
                essayPlanner essayPlanner = new essayPlanner(status);
                essayPlanner.updateLabel(status);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }

}
