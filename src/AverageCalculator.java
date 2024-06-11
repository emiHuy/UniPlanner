import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AverageCalculator extends JFrame implements ActionListener{
    private JPanel calc;
    private JLabel average;
    private JButton backButton;

    public AverageCalculator() {
        initializeFrame();
        addActionListeners();
        calculateAndDisplayAverage();
        windowListener();
    }

    private void initializeFrame(){
        add(calc);
        setContentPane(calc);
        setVisible(true);
        setTitle("Your overall average");
        setLocationRelativeTo(null);
        setSize(1200, 1000);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void addActionListeners(){
        backButton.addActionListener(this);
    }

    private void calculateAndDisplayAverage() {
        // Calculate the average score of all classes combined
        double totalScore = 0;
        int totalCourses = 0;

        // Iterate over all courses
        for (Course course : HomeScreen.userAccount.getCourseList()) {
            totalScore += course.calculateAverageScore();
            totalCourses++;
        }

        // Calculate the average score
        double averageScore = totalCourses > 0 ? totalScore / totalCourses : 0;

        // Display the average score in the label
        average.setText("Average Score of All Classes Combined: " + averageScore +"%");
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
            // Go to ClassesScreen
            new ClassesScreen();
            dispose();
        }
    }
}

