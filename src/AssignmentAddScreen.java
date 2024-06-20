import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AssignmentAddScreen extends JFrame implements ActionListener {
    private JPanel parentPanel;
    private JButton backButton;
    private JButton addButton;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField markField;
    private JLabel header;
    private Course specificCourse;


    public AssignmentAddScreen(Course course) {
        specificCourse = course;
        initializeFrame();
        setupComponents();
        windowListener();
    }

    private void initializeFrame() {
        add(parentPanel);
        setSize(1920, 1080);
        setContentPane(parentPanel);
        setVisible(true);
        setTitle("Your Work Screen");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void setupComponents() {
        header.setBorder(new EmptyBorder(40,0,40,0));

        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.addActionListener(this);

        backButton.setIcon(new ImageIcon("back button icon.png"));
        backButton.setBorder(new EmptyBorder(20,0,20,30));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);
    }


    // method to read test from fields
    private void collectInput() {
        // Collect user input from text fields
        String evaluationName = nameField.getText().trim();
        String evaluationDateStr = dateField.getText().trim();
        String evaluationScoreStr = markField.getText().trim();

        double evaluationScore;
        Date evaluationDate = convertDate(evaluationDateStr);

        for (Evaluation evaluation : specificCourse.getEvaluations()) {
            if (evaluation.getEvaluationName().equals(evaluationName)) {
                JOptionPane.showMessageDialog(this, "Another assignment with this name already exists for this course.", "Duplicate Assignment", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            evaluationScore = Double.parseDouble(evaluationScoreStr);
            // Check if mark is valid
            if (evaluationScore < 0 || evaluationScore > 100) {
                throw new NumberFormatException("Mark out of range");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid mark between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If any fields are empty, display error message and prompt
        if (evaluationName.isEmpty() || evaluationDateStr.isEmpty() || evaluationScoreStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if (!evaluationName.matches("^[a-zA-Z0-9\\-\\s]*$")){
            JOptionPane.showMessageDialog(this, "Input may only consist of letters, numbers, spaces, and/or dashes.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // If data is invalid, display error message and prompt
        else if (evaluationDate == null) {
            JOptionPane.showMessageDialog(this, "Please enter a valid date in the format yyyy-MM-dd.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            // Create a new evaluation object
            Evaluation evaluation = new Evaluation(evaluationName, evaluationScore, evaluationDate);
            // Add the new evaluation to the specific course
            specificCourse.addEvaluation(evaluation);
            new YourWorkScreen(specificCourse);
            dispose();
        }
    }

    // Method to check if the date is valid and get date value
    private Date convertDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date date;
        try {
            // Convert date from string to date type
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
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
            collectInput();
        }
        else if (e.getSource() == backButton) {
                // Go to YourWorkScreen
                new YourWorkScreen(specificCourse);
                dispose();
            }
        }
    }