import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Template extends JFrame implements ActionListener{
    private JPanel essayPanel;
    private JLabel templateImage;
    private JButton backButton;

    public Template(String status) {
        initializeFrame();
        addActionListeners();
        updateLabel(status);
    }

    private void initializeFrame(){
        setTitle("Essay Planner");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the window instead of exiting the application
        setVisible(true); // Make the window visible
        setContentPane(essayPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized
    }

    private void addActionListeners(){
        backButton.addActionListener(this);
    }

    private void updateLabel(String status) {
        ImageIcon essay = new ImageIcon("EssayPlanner.png");
        ImageIcon project = new ImageIcon("ProjectPlanner.png");
        ImageIcon subject = new ImageIcon("SubjectPlanner.png");
        ImageIcon finance = new ImageIcon("FinancePlanner.png");

        // Update the DISPLAY label based on the provided status
        switch (status) {
            case "essay":
                templateImage.setIcon(essay);
                break;
            case "project":
                templateImage.setIcon(project);
                break;
            case "subject":
                templateImage.setIcon(subject);
                break;
            case "finance":
                templateImage.setIcon(finance);
                break;
            default:
                System.out.println("Invalid status: " + status); // Debugging statement for unknown status
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new StudyGuide();
        dispose();
    }
}








