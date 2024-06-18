import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Template extends JFrame implements ActionListener{
    private JPanel essayPanel;
    private JLabel templateImage;
    private JButton backButton;

    public Template(String status) {
        initializeFrame();
        addActionListeners();
        updateLabel(status);
        windowListener();
    }

    private void initializeFrame(){
        setTitle("Essay Planner");
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Dispose the window instead of exiting the application
        setVisible(true); // Make the window visible
        setContentPane(essayPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized
    }

    private void addActionListeners(){
        backButton.addActionListener(this);
    }

    private void updateLabel(String status) {
        // Initialize possible template image icons
        ImageIcon essay = new ImageIcon("EssayPlanner.jpg");
        ImageIcon project = new ImageIcon("ProjectPlanner.png");
        ImageIcon subject = new ImageIcon("SubjectPlanner.png");
        ImageIcon finance = new ImageIcon("FinancePlanner.png");

        // Set template image based off button that was clicked
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
        if(e.getSource() == backButton){
            // Go to StudyGuide screen
            new StudyGuide();
            dispose();
        }
    }
}