import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class Calendar extends JFrame implements ActionListener {
    private JPanel calendar;
    private JButton backButton;
    private JButton saveButton;
    private JTextArea mondayBox;
    private JTextArea mondayBox2;
    private JTextArea tuesdayBox;
    private JTextArea tuesdayBox2;
    private JTextArea wednesdayBox;
    private JTextArea wednesdayBox2;
    private JTextArea thursdayBox;
    private JTextArea thursdayBox2;
    private JTextArea fridayBox;
    private JTextArea sundayBox;
    private JTextArea saturdayBox;
    private JTextArea fridayBox2;
    private JTextArea saturdayBox2;
    private JTextArea sundayBox2;
    private  Map<String, String> savedData;
    public Calendar(Map<String, String> savedData) {
        this.savedData=savedData;
        initializeFrame();
        addActionListeners();
        loadSavedData();
    }

    private void initializeFrame() {
        setSize(1200, 1000);
        setContentPane(calendar);
        setTitle("calendar");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized

    }

    private void addActionListeners() {
        backButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    private void loadSavedData() {
        System.out.println("Loading saved data...");
        mondayBox.setText(savedData.get("TextArea1"));
        mondayBox2.setText(savedData.get("TextArea2"));
        tuesdayBox.setText(savedData.get("TextArea3"));
        tuesdayBox2.setText(savedData.get("TextArea4"));
        wednesdayBox.setText(savedData.get("TextArea5"));
        wednesdayBox2.setText(savedData.get("TextArea6"));
        thursdayBox.setText(savedData.get("TextArea7"));
        thursdayBox2.setText(savedData.get("TextArea8"));
        fridayBox.setText(savedData.get("TextArea9"));
        sundayBox.setText(savedData.get("TextArea10"));
        saturdayBox.setText(savedData.get("TextArea11"));
        fridayBox2.setText(savedData.get("TextArea12"));
        saturdayBox2.setText(savedData.get("TextArea13"));
        sundayBox2.setText(savedData.get("TextArea14"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            //new HomeScreen();
            dispose();
        } else if (e.getSource() == saveButton) {
            saveInfo();
        }
    }

    private void saveInfo() {
        savedData.put("TextArea1", mondayBox.getText());
        savedData.put("TextArea2", mondayBox2.getText());
        savedData.put("TextArea3", tuesdayBox.getText());
        savedData.put("TextArea4", tuesdayBox2.getText());
        savedData.put("TextArea5", wednesdayBox.getText());
        savedData.put("TextArea6", wednesdayBox2.getText());
        savedData.put("TextArea7", thursdayBox.getText());
        savedData.put("TextArea8", thursdayBox2.getText());
        savedData.put("TextArea9", fridayBox.getText());
        savedData.put("TextArea10", sundayBox.getText());
        savedData.put("TextArea11", saturdayBox.getText());
        savedData.put("TextArea12", fridayBox2.getText());
        savedData.put("TextArea13", saturdayBox2.getText());
        savedData.put("TextArea14", sundayBox2.getText());

        // Print the saved data for verification
        savedData.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
        // Show a pop-up message to indicate that the data has been saved
        JOptionPane.showMessageDialog(this, "Data has been saved successfully!");
    }
}

