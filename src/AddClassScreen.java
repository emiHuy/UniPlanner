import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClassScreen implements ActionListener {
    private JFrame addClassesFrame;
    private JButton addClassButton;
    private JPanel borderPanel;
    private JPanel buttonPanel;
    private JPanel parentPanel;
    private JTextField courseNameField;
    private JTextField courseCodeField;
    private JLabel topPlannerName;
    private JLabel bottomPlannerName;
    private JButton backButton;
    private JLabel header;

    public AddClassScreen(){
        initializeFrame();
        adjustComponents();
    }

    private void initializeFrame() {
        addClassesFrame = new JFrame();
        addClassesFrame.setSize(1200, 1000);
        addClassesFrame.setVisible(true);
        addClassesFrame.setTitle("MnM Uni Planner");
        addClassesFrame.setLocationRelativeTo(null);
        addClassesFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        addClassesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addClassesFrame.add(parentPanel);
        addClassesFrame.setContentPane(parentPanel);
    }

    private void adjustComponents(){
        // Add spacing around panels
        borderPanel.setBorder(new EmptyBorder(50,75,50,75));
        buttonPanel.setBorder(new EmptyBorder(25,0,25,0));

        // Add spacing around JLabels
        bottomPlannerName.setBorder(new EmptyBorder(10, 20, 10, 20));
        topPlannerName.setBorder(new EmptyBorder(10, 20, 10, 20));
        header.setBorder(new EmptyBorder(25, 20, 25, 20));

        // Set background colours for buttons
        addClassButton.setBackground(Color.LIGHT_GRAY);
        backButton.setBackground(Color.LIGHT_GRAY);

        // Set backButton size
        backButton.setPreferredSize(new Dimension(180, 50));

        // Add listeners
        backButton.addActionListener(this);
        addClassButton.addActionListener(this);
    }

    private void collectInput(){
        String courseName = courseNameField.getText();
        String courseCode = courseCodeField.getText();

        if(courseName.trim().isEmpty() || courseCode.trim().isEmpty()){
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
            UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 24));
            JOptionPane.showMessageDialog(addClassesFrame, "Please fill out all fields.", "Input Warning", JOptionPane.WARNING_MESSAGE);
        }
        else{
            Course course = new Course(courseName, courseCode);
            HomeScreen.userAccount.addCourse(course);
            new ClassesScreen();
            addClassesFrame.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            new ClassesScreen(); // Go back to classes screen
            addClassesFrame.dispose();
        }
        else if(e.getSource() == addClassButton){
            collectInput();
        }
    }
}