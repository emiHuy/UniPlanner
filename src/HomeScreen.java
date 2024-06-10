import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class HomeScreen implements ActionListener{
    private JFrame window;
    private JPanel homePanel;
    private JButton classesButton;
    private JButton calendarButton;
    private JButton studyGuideButton;
    private JButton xButton;
    private  Map<String, String> savedData;

    public HomeScreen() {
        initializeFrame();
        addActionListeners();
        this.savedData = new HashMap<>();
    }

    private void initializeFrame(){
        window = new JFrame();
        window.add(homePanel);
        window.setContentPane(homePanel);
        window.setVisible(true);
        window.setTitle("MnM Uni Planner");
        window.setLocationRelativeTo(null);
        window.setSize(1200, 1000);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addActionListeners(){
        classesButton.addActionListener(this);
        calendarButton.addActionListener(this);
        studyGuideButton.addActionListener(this);
        xButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == classesButton){
            new ClassesScreen();
            window.dispose();
        }
        else if(e.getSource() == calendarButton){

            System.out.print("load:"+savedData.toString());
            new Calendar(savedData);
            //window.dispose();
        }
        else if(e.getSource() == studyGuideButton){
            new StudyGuide();
            window.dispose();
        }
        else if(e.getSource() == xButton){
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }}}


