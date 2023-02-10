package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShiFuMi extends Application{

    private ImageIcon background;
    private Image image;
    JFrame frame = new JFrame();
    JPanel panelStart = new JPanel();
    JPanel panelUser = new JPanel();

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);

        /*frame.setContentPane(panelStart);*/

        frame.setSize(980,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        ImageIcon imageIcon = new ImageIcon("src/main/java/images/fondecranAccueil.png");

        JLabel background = new JLabel("",imageIcon,JLabel.CENTER);
        background.setBounds(0,0,0,0);

        /*JLabel test = new JLabel("Salut");
        test.setBounds(0,0,500,50);
        test.setForeground(Color.BLUE);
        frame.add(test);*/

        Icon iconStart = new ImageIcon("src/main/java/images/btnStart.png");
        JButton btn = new JButton(iconStart);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBounds(430,320,120,50);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        /*btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectionButtonPressed();
            }
        } );*/
        frame.add(btn);
        frame.add(background);



        frame.setVisible(true);


        /*stage.setScene(scene);

        stage.show();
*/

    }

}