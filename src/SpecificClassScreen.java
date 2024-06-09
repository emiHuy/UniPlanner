import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecificClassScreen implements ActionListener {
    JFrame classFrame;
    private JPanel parentPanel;
    private JLabel topPlannerName;
    private JButton backButton;
    private JLabel bottomPlannerName;
    private JPanel borderPanel;
    private JLabel header;
    private JButton yourWorkButton;
    private JButton calculateAverageButton;

    public SpecificClassScreen(Course course){
        initializeFrame();
        adjustComponents();
        loadVariableData(course);
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
        borderPanel.setBorder(new EmptyBorder(200,300,200,300));

        // Add spacing around JLabels
        topPlannerName.setBorder(new EmptyBorder(50,50,50,50));
        bottomPlannerName.setBorder(new EmptyBorder(50,50,50,50));
        header.setBorder(new EmptyBorder(50, 20, 50, 20));

        // Set background colours for buttons
        backButton.setBackground(Color.LIGHT_GRAY);
        yourWorkButton.setBackground(Color.LIGHT_GRAY);
        calculateAverageButton.setBackground(Color.LIGHT_GRAY);

        // Set backButton size
        backButton.setPreferredSize(new Dimension(180, 50));

        // Add listeners
        backButton.addActionListener(this);
    }

    private void loadVariableData(Course course){
        header.setText(course.getName() + "(" + course.getCode() + ")");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            classFrame.dispose();
            new ClassesScreen(); // Go back to classes screen
        }
        else if(e.getSource() == yourWorkButton){
            // go to your work screen
        }
        else if(e.getSource() == calculateAverageButton){
            // display calculated average
        }
    }
}