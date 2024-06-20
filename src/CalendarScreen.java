import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class CalendarScreen extends JFrame implements ActionListener {
    private JPanel parentPanel;
    private JButton box1;
    private JButton box2;
    private JButton box3;
    private JButton box4;
    private JButton box5;
    private JButton box6;
    private JButton box7;
    private JButton box8;
    private JButton box9;
    private JButton box10;
    private JButton box11;
    private JButton box12;
    private JButton box13;
    private JButton box14;
    private JButton box15;
    private JButton box16;
    private JButton box17;
    private JButton box18;
    private JButton box19;
    private JButton box20;
    private JButton box21;
    private JButton box22;
    private JButton box23;
    private JButton box24;
    private JButton box25;
    private JButton box26;
    private JButton box27;
    private JButton box28;
    private JButton box29;
    private JButton box30;
    private JButton box31;
    private JButton box32;
    private JButton box33;
    private JButton box44;
    private JButton box35;
    private JLabel sundayLabel;
    private JLabel mondayLabel;
    private JLabel tuesdayLabel;
    private JLabel wednesdayLabel;
    private JLabel thursdayLabel;
    private JLabel fridayLabel;
    private JLabel saturdayLabel;
    private JLabel monthLabel;
    private JButton nextMonthButton;
    private JButton previousMonthButton;
    private JButton box36;
    private JButton box37;
    private JButton box38;
    private JButton box39;
    private JButton box40;
    private JButton box41;
    private JButton box42;
    private JButton backButton;
    private JLabel dayOfWeekLabel;
    private JLabel dateLabel;
    private JPanel checkBoxPanel;
    private JButton addButton;
    private LocalDate currentDate;
    private JButton[] dayButtons = {box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box11, box12, box13,
            box14, box15, box16, box17, box18, box19, box20, box21, box22, box23, box24, box25, box26, box27, box28,
            box29, box30, box31, box32, box33, box44, box35, box36, box37, box38, box39, box40, box41, box42};
    private JLabel[] dayHeaders = {sundayLabel, mondayLabel, tuesdayLabel, wednesdayLabel, thursdayLabel, fridayLabel, saturdayLabel};

    public CalendarScreen(){
        currentDate = LocalDate.now();
        initializeFrame();
        updateCalendar();
        updateSidePanel();
        controlButtons();
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

        for(JLabel label : dayHeaders){
            label.setFont(new Font("Arial", Font.PLAIN, 30));
        }
        for(JButton button : dayButtons){
            button.addActionListener(this);
        }

        checkBoxPanel.setLayout(new GridLayout(20, 1));
        checkBoxPanel.setBackground(new Color(134,163,141));

        nextMonthButton.setBackground(Color.LIGHT_GRAY);
        previousMonthButton.setBackground(Color.LIGHT_GRAY);
    }

    private void controlButtons(){
        previousMonthButton.addActionListener(this);
        nextMonthButton.addActionListener(this);

        backButton.setIcon(new ImageIcon("back button icon.png"));
        backButton.setBorder(new EmptyBorder(0, 0, 0, 30));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);

        addButton.setIcon(new ImageIcon("add icon.png"));
        addButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        addButton.setFocusPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.addActionListener(this);
    }

    private void updateCalendar(){
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        monthLabel.setText(Month.of(month).getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + year);

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        for(JButton button : dayButtons){
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.putClientProperty("date", null);
            button.setBackground(Color.WHITE);
            button.setText("");
        }

        LocalDate date = firstDayOfMonth;
        int startIndex = startDayOfWeek;

        Account account = HomeScreen.userAccount;
        ArrayList<Course> courses = account.getCourseList();
        ArrayList<Activity> activities = account.getActivityList();

        for(int day = 1; day <= daysInMonth; day++){
            dayButtons[startIndex].setText(String.valueOf(day));
            dayButtons[startIndex].putClientProperty("date", date);

            boolean hasEvaluation = false;
            boolean hasActivity = false;

            for(Course course : courses){
                for(Evaluation eval : course.getEvaluations()){
                    LocalDate evalDate = eval.getEvaluationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    // Check if date has any evaluations
                    if(evalDate.equals(date)){
                        hasEvaluation = true;
                        break;
                    }
                }
                if(hasEvaluation){
                    break;
                }
            }
            if(!hasEvaluation){
                for(Activity activity : activities){
                    LocalDate activityDate = activity.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    // Check if date has any activities
                    if(activityDate.equals(date)){
                        hasActivity = true;
                        break;
                    }
                }
            }

            // Highlight date if there are any activities or evaluations on it
            if (hasEvaluation || hasActivity) {
                dayButtons[startIndex].setBackground(new Color(202,229,226)); // Color for evaluations
            }

            startIndex++;
            date = date.plusDays(1);
        }
    }

    private void updateSidePanel(){
        checkBoxPanel.removeAll();

        // Set side panel header with date
        dayOfWeekLabel.setText(currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        dateLabel.setText(currentDate.format(dateFormat));

        Account account = HomeScreen.userAccount;
        ArrayList<Course> courses = account.getCourseList();
        ArrayList<Evaluation> evaluations;
        LocalDate convertedDate;
        ZoneId zoneId = ZoneId.systemDefault();

        for(Course course : courses){
            evaluations = course.getEvaluations();
            for(Evaluation evaluation : evaluations){
                // Convert evaluation Date to LocalDate
                convertedDate = evaluation.getEvaluationDate().toInstant().atZone(zoneId).toLocalDate();
                // Compare dates
                if(convertedDate.isEqual(currentDate)){
                    JCheckBox checkBox = new JCheckBox(" " + evaluation.getEvaluationName() + " (" + course.getCode() + ")");
                    checkBox.setFont(new Font("Arial", Font.BOLD, 28));
                    checkBox.setBackground(new Color(134,163,141));
                    checkBoxPanel.add(checkBox);
                }
            }
        }

        ArrayList<Activity> activities = account.getActivityList();
        for(Activity activity : activities){
            // Convert evaluation Date to LocalDate
            convertedDate = activity.getDate().toInstant().atZone(zoneId).toLocalDate();
            // Compare dates
            if(convertedDate.isEqual(currentDate)){
                JCheckBox checkBox = new JCheckBox(" " + activity.getName());
                checkBox.setFont(new Font("Arial", Font.BOLD, 28));
                checkBox.setBackground(new Color(134,163,141));
                checkBoxPanel.add(checkBox);
            }
        }

        revalidate();
        repaint();
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
        if(e.getSource() == previousMonthButton){
            currentDate = currentDate.minusMonths(1);
            updateCalendar();
            updateSidePanel();
        }
        else if(e.getSource() == nextMonthButton){
            currentDate = currentDate.plusMonths(1);
            updateCalendar();
            updateSidePanel();
        }
        else if(e.getSource() == backButton){
            dispose();
            new HomeScreen(HomeScreen.userAccount);
        }
        else if(e.getSource() == addButton){
            dispose();
            new AddActivityScreen();
        }
        else{
            JButton clickedButton = (JButton) e.getSource();
            LocalDate selectedDate = (LocalDate) clickedButton.getClientProperty("date");
            if(selectedDate != null){
                currentDate = selectedDate;
                updateSidePanel();
            }
        }
    }

    public static void main(String[] args) {
        new CalendarScreen();
    }
}