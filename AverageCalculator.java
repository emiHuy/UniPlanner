import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AverageCalculator extends JFrame{
    private JPanel calc;
    private JLabel average;
    private JButton BACKButton;
    public static float calculateAverage(ArrayList<Course> courses) {
        int totalScores = 0;
        int totalEvaluations = 0;

        for (Course course : courses) {
            ArrayList<Evaluation> evaluations = course.getEvaluations();
            for (Evaluation evaluation : evaluations) {
                totalScores += evaluation.getEvaluationScore();
                totalEvaluations++;
            }
        }

        if (totalEvaluations == 0) {
            return 0; // To avoid division by zero
        }

        return (float) totalScores / totalEvaluations;
    }


    public AverageCalculator() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(calc);
        setVisible(true);       new JFrame();
        setTitle("Your overall average");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }
    public void calculateAndDisplayAverage() {
        // Calculate the average score of all classes combined
        float totalScore = 0;
        int totalEvaluations = 0;

        // Iterate over all courses
        for (Course course : Course.getCourseList()) {
            totalScore += course.calculateAverageScore();
            totalEvaluations++;
        }

        // Calculate the average score
        float averageScore = totalEvaluations > 0 ? totalScore / totalEvaluations : 0;

        // Display the average score in the label
        average.setText("Average Score of All Classes Combined: " + averageScore +"%");
    }
}

