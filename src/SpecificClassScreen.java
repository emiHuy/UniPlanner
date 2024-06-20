import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SpecificClassScreen extends JFrame implements ActionListener {
    private JPanel parentPanel;
    private JLabel topPlannerName;
    private JButton backButton;
    private JLabel bottomPlannerName;
    private JPanel borderPanel;
    private JLabel header;
    private JButton yourWorkButton;
    private JButton calculateAverageButton;
    private JButton yourWorkIcon;
    private JButton calculatorIcon;
    private Course specificCourse;

    public SpecificClassScreen(Course course){
        specificCourse = course;
        initializeFrame();
        setupComponents();
        windowListener();
    }

    private void initializeFrame() {
        setSize(1920, 1080);
        setVisible(true);
        setTitle("MnM Uni Planner");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        add(parentPanel);
        setContentPane(parentPanel);
    }

    private void setIcon(JButton button, String filename, int xSpacing){
        button.setIcon(new ImageIcon(filename));
        button.setBorder(new EmptyBorder(0,0,0,xSpacing));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(this);
    }
    private void setupComponents(){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 27));
        UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 27));

        header.setText(specificCourse.getName() + "(" + specificCourse.getCode() + ")");

        // Add spacing around panels
        borderPanel.setBorder(new EmptyBorder(50,75,50,75));

        // Add spacing around JLabels
        bottomPlannerName.setBorder(new EmptyBorder(10, 20, 10, 20));
        topPlannerName.setBorder(new EmptyBorder(10, 20, 10, 20));
        header.setBorder(new EmptyBorder(40, 20, 40, 20));

        // Set icons
        setIcon(yourWorkIcon, "your work icon.png",0);
        setIcon(calculatorIcon, "calculator icon.png",0);
        setIcon(backButton, "back button icon.png",30);

        // Set background colours for buttons
        yourWorkButton.setBackground(Color.LIGHT_GRAY);
        calculateAverageButton.setBackground(Color.LIGHT_GRAY);

        // Add regular button listeners
        yourWorkButton.addActionListener(this);
        calculateAverageButton.addActionListener(this);
    }

    // Method to retrieve and display the average score for the specific course
    private void calculateAndDisplayAverage() {
        double average = specificCourse.calculateAverageScore();
        double averageRounded = Math.round(average * 100) / 100.0;

        if(average == -1){
            JOptionPane.showMessageDialog(this, "No evaluations have been added to this course yet.", "Course Average", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            // Display a dialog with the calculated average
            JOptionPane.showMessageDialog(this, "Average Score for " + specificCourse.getName() + ": " + averageRounded+"%", "Course Average", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void windowListener(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
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
        if(e.getSource() == backButton) {
            // Go to ClassesScreen
            new ClassesScreen();
            dispose();
        }
        else if(e.getSource() == yourWorkButton || e.getSource() == yourWorkIcon){;
            // Go to YourWorkScreen
            new YourWorkScreen(specificCourse);
            dispose();
        }
        else if(e.getSource() == calculateAverageButton || e.getSource() == calculatorIcon){
            calculateAndDisplayAverage();
        }
    }
}