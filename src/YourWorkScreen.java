import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
public class YourWorkScreen extends JFrame implements ActionListener{
    private JButton backButton;
    private JTable table;
    private JButton addAssignmentButton;
    private JPanel yourWork;
    private JLabel header;
    private Course specificCourse;

    public YourWorkScreen(Course course){
        specificCourse = course;
        initializeFrame();
        adjustComponents();
        setupButtons();
        //setupTable();
        populateTable();
        windowListener();
    }

    private void initializeFrame(){
        setContentPane(yourWork);
        setVisible(true);
        setTitle("Your Work Screen");
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void adjustComponents(){
        header.setText("Your " + specificCourse.getName() + " Work (" + specificCourse.getCode() + ")");
        header.setBorder(new EmptyBorder(25, 0, 25, 0));
    }

    private void setupButtons(){
        addAssignmentButton.setIcon(new ImageIcon("add icon.png"));
        addAssignmentButton.setBorder(new EmptyBorder(0,20,0,0));
        addAssignmentButton.setFocusPainted(false);
        addAssignmentButton.setContentAreaFilled(false);
        addAssignmentButton.addActionListener(this);

        backButton.setIcon(new ImageIcon("back button icon.png"));
        backButton.setBorder(new EmptyBorder(20,0,20,20));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);
    }

    private void setupTable(){
        JTableHeader tableHeader = table.getTableHeader();

        tableHeader.setFont(new Font("Courier New", Font.BOLD, 36));
        String[] columnNames = {"Evaluation", "Score", "Date"};
        table.setModel(new DefaultTableModel(new Object[][]{}, columnNames));

        table.setRowHeight(38);
    }

    private void populateTable() {
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Courier New", Font.BOLD, 36));
        table.setRowHeight(38);

        // Retrieve evaluations for the specific course
        ArrayList<Evaluation> evaluations = specificCourse.getEvaluations();
        // Sort evaluations by date using merge sort
        evaluations = mergeSortEvaluations(evaluations);

        // Create a two-dimensional array to hold the data
        Object[][] data = new Object[evaluations.size()][3];

        // Create format for date display in table
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");

        // Fill the data array with evaluation information
        for (int i = 0; i < evaluations.size(); i++) {
            Evaluation evaluation = evaluations.get(i);
            data[i][0] = evaluation.getEvaluationName();
            data[i][1] = evaluation.getEvaluationScore();
            data[i][2] = dateFormat.format(evaluation.getEvaluationDate());
        }

        // Define column names
        String[] columnNames = {"Evaluation", "Score", "Date"};

        // Create a table model with the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        model.fireTableDataChanged();

        // Set the table model to the JTable
        table.setModel(model);

        table.getColumn("Evaluation").setPreferredWidth(1500);
        table.getColumn("Score").setPreferredWidth(550);
        table.getColumn("Date").setPreferredWidth(1000);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return component;
            }
        };

        // Apply the renderer to all columns
        table.setDefaultRenderer(Object.class, cellRenderer);
    }

    private ArrayList<Evaluation> mergeSortEvaluations(ArrayList<Evaluation> evaluations) {
        if (evaluations.size() <= 1) {
            return evaluations;
        }

        int mid = evaluations.size() / 2;
        ArrayList<Evaluation> left = new ArrayList<>(evaluations.subList(0, mid));
        ArrayList<Evaluation> right = new ArrayList<>(evaluations.subList(mid, evaluations.size()));

        return merge(mergeSortEvaluations(left), mergeSortEvaluations(right));
    }

    private ArrayList<Evaluation> merge(ArrayList<Evaluation> left, ArrayList<Evaluation> right) {
        ArrayList<Evaluation> merged = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getEvaluationDate().before(right.get(rightIndex).getEvaluationDate())) {
                merged.add(left.get(leftIndex++));
            } else {
                merged.add(right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }

        return merged;
    }

    private void windowListener(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 27));
                UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 27));
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
            // Go to specific class screen
            new SpecificClassScreen(specificCourse);
            dispose();
        }
        else if(e.getSource() == addAssignmentButton){
            // Go to assignment add screen
            new AssignmentAddScreen(specificCourse);
            dispose();
        }
    }
}