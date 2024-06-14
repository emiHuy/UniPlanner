import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
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
        this.savedData= new HashMap<>(savedData);
        initializeFrame();
        addActionListeners();
        windowListener();
        loadSavedData();
    }

    private void initializeFrame() {
        setSize(1200, 1000);
        setContentPane(calendar);
        setTitle("calendar");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized

    }

    private void addActionListeners() {
        backButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    private void windowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
                UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 24));
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

    private void loadSavedData() {
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

        HomeScreen.userAccount.updateCalendarData(savedData);

        // Show a pop-up message to indicate that the data has been saved
        JOptionPane.showMessageDialog(this, "Data has been saved successfully!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Go to HomeScreen
            dispose();
            new HomeScreen(HomeScreen.userAccount);
        }
        else if (e.getSource() == saveButton) {
            saveInfo();
        }
    }
}

