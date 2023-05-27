package controleur;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import vue.TexteAction;
import vue.fenettre;
import vue.findejeux;
import modele.Jeux;
import vue.grilleDeJeux;

import java.util.*;

/**
 * The "controle" class contains methods for generating a graphical user interface for a treasure hunt
 * game and creating a panel with buttons based on an array of choices.
 */
public class controle {
    private fenettre f;
    private JLabel l;
    private Jeux j;
    private JPanel Zone;
    private TexteAction t;
    public controle(grilleDeJeux Zone,TexteAction TextAction,fenettre f){
        this.f=f;
        this.t=TextAction;
        this.j=Zone.getJ();
        this.Zone=Zone;
        String[] choix = {
            "Paramètre custom",
            "Paramètre par défaut",
            "Preset A rebond mur et pierre",
            "Preset B sur un mur",
            "Preset C carte de redirection",
            "Preset D glue et contournement"
        };
        ChoixPanel(choix,Zone,f);

    }
    /**
     * This function generates a graphical user interface for a treasure hunt game with buttons to
     * advance to the next turn or quit the game.
     * 
     * @param NORTH A JPanel object that will be used to display the game interface.
     */
    public void generateAvancement(JPanel NORTH){
        
        NORTH.setLayout(new GridLayout());
        JButton b=new JButton("Tour suivant!");
        JButton b_quitter=new JButton("Quitter");
        
        b.setBackground(Color.WHITE);
        b_quitter.setBackground(Color.WHITE);
        ActionListener ecbq=new ActionListener(){
            boolean confirm=false;
            public void actionPerformed(ActionEvent e){
                if(confirm){
                    System.exit(0);
                }
                else{
                    confirm=true;
                    b_quitter.setText("Confirmé");
                }
                
            }
        };

        ActionListener nextTour=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean w =j.nextTour();
                
                Zone.repaint();
                if(w){
                    NORTH.removeAll();
                    NORTH.setLayout(new GridLayout());
                    NORTH.add(b_quitter);
                    NORTH.validate();
                    NORTH.repaint();
                    findejeux f=new findejeux(j);
                }
                f.setTitle("Chasse au trésor - Tour "+j.getNbrTour());
            }
        };
        b.addActionListener(nextTour);
        ActionListener oneTurn=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                boolean w = j.oneTurn();
                Zone.repaint();
                t.updateLabel();
                if(w){
                    NORTH.removeAll();
                    NORTH.setLayout(new GridLayout());
                    NORTH.add(b_quitter);
                    NORTH.validate();
                    NORTH.repaint();
                    findejeux f=new findejeux(j);
                }
                f.setTitle("Chasse au trésor - Tour "+j.getNbrTour());
            }
        };
        JButton turn=new JButton("Jouer un perso");
        turn.addActionListener(oneTurn);
        NORTH.add(turn);

        turn.setBackground(Color.WHITE);
        
        NORTH.setLayout(new GridLayout());
        NORTH.setVisible(true);
        NORTH.add(b);
        b_quitter.addActionListener(ecbq);
        NORTH.add(b_quitter);
        NORTH.validate();
    }


    /**
     * This function creates a panel with buttons based on an array of choices, and when a button is
     * clicked, it retrieves the index of the selected choice and performs some actions.
     * 
     * @param choix An array of Strings representing the choices to be displayed on the buttons.
     * @param Zone A JPanel where the buttons will be added and displayed.
     * @param f It seems that "f" is an instance of a class called "fenettre". It is likely that this
     * class represents a window or a frame in a graphical user interface (GUI) and is used to
     * initialize and display various components such as panels, buttons, etc.
     */
    public void ChoixPanel(String[] choix,JPanel Zone,fenettre f){
        JButton[] buttons = new JButton[choix.length];
        Zone.setLayout(new GridLayout(choix.length, 1));
        for (int i = 0; i < choix.length; i++) {
            

            buttons[i] = new JButton(choix[i]);
            buttons[i] .setBackground(Color.WHITE);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //recupere l'index de l'element selectionné
                    int index=Arrays.asList(choix).indexOf(e.getActionCommand());
                    System.out.println(index);
                    Zone.removeAll();
                    f.inizialitationZoneDeJeux();
                    j.initialisation(index,f);
                    

                    
                    Zone.validate();
                    Zone.repaint();

                }
            });
            Zone.add(buttons[i]);
            Zone.validate();
            Zone.repaint();
        }
        Zone.validate();
        Zone.repaint();
        Zone.setVisible(true);
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
