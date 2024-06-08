import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClassesScreen extends JFrame implements ActionListener{
    private JFrame classesFrame;
    private JPanel viewClassesPanel;
    private JButton calculateAvgButton;
    private JButton addClassButton;
    private JButton backButton;
    private ArrayList<JButton> classButtons = new ArrayList<JButton>();

    public ClassesScreen(){
        initializeFrame();
        setupTopPanel();
        setupBottomPanel();
        setupCentralPanel();
        createClassButtons();
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
        button.setFont(new Font("Arial", Font.PLAIN, 32));
        button.addActionListener(this);
        return button;
    }

    private void setupCentralPanel(){

        // Main central panel
        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBorder(new EmptyBorder(200,300,200,300));
        borderPanel.setBackground(Color.BLACK);
        classesFrame.add(borderPanel, BorderLayout.CENTER);

        // Sub-panel for header
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Your Classes This Semester");
        title.setFont(new Font("Courier New", Font.BOLD, 65));
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(50, 20, 50, 20));
        titlePanel.setBackground(new Color(0, 229, 31));
        titlePanel.add(title);
        borderPanel.add(titlePanel, BorderLayout.NORTH);

        // Sub-panel for classes display
        viewClassesPanel = new JPanel();
        viewClassesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
        borderPanel.add(viewClassesPanel, BorderLayout.CENTER);

        // Sub-panel for overall average calculator button
        JPanel avgPanel = new JPanel();
        avgPanel.setBorder(new EmptyBorder(20,0,20,0));
        calculateAvgButton = createButton("AVERAGE CALCULATOR", 500, 50, Color.LIGHT_GRAY);
        avgPanel.add(calculateAvgButton);
        borderPanel.add(avgPanel, BorderLayout.SOUTH);
    }

    private void createClassButtons(){
        ArrayList<Course> courseList = new ArrayList<Course>();

        // Set up buttons for each class
        for(Course course : courseList){
            JButton button = createButton(course.getName(), 300, 50, Color.LIGHT_GRAY);
            classButtons.add(button);
            viewClassesPanel.add(button);
        }

        // Set up button for adding a class
        addClassButton = createButton("+ Add Class", 300, 50, new Color(0,229,31));
        viewClassesPanel.add(addClassButton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            classesFrame.dispose();
            new homeScreen();
        }
        else if(e.getSource() == calculateAvgButton){
            // display dialog with calculated average or new screen?
        }
        else if(e.getSource() == addClassButton){
            // go to add class screen
        }
        else{
            // check which specific class button was clicked
            // proceed to corresponding class screen
        }
    }
}