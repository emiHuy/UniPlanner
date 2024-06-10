import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClassesScreen implements ActionListener{
    private JFrame classesFrame;
    private JPanel viewClassesPanel;
    private JButton calculateAvgButton;
    private JButton addClassButton;
    private JButton backButton;
    private ArrayList<JButton> classButtons = new ArrayList<>();

    public ClassesScreen(){
        initializeFrame();
        setupTopPanel();
        setupBottomPanel();
        setupCentralPanel();
        createClassButtons();
    }

    private void initializeFrame() {
        classesFrame = new JFrame();
        classesFrame.setSize(1200, 1000);
        classesFrame.setVisible(true);
        classesFrame.setTitle("MnM Uni Planner");
        classesFrame.setLocationRelativeTo(null);
        classesFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        classesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        classesFrame.setLayout(new BorderLayout());
    }

    private void createPlannerName(JPanel panel){
        JLabel plannerName = new JLabel("MnM Uni Planner");
        plannerName.setFont(new Font("Courier New", Font.PLAIN, 16));
        plannerName.setForeground(new Color(0,229,31));
        plannerName.setBorder(new EmptyBorder(25,25,25,25));
        panel.add(plannerName);
    }

    private void setupTopPanel(){
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.BLACK);
        createPlannerName(topPanel);
        classesFrame.add(topPanel, BorderLayout.NORTH);
    }

    private void setupBottomPanel(){
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        backButton = createButton("Back", 180, 50, Color.LIGHT_GRAY);
        bottomPanel.add(backButton);

        bottomPanel.setBackground(Color.BLACK);
        createPlannerName(bottomPanel);

        classesFrame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createButton (String text, int xSize, int ySize, Color color){
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(color);
        button.setPreferredSize(new Dimension(xSize, ySize));
        button.setFont(new Font("Arial", Font.PLAIN, 28));
        button.addActionListener(this);
        return button;
    }

    private void setupCentralPanel(){

        // Main central panel
        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBorder(new EmptyBorder(50,75,50,75));
        borderPanel.setBackground(Color.BLACK);
        classesFrame.add(borderPanel, BorderLayout.CENTER);

        // Sub-panel for header
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Your Classes This Semester");
        title.setFont(new Font("Courier New", Font.BOLD, 40));
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(25, 25, 25, 25));
        titlePanel.setBackground(new Color(0, 229, 31));
        titlePanel.add(title);
        borderPanel.add(titlePanel, BorderLayout.NORTH);

        // Sub-panel for classes display
        viewClassesPanel = new JPanel();
        viewClassesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        borderPanel.add(viewClassesPanel, BorderLayout.CENTER);

        // Sub-panel for overall average calculator button
        JPanel avgPanel = new JPanel();
        avgPanel.setBorder(new EmptyBorder(10,0,10,0));
        calculateAvgButton = createButton("AVERAGE CALCULATOR", 500, 50, Color.LIGHT_GRAY);
        avgPanel.add(calculateAvgButton);
        borderPanel.add(avgPanel, BorderLayout.SOUTH);
    }

    private void createClassButtons(){
        //Retrieve courses
        ArrayList<Course> courseList = Course.getCourseList();

        // Set up buttons for each class
        for(Course course : courseList){
            JButton button = createButton(course.getName(), 400, 50, Color.LIGHT_GRAY);
            classButtons.add(button);
            viewClassesPanel.add(button);
        }

        // Set up button for adding a class
        addClassButton = createButton("+ Add Class", 400, 50, new Color(0,229,36));
        viewClassesPanel.add(addClassButton);
    }

    private Course linearSearchCourse(String name){
        //Retrieve courses
        ArrayList<Course> courseList = Course.getCourseList();

        for(Course course : courseList){
            if(course.getName().equals(name)){
                return course;
            }
        }
        return new Course("", "");
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            new HomeScreen();
            classesFrame.dispose();
        }
        else if(e.getSource() == calculateAvgButton){
            // display dialog with calculated average or new screen?
            new AverageCalculator();
        }
        else if(e.getSource() == addClassButton){
            new AddClassScreen();
            classesFrame.dispose();
        }
        else{
            // Get button that was clicked
            JButton button = (JButton)e.getSource();
            // Search for course by course name
            Course course = linearSearchCourse(button.getText());
            // Go to corresponding course screen
            new SpecificClassScreen(course);
        }
    }
}