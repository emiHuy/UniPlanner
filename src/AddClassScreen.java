import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddClassScreen extends JFrame implements ActionListener{
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
        setSize(1920, 1080);
        setVisible(true);
        setTitle("MnM Uni Planner");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        add(parentPanel);
        setContentPane(parentPanel);
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
        // Setting dialog fonts and font sizes
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 27));
        UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 27));

        // Get user input from text fields
        String courseName = courseNameField.getText().trim();
        String courseCode = courseCodeField.getText().trim();

        // Check if existing course already has inputted course name or course code
        for(Course course: HomeScreen.userAccount.getCourseList()){
            if(courseName.equals(course.getName())){
                JOptionPane.showMessageDialog(this, "Another course already has this name.", "Input Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else if(courseCode.equals(course.getCode())){
                JOptionPane.showMessageDialog(this, "Another course already has this course code.", "Input Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Check user input collected from text fields if empty (is invalid)
        if(courseName.isEmpty() || courseCode.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Input Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if(!courseName.matches("^[a-zA-Z0-9\\-]*$") || !courseCode.matches("^[a-zA-Z0-9\\-]*$")){
            JOptionPane.showMessageDialog(this, "Input may only consist of letters, numbers, and/or dashes.", "Input Warning", JOptionPane.WARNING_MESSAGE);
        }
        else{
            // If valid, create Course object with user input
            Course course = new Course(courseName, courseCode);
            // Add course to account's course list
            HomeScreen.userAccount.addCourse(course);

            new ClassesScreen();
            dispose();
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
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            new ClassesScreen(); // Go back to classes screen
            dispose();
        }
        else if(e.getSource() == addClassButton){
            collectInput();
        }
    }
}