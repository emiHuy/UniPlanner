import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecificClassScreen implements ActionListener {
    private JFrame classFrame;
    private JPanel parentPanel;
    private JLabel topPlannerName;
    private JButton backButton;
    private JLabel bottomPlannerName;
    private JPanel borderPanel;
    private JLabel header;
    private JButton yourWorkButton;
    private JButton calculateAverageButton;
    // EMMA: Create private Course object here --> name it smt other than course as I have used 'course' in other areas of code
    // EMMA: ie. you can name it specificCourse or selectedCourse or smt else.

    public SpecificClassScreen(Course course){
        initializeFrame();
        adjustComponents();
        loadVariableData(course);
        // EMMA: Here is where you assign course object value, using the course parameter taken
        // EMMA: ie. specificCourse = course; <--- if you named the object variable specificCourse.
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
            // EMMA: go to your work screen, take in Course object as parameter
            // EMMA: ie. new YourWorkScreen(specificCourse); <--- if specificCourse is the object variable name

            // EMMA: Screen for adding new evaluations is very similar to adding classes --> check out my code and form
            // EMMA: Most of the code is for layout, which you can copy along with form. Check the method I used for retrieving input and where it was called in code.
        }
        else if(e.getSource() == calculateAverageButton){
            // EMMA: display calculated average screen, take in Course object as parameter (like I stated in above else if statement)

            // EMMA: in average screen, use the getter method for evaluation list to retrieve evaluations --> this is an instance method, so you must use Course object to call it
            // EMMA: use enhanced for loop to iterate through each evaluation, and use a getter to retrieve marks (add marks and divide by number of evaluations)
        }
    }
}