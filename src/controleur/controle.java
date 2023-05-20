package controleur;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import vue.TexteAction;

import modele.Jeux;
import vue.grilleDeJeux;

import java.util.*;

public class controle {
    private JLabel l;
    private Jeux j;
    private JPanel Zone;
    private boolean win=false;
    private TexteAction t;
    public controle(grilleDeJeux Zone,TexteAction TextAction){
        this.t=TextAction;
        this.j=Zone.getJ();
        this.Zone=Zone;
        String[] choix = {
            "Paramètre custom",
            "Paramètre par défaut",
            "Preset A",
            "Preset B",
            "Preset C",
            "Preset D"
        };
        ChoixPanel(choix,Zone);
        while(!j.getIntialized()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Zone.removeAll();
        


        
        
    }
    public void generateAvancement(JPanel NORTH){
        
        NORTH.setLayout(new GridLayout());
        JButton b=new JButton("Tour suivant!");
        JButton b_quitter=new JButton("Quitter");

        ActionListener ecbq=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        };

        ActionListener nextTour=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean w =j.nextTour();
                win=w;
                
                Zone.repaint();
            }
        };
        b.addActionListener(nextTour);
        ActionListener oneTurn=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean w = j.oneTurn();
                win=w;
                Zone.repaint();
                t.updateLabel();
            }
        };
        JButton turn=new JButton("Joué un perso");
        turn.addActionListener(oneTurn);
        NORTH.add(turn);


        
        NORTH.setLayout(new GridLayout());
        NORTH.setVisible(true);
        NORTH.add(b);
        b_quitter.addActionListener(ecbq);
        NORTH.add(b_quitter);
        NORTH.validate();
    }


    public void ChoixPanel(String[] choix,JPanel Zone){
        JButton[] buttons = new JButton[choix.length];
        Zone.setLayout(new GridLayout(choix.length, 1));
        

        for (int i = 0; i < choix.length; i++) {
            buttons[i] = new JButton(choix[i]);
            buttons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //recupere l'index de l'element selectionné
                    int index=Arrays.asList(choix).indexOf(e.getActionCommand());
                    System.out.println(index);
                    j.initialisation(index);
                }
            });
            Zone.add(buttons[i]);
            Zone.validate();
            Zone.repaint();
        }
        Zone.validate();
        Zone.repaint();
    }

    /**
     * @return JLabel return the l
     */
    public JLabel getLabel() {
        return l;
    }
    
    /**
     * @param l the JLabel to set
     */
    public void setLabel(JLabel l){
        this.l=l;
    }


    /**
     * @param j the j to set
     */
    public void setJ(Jeux j) {
        this.j = j;
    }

    /**
     * @return JPanel return the Zone
     */
    public JPanel getZone() {
        return Zone;
    }

    /**
     * @param Zone the Zone to set
     */
    public void setZone(JPanel Zone) {
        this.Zone = Zone;
    }

}
