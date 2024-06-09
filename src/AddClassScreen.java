import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClassScreen extends JFrame implements ActionListener {
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
        addClassesFrame.setSize(1280, 720);
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

    private void collectInput(){
        String courseName = courseNameField.getText();
        String courseCode = courseCodeField.getText();

        if(courseName.trim().isEmpty() || courseCode.trim().isEmpty()){
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
            UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 24));
            JOptionPane.showMessageDialog(addClassesFrame, "Please fill out all fields.", "Input Warning", JOptionPane.WARNING_MESSAGE);
        }
        else{
            new Course(courseName, courseCode);
            addClassesFrame.dispose();
            new ClassesScreen();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            addClassesFrame.dispose();
            new ClassesScreen(); // Go back to classes screen
        }
        else if(e.getSource() == addClassButton){
            collectInput();
        }
    }
}