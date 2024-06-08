import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Classes extends JFrame {
    private JFrame classesFrame;
    private JPanel viewClassesPanel;
    private JButton calculateAvgButton;
    private JButton addClassButton;

    public Classes(){
        initializeFrame();
        setupTopPanel();
        setupBottomPanel();
        setupCentralPanel();
        classButtons();
    }

    private void initializeFrame() {
        classesFrame = new JFrame();
        classesFrame.setSize(1280, 720);
        classesFrame.setVisible(true);
        classesFrame.setTitle("MnM Uni Planner");
        classesFrame.setLocationRelativeTo(null);
        classesFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        classesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        classesFrame.setLayout(new BorderLayout());
    }

    private void createPlannerName(JPanel panel){
        JLabel plannerName = new JLabel("MnM Uni Planner");
        plannerName.setFont(new Font("Courier New", Font.PLAIN, 25));
        plannerName.setForeground(new Color(0,229,31));
        plannerName.setBorder(new EmptyBorder(50,50,50,50));
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
        bottomPanel.setBackground(Color.BLACK);
        createPlannerName(bottomPanel);
        classesFrame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createButton (String text, int xSize, int ySize){
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.LIGHT_GRAY);
        button.setPreferredSize(new Dimension(xSize, ySize));
        button.setFont(new Font("Arial", Font.PLAIN, 32));
        return button;
    }

    private void setupCentralPanel(){
        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBorder(new EmptyBorder(200,300,200,300));
        borderPanel.setBackground(Color.BLACK);
        classesFrame.add(borderPanel, BorderLayout.CENTER);

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Your Classes This Semester");
        title.setFont(new Font("Courier New", Font.BOLD, 65));
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(50, 20, 50, 20));
        titlePanel.setBackground(new Color(0, 229, 31));
        titlePanel.add(title);
        borderPanel.add(titlePanel, BorderLayout.NORTH);

        viewClassesPanel = new JPanel();
        viewClassesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
        borderPanel.add(viewClassesPanel, BorderLayout.CENTER);

        JPanel avgPanel = new JPanel();
        avgPanel.setBorder(new EmptyBorder(20,0,20,0));
        calculateAvgButton = createButton("AVERAGE CALCULATOR", 500, 50);
        avgPanel.add(calculateAvgButton);
        borderPanel.add(avgPanel, BorderLayout.SOUTH);
    }

    private void classButtons(){
        ArrayList<Course> courseList = new ArrayList<Course>();

        /*
        // Demo classes
        Course One = new Course("Organic Chemistry", "1");
        Course Two = new Course("Algorithms", "1");
        Course A = new Course("Statistics", "1");
        Course B = new Course("Economics", "1");
        Course C = new Course("Biology", "1");
        Course D = new Course("Computer Science", "1");
        Course E = new Course("Calculus II", "1");
        Course F = new Course("Human Anatomy", "1");
        Course G = new Course("A Random Course", "1");
        Course H = new Course("Testing", "1");

        courseList.add(One);
        courseList.add(Two);
        courseList.add(A);
        courseList.add(B);
        courseList.add(C);
        courseList.add(D);
        courseList.add(E);
        courseList.add(F);
        courseList.add(G);
        courseList.add(H);
        */

        ArrayList<JButton> classButtons = new ArrayList<JButton>();
        for(Course course : courseList){
            JButton button = createButton(course.getName(), 300, 50);
            classButtons.add(button);
            viewClassesPanel.add(button);
        }
        addClassButton = createButton("+ Add Class", 300, 50);
        viewClassesPanel.add(addClassButton);
    }
}
