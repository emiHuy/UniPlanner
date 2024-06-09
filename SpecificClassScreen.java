import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    // EMMA: Create private Course object here --> name it smt other than course as I have used 'course' in other areas of code
    private static Course specificCourse;
    // EMMA: ie. you can name it specificCourse or selectedCourse or smt else. 

    public SpecificClassScreen(Course course){
        this.specificCourse=course;
        initializeFrame();
        adjustComponents();
        loadVariableData(course);
        // EMMA: Here is where you assign course object value, using the course parameter taken
        // EMMA: ie. specificCourse = course; <--- if you named the object variable specificCourse.
    }
    public static Course getSpecificCourse() {
        return specificCourse;
    }
    private void initializeFrame() {
        classFrame = new JFrame();
        classFrame.setSize(1280, 720);
        classFrame.setVisible(true);
        classFrame.setTitle("MnM Uni Planner");
        classFrame.setLocationRelativeTo(null);
        classFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        classFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        float totalScore = 0;
        int count = evaluations.size();

        // Calculate total score
        for (Evaluation evaluation : evaluations) {
            totalScore += evaluation.getEvaluationScore();
        }

        // Calculate average
        float average = count > 0 ? totalScore / count : 0;

        // Display a dialog with the calculated average
        JOptionPane.showMessageDialog(null, "Average Score for " + specificCourse.getName() + ": " + average);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            classFrame.dispose();
            new ClassesScreen(); // Go back to classes screen
        }
        else if(e.getSource() == yourWorkButton){;
            new YourWorkScreen(specificCourse);
        }
        else if(e.getSource() == calculateAverageButton){
            calculateAndDisplayAverage();
            // EMMA: display calculated average screen, take in Course object as parameter (like I stated in above else if statement)

            // EMMA: in average screen, use the getter method for evaluation list to retrieve evaluations --> this is an instance method, so you must use Course object to call it
            // EMMA: use enhanced for loop to iterate through each evaluation, and use a getter to retrieve marks (add marks and divide by number of evaluations)
        }
    }
}