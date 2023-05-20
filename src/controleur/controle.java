package controleur;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import modele.Jeux;

import java.util.*;

public class controle {
    Jeux j;
    JPanel Zone;
    public controle(Jeux j,JPanel Zone){
        this.j=j;
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
                j.nextTour();
                Zone.repaint();
            }
        };
        b.addActionListener(nextTour);
        ActionListener oneTurn=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                j.oneTurn();
                Zone.repaint();
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
    }
}
