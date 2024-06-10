import javax.swing.*;
import java.awt.event.ActionEvent;import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class YourWorkScreen extends JFrame implements ActionListener{
    private JButton backButton;
    private JTable table1;
    private JButton addAssignmentButton;
    private JPanel yourWork;
    private Course specificCourse;
    public YourWorkScreen(Course course){
        specificCourse = course;
        initializeFrame();
        addActionListeners();
        setupTable();
        populateTable();
    }

    private void initializeFrame(){
        setContentPane(yourWork);
        setVisible(true);
        setTitle("Your Work Screen");
        setLocationRelativeTo(null);
        setSize(1200,1000);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addActionListeners(){
        addAssignmentButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    private void setupTable(){
        String[] columnNames = {"Evaluation Name", "Score", "Date"};
        table1.setModel(new DefaultTableModel(new Object[][]{}, columnNames)); // Set font for table headers
    }

    private void populateTable() {
        // Retrieve evaluations for the specific course
        ArrayList<Evaluation> evaluations = specificCourse.getEvaluations();

        // Create a two-dimensional array to hold the data
        Object[][] data = new Object[evaluations.size()][3];

        // Fill the data array with evaluation information
        for (int i = 0; i < evaluations.size(); i++) {
            Evaluation evaluation = evaluations.get(i);
            data[i][0] = evaluation.getEvaluationName();
            data[i][1] = evaluation.getEvaluationScore();
            data[i][2] = evaluation.getEvaluationDate();
        }

        // Define column names
        String[] columnNames = {"Evaluation Name", "Score", "Date"};
        // Create a table model with the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        model.fireTableDataChanged();

        // Set the table model to the JTable
        table1.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            new SpecificClassScreen(specificCourse);
            dispose();
        }
        else if(e.getSource() == addAssignmentButton){
            new AssignmentAddScreen(specificCourse);
        }
    }
}