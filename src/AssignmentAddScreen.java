import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AssignmentAddScreen extends JFrame implements ActionListener {
    private JPanel project;
    private JButton backButton;
    private JButton addButton;
    private JTextField assignmentNameField;
    private JTextField dueDateField;
    private JTextField markField;
    private Course specificCourse;


    public AssignmentAddScreen(Course course) {
        specificCourse = course;
        initializeFrame();
        addActionListeners();
        windowListener();
    }

    private void initializeFrame() {
        add(project);
        setSize(1920, 1080);
        setContentPane(project);
        setVisible(true);
        setTitle("Your Work Screen");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void addActionListeners() {
        addButton.addActionListener(this);
        backButton.addActionListener(this);
    }
    private boolean isDuplicateAssignmentName(String assignmentName) {
        for (Evaluation evaluation : specificCourse.getEvaluations()) {
            if (evaluation.getEvaluationName().equals(assignmentName)) {
                return true;
            }
        }
        return false;
    }

    // method to read test from fields
    private boolean collectInput() {
        // Collect user input from text fields
        String evaluationName = assignmentNameField.getText().trim();
        String evaluationDateStr = dueDateField.getText().trim();
        String evaluationScoreStr = markField.getText().trim();
        // If any fields are empty, display error message and prompt
        if (evaluationName.isEmpty() || evaluationDateStr.isEmpty() || evaluationScoreStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!evaluationName.matches("^[a-zA-Z0-9\\-]*$")){
            JOptionPane.showMessageDialog(this, "Input may only consist of letters, numbers, and/or dashes.", "Input Warning", JOptionPane.WARNING_MESSAGE);
        }
        // If data is invalid, display error message and prompt
        if (!isValidDate(evaluationDateStr)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid date in the format yyyy-MM-dd.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Date evaluationDate = new SimpleDateFormat("yyyy-MM-dd").parse(evaluationDateStr);
            double evaluationScore = Double.parseDouble(evaluationScoreStr);

            // Check if mark is valid
            if (evaluationScore < 0 || evaluationScore > 100) {
                throw new NumberFormatException("Mark out of range");
            }
            if (isDuplicateAssignmentName(evaluationName)) {
                JOptionPane.showMessageDialog(this, "Another assignment with this name already exists for this course.", "Duplicate Assignment", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            System.out.println("Assignment Name: " + evaluationName);
            System.out.println("Due Date: " + evaluationDate);
            System.out.println("Mark: " + evaluationScore);

            // Create a new evaluation object
            Evaluation newEvaluation = new Evaluation(evaluationName, evaluationScore, evaluationDate);
            // Add the new evaluation to the specific course
            specificCourse.addEvaluation(newEvaluation);
            return true;
            // Transition to YourWorkScreen after successful input collection
            //new YourWorkScreen(specificCourse);
            //dispose();
        } catch (NumberFormatException | ParseException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid mark between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Method to check if the date is valid
    private boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            // Convert date from string to date type
            Date date = sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void windowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 27));
                UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 27));
                // Ask user to confirm exit when clicking exit button
                int exitResponse = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (exitResponse == JOptionPane.YES_OPTION) {
                    // If user confirms exit, save data before closing window
                    FileOperations.saveData();
                    dispose();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Collect inputted assignment information
            if (collectInput()) {
                new YourWorkScreen(specificCourse);
                dispose();
            }
        }else if (e.getSource() == backButton) {
                // Go to YourWorkScreen
                new YourWorkScreen(specificCourse);
                dispose();
            }
        }
    }

