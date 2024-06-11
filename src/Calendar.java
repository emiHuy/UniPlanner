import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calendar extends JFrame implements ActionListener{
    private JPanel calendar;
    private JButton backButton;
    private JEditorPane editorPane1;
    private JEditorPane editorPane2;
    private JEditorPane editorPane3;
    private JEditorPane editorPane4;
    private JEditorPane editorPane5;
    private JEditorPane editorPane6;
    private JEditorPane editorPane7;
    private JEditorPane editorPane8;
    private JEditorPane editorPane9;
    private JEditorPane editorPane10;
    private JEditorPane editorPane11;
    private JEditorPane editorPane12;
    private JEditorPane editorPane13;
    private JEditorPane editorPane14;
    private JButton saveButton;

    public Calendar() {
        initializeFrame();
        addActionListeners();
        windowListener();
    }

    private void initializeFrame(){
        setSize(1200, 1000);
        setContentPane(calendar);
        setTitle("calendar");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized
    }

    private void addActionListeners(){
        backButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    private void windowListener(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 24));
                UIManager.put("OptionPane.buttonFont", new Font("Courier New", Font.BOLD, 24));
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
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            // Go to HomeScreen
            new HomeScreen(HomeScreen.userAccount);
            dispose();
        }
        else if(e.getSource() == saveButton){
            // save info/changes
        }
    }
}
