import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class essayPlanner  extends JFrame {
    private JPanel essayPanel;
    private JButton back;
    private JLabel DISPLAY;
    private ImageIcon essay = new ImageIcon("EssayPlanner.png");
    private ImageIcon project = new ImageIcon("ProjectPlanner.png");
    private ImageIcon subject = new ImageIcon("SubjectPlanner.png");
    private ImageIcon finance = new ImageIcon("FinancePlanner.png");

    public essayPlanner(String status) {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setTitle("Essay Planner");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the window instead of exiting the application
        setVisible(true); // Make the window visible
        setContentPane(essayPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized

        updateLabel(status);
    }

    // Initialize DISPLAY label with a default icon
    private void updateLabel(String status) {
        // Update the DISPLAY label based on the provided status
        switch (status) {
            case "essay":
                DISPLAY.setIcon(essay);
                break;
            case "project":
                DISPLAY.setIcon(project);
                break;
            case "subject":
                DISPLAY.setIcon(subject);
                break;
            case "finance":
                DISPLAY.setIcon(finance);
                break;
            default:
                System.out.println("Invalid status: " + status); // Debugging statement for unknown status
                break;
        }
    }
}