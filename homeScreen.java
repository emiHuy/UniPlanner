import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homeScreen extends JFrame {
    private JPanel homePanel;
    private JButton classes;
    private JButton calendar;
    private JButton studyGuide;
    private JButton xButton;

    public homeScreen() {

    //initialize frame



        classes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        calendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        studyGuide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "confirm", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        homeScreen h=new homeScreen();
        h.setSize(900, 1000);
        h.setContentPane(h.homePanel);
        h.setTitle("home screen");
        h.setVisible(true);
        h.setLocationRelativeTo(null);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
