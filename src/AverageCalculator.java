import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AverageCalculator extends JFrame implements ActionListener{
    private JPanel calc;
    private JLabel average;
    private JButton backButton;

    public AverageCalculator() {
        initializeFrame();
        addActionListeners();
        calculateAndDisplayAverage();
    }

    private void initializeFrame(){
        add(calc);
        setContentPane(calc);
        setVisible(true);
        setTitle("Your overall average");
        setLocationRelativeTo(null);
        setSize(1200, 1000);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addActionListeners(){
        backButton.addActionListener(this);
    }

    private void calculateAndDisplayAverage() {
        // Calculate the average score of all classes combined
        double totalScore = 0;
        int totalCourses = 0;

        // Iterate over all courses
        for (Course course : Course.getCourseList()) {
            totalScore += course.calculateAverageScore();
            totalCourses++;
        }

        // Calculate the average score
        double averageScore = totalCourses > 0 ? totalScore / totalCourses : 0;

        // Display the average score in the label
        average.setText("Average Score of All Classes Combined: " + averageScore +"%");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            new ClassesScreen();
            dispose();
        }
    }
}

