import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Calendar extends JFrame implements ActionListener {
    private JPanel calendar;
    private JButton backButton;
    private JButton saveButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;
    private JTextArea textArea6;
    private JTextArea textArea7;
    private JTextArea textArea8;
    private JTextArea textArea9;
    private JTextArea textArea10;
    private JTextArea textArea11;
    private JTextArea textArea12;
    private JTextArea textArea13;
    private JTextArea textArea14;
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
        textArea1.setText(savedData.get("TextArea1"));
        textArea2.setText(savedData.get("TextArea2"));
        textArea3.setText(savedData.get("TextArea3"));
        textArea4.setText(savedData.get("TextArea4"));
        textArea5.setText(savedData.get("TextArea5"));
        textArea6.setText(savedData.get("TextArea6"));
        textArea7.setText(savedData.get("TextArea7"));
        textArea8.setText(savedData.get("TextArea8"));
        textArea9.setText(savedData.get("TextArea9"));
        textArea10.setText(savedData.get("TextArea10"));
        textArea11.setText(savedData.get("TextArea11"));
        textArea12.setText(savedData.get("TextArea12"));
        textArea13.setText(savedData.get("TextArea13"));
        textArea14.setText(savedData.get("TextArea14"));
        //System.out.print("load date:"+savedData.get"TextArea1" ""));
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
        savedData.put("TextArea1", textArea1.getText());
        savedData.put("TextArea2", textArea2.getText());
        savedData.put("TextArea3", textArea3.getText());
        savedData.put("TextArea4", textArea4.getText());
        savedData.put("TextArea5", textArea5.getText());
        savedData.put("TextArea6", textArea6.getText());
        savedData.put("TextArea7", textArea7.getText());
        savedData.put("TextArea8", textArea8.getText());
        savedData.put("TextArea9", textArea9.getText());
        savedData.put("TextArea10", textArea10.getText());
        savedData.put("TextArea11", textArea11.getText());
        savedData.put("TextArea12", textArea12.getText());
        savedData.put("TextArea13", textArea13.getText());
        savedData.put("TextArea14", textArea14.getText());

        // Print the saved data for verification
        savedData.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
        // Show a pop-up message to indicate that the data has been saved
        JOptionPane.showMessageDialog(this, "Data has been saved successfully!");
    }
}

