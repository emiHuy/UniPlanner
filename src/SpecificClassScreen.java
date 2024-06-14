import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SpecificClassScreen implements ActionListener {
    private JFrame classFrame;
    private JPanel parentPanel;
    private JLabel topPlannerName;
    private JButton backButton;
    private JLabel bottomPlannerName;
    private JPanel borderPanel;
    private JLabel header;
    private JButton yourWorkButton;
    private JButton calculateAverageButton;
    private static Course specificCourse;

    public SpecificClassScreen(Course course){
        specificCourse = course;
        initializeFrame();
        adjustComponents();
        loadVariableData(course);
        windowListener();
    }
    public static Course getSpecificCourse() {
        return specificCourse;
    }

    private void initializeFrame() {
        classFrame = new JFrame();
        classFrame.setSize(1200, 1000);
        classFrame.setVisible(true);
        classFrame.setTitle("MnM Uni Planner");
        classFrame.setLocationRelativeTo(null);
        classFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        classFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        classFrame.add(parentPanel);
        classFrame.setContentPane(parentPanel);
    }

    private void adjustComponents(){
        // Add spacing around panels
        borderPanel.setBorder(new EmptyBorder(50,75,50,75));

        // Add spacing around JLabels
        bottomPlannerName.setBorder(new EmptyBorder(10, 20, 10, 20));
        topPlannerName.setBorder(new EmptyBorder(10, 20, 10, 20));
        header.setBorder(new EmptyBorder(50, 20, 50, 20));

        // Set background colours for buttons
        backButton.setBackground(Color.LIGHT_GRAY);
        yourWorkButton.setBackground(Color.LIGHT_GRAY);
        calculateAverageButton.setBackground(Color.LIGHT_GRAY);

        // Set backButton size
        backButton.setPreferredSize(new Dimension(180, 50));

        // Add listeners
        backButton.addActionListener(this);
        yourWorkButton.addActionListener(this);
        calculateAverageButton.addActionListener(this);
    }

    private void loadVariableData(Course course){
        header.setText(course.getName() + "(" + course.getCode() + ")");
    }

    // Method to calculate and display the average score for the specific course
    private void calculateAndDisplayAverage() {
        // Retrieve evaluations for the specific course
        ArrayList<Evaluation> evaluations = specificCourse.getEvaluations();
        double totalScore = 0;
        int count = evaluations.size();

        // Calculate total score
        for (Evaluation evaluation : evaluations) {
            totalScore += evaluation.getEvaluationScore();
        }

        // Calculate average
        double average = count > 0 ? totalScore / count : 0;
        double averageRounded=Math.round(average * 100) / 100.0;

        // Display a dialog with the calculated average
        JOptionPane.showMessageDialog(null, "Average Score for " + specificCourse.getName() + ": " + averageRounded+"%");
    }

    private void windowListener(){
        classFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
                UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 24));
                // Ask user to confirm exit when clicking exit button
                int exitResponse = JOptionPane.showConfirmDialog(classFrame, "Are you sure you want to quit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(exitResponse == JOptionPane.YES_OPTION){
                    // If user confirms exit, save data before closing window
                    FileOperations.saveData();
                    classFrame.dispose();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            // Go to ClassesScreen
            new ClassesScreen();
            classFrame.dispose();
        }
        else if(e.getSource() == yourWorkButton){;
            // Go to YourWorkScreen
            new YourWorkScreen(specificCourse);
            classFrame.dispose();
        }
        else if(e.getSource() == calculateAverageButton){
            calculateAndDisplayAverage();
        }
    }
}