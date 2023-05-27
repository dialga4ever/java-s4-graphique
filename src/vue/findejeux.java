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
        frame.setResizable(false);
        frame.setSize(200,100);
        ImageIcon img = new ImageIcon("src/vue/image/$.png");
        frame.setIconImage(img.getImage());
        Container container =frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.revalidate();
        container.repaint();
        
        Occupant o=j.getwinner();
        ///Zone du plateaux
        JLabel l=new JLabel("Fin de jeux winner : "+o.getRepresentation());

        container.add(l);

        //JLabel l2=new JLabel("");
        
        //container.add(l2);
        //JLabel panel = new JLabel();

        //ImageIcon pic = new ImageIcon("A.jpg");
        //panel.setIcon(pic);
        
        //panel.setPreferredSize(new Dimension(100, 100));
        //container.add(panel);


        container.revalidate();
        container.repaint();
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);




    }

}
