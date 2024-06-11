import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    private void initializeFrame(){
        setSize(1200, 1000);
        setContentPane(calendar);
        setTitle("calendar");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized
    }

    private void addActionListeners(){
        backButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            new HomeScreen(HomeScreen.userAccount);
            dispose();
        }
        else if(e.getSource() == saveButton){
            // save info/changes
        }
    }
}
