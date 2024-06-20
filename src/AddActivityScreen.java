import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivityScreen extends JFrame implements ActionListener {
    private JButton backButton;
    private JLabel header;
    private JTextField nameField;
    private JTextField dateField;
    private JButton addButton;
    private JPanel parentPanel;

    public AddActivityScreen(){
        initializeFrame();
        setupComponents();
        windowListener();
    }

    private void initializeFrame(){
        setSize(1920, 1080);
        setVisible(true);
        setTitle("MnM Uni Planner");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        add(parentPanel);
        setContentPane(parentPanel);
    }

    private void setupComponents(){
        header.setBorder(new EmptyBorder(40,0,40,0));

        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.addActionListener(this);

        backButton.setIcon(new ImageIcon("back button icon.png"));
        backButton.setBorder(new EmptyBorder(20,0,20,30));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);
    }

    private void collectInput(){
        // Collect user input from text fields
        String activityName = nameField.getText().trim();
        String activityDateStr = dateField.getText().trim();

        Date activityDate = convertDate(activityDateStr);

        // If any fields are empty, display error message and prompt
        if (activityName.isEmpty() || activityDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if (!activityName.matches("^[a-zA-Z0-9\\-\\s]*$")){
            JOptionPane.showMessageDialog(this, "Input may only consist of letters, numbers, spaces, and/or dashes.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // If data is invalid, display error message and prompt
        else if (activityDate == null) {
            JOptionPane.showMessageDialog(this, "Please enter a valid date in the format yyyy-MM-dd.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            // Create a new evaluation object
            Activity activity = new Activity(activityName, activityDate);
            // Add the new evaluation to the specific course
            HomeScreen.userAccount.addActivity(activity);
            new CalendarScreen();
            dispose();
        }
    }

    // Method to check if the date is valid and get date value
    private Date convertDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date date;
        try {
            // Convert date from string to date type
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    private void windowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 27));
                UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 27));
                // Ask user to confirm exit when clicking exit button
                int exitResponse = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (exitResponse == JOptionPane.YES_OPTION) {
                    // If user confirms exit, save data before closing window
                    FileOperations.saveData();
                    dispose();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            new CalendarScreen();
            dispose();
        }
        else if(e.getSource() == addButton){
            collectInput();
        }
    }
}

