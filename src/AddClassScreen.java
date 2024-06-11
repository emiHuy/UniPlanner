import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddClassScreen implements ActionListener {
    private JFrame addClassesFrame;
    private JButton addClassButton;
    private JPanel borderPanel;
    private JPanel buttonPanel;
    private JPanel parentPanel;
    private JTextField courseNameField;
    private JTextField courseCodeField;
    private JLabel topPlannerName;
    private JLabel bottomPlannerName;
    private JButton backButton;
    private JLabel header;

    public AddClassScreen(){
        initializeFrame();
        adjustComponents();
        windowListener();
    }

    private void initializeFrame() {
        addClassesFrame = new JFrame();
        addClassesFrame.setSize(1200, 1000);
        addClassesFrame.setVisible(true);
        addClassesFrame.setTitle("MnM Uni Planner");
        addClassesFrame.setLocationRelativeTo(null);
        addClassesFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        addClassesFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addClassesFrame.add(parentPanel);
        addClassesFrame.setContentPane(parentPanel);
    }

    private void adjustComponents(){
        // Add spacing around panels
        borderPanel.setBorder(new EmptyBorder(50,75,50,75));
        buttonPanel.setBorder(new EmptyBorder(25,0,25,0));

        // Add spacing around JLabels
        bottomPlannerName.setBorder(new EmptyBorder(10, 20, 10, 20));
        topPlannerName.setBorder(new EmptyBorder(10, 20, 10, 20));
        header.setBorder(new EmptyBorder(25, 20, 25, 20));

        // Set background colours for buttons
        addClassButton.setBackground(Color.LIGHT_GRAY);
        backButton.setBackground(Color.LIGHT_GRAY);

        // Set backButton size
        backButton.setPreferredSize(new Dimension(180, 50));

        // Add listeners
        backButton.addActionListener(this);
        addClassButton.addActionListener(this);
    }

    private void collectInput(){
        // Get user input from text fields
        String courseName = courseNameField.getText();
        String courseCode = courseCodeField.getText();

        // Check user input collected from text fields if empty (is invalid)
        if(courseName.trim().isEmpty() || courseCode.trim().isEmpty()){
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
            UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 24));
            JOptionPane.showMessageDialog(addClassesFrame, "Please fill out all fields.", "Input Warning", JOptionPane.WARNING_MESSAGE);
        }
        else{
            // If valid, create Course object with user input
            Course course = new Course(courseName, courseCode);
            // Add course to account's course list
            HomeScreen.userAccount.addCourse(course);

            new ClassesScreen();
            addClassesFrame.dispose();
        }
    }

    private void windowListener(){
        addClassesFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
                UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 24));
                // Ask user to confirm exit when clicking exit button
                int exitResponse = JOptionPane.showConfirmDialog(addClassesFrame, "Are you sure you want to quit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(exitResponse == JOptionPane.YES_OPTION){
                    // If user confirms exit, save data before closing window
                    FileOperations.saveData();
                    addClassesFrame.dispose();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            new ClassesScreen(); // Go back to classes screen
            addClassesFrame.dispose();
        }
        else if(e.getSource() == addClassButton){
            collectInput();
        }
    }
}