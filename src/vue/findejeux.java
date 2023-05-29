package vue;
import java.awt.*;
import javax.swing.*;
import controleur.controle;
import modele.Jeux;
import modele.occupant.Occupant;

import java.io.File;

/**
 * The "findejeux" class creates a JFrame to display the winner of a game.
 */
public class findejeux{
    public findejeux(Jeux j){

        JFrame frame = new JFrame("Fin de jeux");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setTitle("Chasse au trésor");
        frame.setSize(300,150);
        frame.setResizable(false);
        ImageIcon img = new ImageIcon("src/vue/image/$.png");
        frame.setIconImage(img.getImage());
        Container container =frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.revalidate();
        container.repaint();


        Occupant o = j.getwinner();
        JLabel l = new JLabel("Fin de jeux winner: " + o.getRepresentation());
        container.add(l, BorderLayout.NORTH);

        JLabel panel = new JLabel();
        ImageIcon pic = new ImageIcon("src/vue/image/A.png");
        panel.setIcon(pic);
        panel.setPreferredSize(new Dimension(100, 100));
        container.add(panel, BorderLayout.CENTER);


        container.revalidate();
        container.repaint();
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);




    }

}
