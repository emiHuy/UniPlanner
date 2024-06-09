import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClassScreen extends JFrame implements ActionListener {
    private JFrame classesFrame;
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
        classesFrame = new JFrame();
        classesFrame.setSize(1280, 720);
        classesFrame.setVisible(true);
        classesFrame.setTitle("MnM Uni Planner");
        classesFrame.setLocationRelativeTo(null);
        classesFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        classesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        classesFrame.add(parentPanel);
        classesFrame.setContentPane(parentPanel);
    }

    private void adjustComponents(){
        // Add spacing around panels
        borderPanel.setBorder(new EmptyBorder(200,300,200,300));
        buttonPanel.setBorder(new EmptyBorder(100,0,100,0));

        // Add spacing around JLabels
        topPlannerName.setBorder(new EmptyBorder(50,50,50,50));
        bottomPlannerName.setBorder(new EmptyBorder(50,50,50,50));
        header.setBorder(new EmptyBorder(50, 20, 50, 20));

        // Set background colours for buttons
        addClassButton.setBackground(Color.LIGHT_GRAY);
        backButton.setBackground(Color.LIGHT_GRAY);

        // Set backButton size
        backButton.setPreferredSize(new Dimension(180, 50));

        // Add listeners
        backButton.addActionListener(this);
        addClassButton.addActionListener(this);
    }

    // Get valid string input
    private static String getValidStrInput(JTextField field){
        String input;
        boolean isInvalid;

        do{
            isInvalid = false;
            input = field.getText().trim();

            if(input.isEmpty()){
                isInvalid = true;
                System.out.println("Invalid input. Nothing entered.\n");
            }

        } while(isInvalid);
        return input;
    }

    private void collectInput(){
        String courseName = getValidStrInput(courseNameField);
        String courseCode = getValidStrInput(courseCodeField);
        new Course(courseName, courseCode);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            classesFrame.dispose();
            new ClassesScreen(); // Go back to classes screen
        }
        else if(e.getSource() == addClassButton){
            classesFrame.dispose();
            collectInput();
            new ClassesScreen();
        }
    }
}