package vue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modele.Jeux;
import controleur.controle;
import modele.occupant.*;
import modele.occupant.objetMobile.ObjetMobile;

import java.util.List;
public class fenettre {


    public static void main(String[] args){
        JFrame f=new JFrame();
        f.setResizable(false);
        f.setSize(816,891);
        f.setVisible(true);
        Container c =f.getContentPane();
        c.setLayout(new BorderLayout());

        ///Zone du plateaux
        String text="";
        JLabel l=new JLabel(""+text,JLabel.CENTER );
        TexteAction t=new TexteAction(l,text);
        grilleDeJeux CENTER=new grilleDeJeux(t);

        c.add(CENTER);
        controle c1=new controle(CENTER,t);
        CENTER.creeGrilleDeJeux();

        

        //zone du bas
        

        c1.setLabel(l);
        c.add(l,BorderLayout.SOUTH);

        JPanel NORTH=new JPanel();
        NORTH.setVisible(true);
        f.add(NORTH,BorderLayout.NORTH);
        c1.generateAvancement(NORTH);

        c.revalidate();
        




        


        


        //met à jour la Zone
        CENTER.validate();
        CENTER.repaint();
        
        
    }
}
