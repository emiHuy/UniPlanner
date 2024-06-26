import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ClassesScreen extends JFrame implements ActionListener{
    private JPanel viewClassesPanel;
    private JButton calculateAvgButton;
    private JButton addClassButton;
    private JButton backButton;
    private ArrayList<JButton> classButtons = new ArrayList<>();
    private Account userAccount = HomeScreen.userAccount;

    public ClassesScreen(){
        initializeFrame();
        setupTopPanel();
        setupBottomPanel();
        setupCentralPanel();
        createClassButtons();
        windowListener();
    }

    private void initializeFrame() {
        setSize(1920, 1080);
        setVisible(true);
        setTitle("MnM Uni Planner");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void createPlannerName(JPanel panel){
        JLabel plannerName = new JLabel("MnM Uni Planner");
        plannerName.setFont(new Font("Arial", Font.PLAIN, 16));
        plannerName.setForeground(new Color(0,229,31));
        plannerName.setBorder(new EmptyBorder(25,25,25,25));
        panel.add(plannerName);
    }

    private void setupTopPanel(){
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.BLACK);
        createPlannerName(topPanel);
        add(topPanel, BorderLayout.NORTH);
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

    private void setupBottomPanel(){
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.BLACK);
        createPlannerName(bottomPanel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setupCentralPanel(){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 27));
        UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 27));

        // Main central panel
        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBorder(new EmptyBorder(50,75,50,75));
        borderPanel.setBackground(Color.BLACK);
        add(borderPanel, BorderLayout.CENTER);

        // Sub-panel for header
        JPanel subTopPanel = new JPanel(new GridLayout());

        JPanel leftTopPanel = new JPanel();
        leftTopPanel.setBackground(new Color(0, 229, 31));
        subTopPanel.add(leftTopPanel);

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Your Classes");
        title.setFont(new Font("Courier New", Font.BOLD, 65));
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(25, 25, 25, 25));
        titlePanel.setBackground(new Color(0, 229, 31));
        titlePanel.add(title);
        subTopPanel.add(titlePanel);

        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightTopPanel.setBackground(new Color(0, 229, 31));
        backButton = new JButton();
        backButton.setIcon(new ImageIcon("back button icon.png"));
        backButton.setBorder(new EmptyBorder(20,0,0,20));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);
        rightTopPanel.add(backButton);
        subTopPanel.add(rightTopPanel);

        borderPanel.add(subTopPanel, BorderLayout.NORTH);

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
        // Sort the course list alphabetically
        userAccount.bubbleSortCourseList();
        //Retrieve courses
        ArrayList<Course> courseList = userAccount.getCourseList();

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
        ArrayList<Course> courseList = userAccount.getCourseList();

        // Linear search through courses to find out the selected course
        for(Course course : courseList){
            // Return course if course's name matches button text
            if(course.getName().equals(name)){
                return course;
            }
        }
        return null;
    }

    private void calculateAndDisplayAverage() {
        // Calculate the average score of all classes combined
        double totalScore = 0;
        double courseAverage;
        int totalCourses = 0;

        if(HomeScreen.userAccount.getCourseList().size() == 0){
            JOptionPane.showMessageDialog(null, "No courses have been added yet.", "Overall Average", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Iterate over all courses
        for (Course course : HomeScreen.userAccount.getCourseList()) {
            courseAverage = course.calculateAverageScore();
            if(courseAverage != -1){
                totalScore += courseAverage;
                totalCourses++;
            }
        }

        // Calculate the average score
        double averageScore = totalCourses > 0 ? totalScore / totalCourses : 0;
        //rounded
        double averageScoreRounded= Math.round(averageScore * 100) / 100.0;

        // Display the overall average score in JDialog
        JOptionPane.showMessageDialog(null, "Overall average for all classes combined: " + averageScoreRounded+"%", "Overall Average", JOptionPane.INFORMATION_MESSAGE);
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
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            // Go to home screen
            dispose();
            new HomeScreen(HomeScreen.userAccount);
        }
        else if(e.getSource() == calculateAvgButton){
            // Calculate overall average and display it
            calculateAndDisplayAverage();
        }
        else if(e.getSource() == addClassButton){
            // Go to AddClassScreen
            dispose();
            new AddClassScreen();
        }
        else{
            // Get button that was clicked
            JButton button = (JButton)e.getSource();
            // Search for course by course name
            Course course = linearSearchCourse(button.getText());
            // Go to corresponding course screen
            new SpecificClassScreen(course);
            dispose();
        }
    }
}