import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AssignmentAddScreen extends JFrame {
    private JButton goBackButton;
    private JPanel project;
    private JButton goBack;
    private JButton ADDButton;
    private JTextField assignmentNameField;
    private JTextField dueDateField;
    private JTextField markField;
    private YourWorkScreen yourWorkScreen;


    public AssignmentAddScreen(YourWorkScreen yourWorkScreen) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(project);
        setVisible(true);
        setTitle("Your Work Screen");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.yourWorkScreen= yourWorkScreen;

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collectInput();
                yourWorkScreen.dispose();


            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
// method to read test from fields
private void collectInput() {
    String evaluationName = assignmentNameField.getText();
    String evaluationDateStr = dueDateField.getText();
    String evaluationScoreStr = markField.getText();
    if (evaluationName.isEmpty() || evaluationDateStr.isEmpty() || evaluationScoreStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    if (!isValidDate(evaluationDateStr)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid date in the format yyyy-MM-dd.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    try {
        Date evaluationDate = new SimpleDateFormat("yyyy-MM-dd").parse(evaluationDateStr);
        float evaluationScore = Float.parseFloat(evaluationScoreStr);

        if (evaluationScore < 0 || evaluationScore > 100) {
            throw new NumberFormatException("Mark out of range");
        }
        System.out.println("Assignment Name: " + evaluationName);
        System.out.println("Due Date: " + evaluationDate);
        System.out.println("Mark: " + evaluationScore);

        // Create a new evaluation object
        Evaluation newEvaluation = new Evaluation(evaluationName, evaluationScore, evaluationDate);
        // Add the new evaluation to the specific course
        SpecificClassScreen.getSpecificCourse().addEvaluation(newEvaluation);
        // Dispose the window after successful input collection
        dispose();
    } catch (NumberFormatException | ParseException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid mark between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
// Method to check if the date is valid
private boolean isValidDate(String dateStr) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(false);
    try {
        Date date = sdf.parse(dateStr);
        return true;
    } catch (ParseException e) {
        return false;
    }
}
}

