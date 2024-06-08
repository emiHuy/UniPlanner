import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class essayPlanner  extends JFrame {
    private JPanel essayPanel;
    private JButton back;

    public essayPlanner() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setTitle("Essay Planner");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the window instead of exiting the application
        setVisible(true); // Make the window visible
        setContentPane(essayPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to be maximized
    }

}


