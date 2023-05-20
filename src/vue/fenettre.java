package vue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modele.Jeux;
import controleur.controle;
import modele.occupant.*;
import modele.occupant.objetMobile.ObjetMobile;

import java.util.List;
import java.awt.image.BufferedImage;
public class fenettre {

    public static void createGrid(Jeux j, JPanel Zone) {
        Zone.setLayout(new GridLayout(j.getG().getMaxX(), j.getG().getMaxY()));

        for (int i = 0; i < j.getG().getMaxX(); i++) {
            for (int k = 0; k < j.getG().getMaxY(); k++) {
                final int rowIndex = i;
                final int colIndex = k;
                JPanel panel = new JPanel() {
                    
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        
                        
                        // Dessiner l'image d'herbe en arrière-plan
                        ImageIcon grassIcon = new ImageIcon("src\\vue\\image\\grass"+((rowIndex+colIndex)%10)+".png");
                        Image grassImage = grassIcon.getImage();
                        g.drawImage(grassImage, 0, 0, getWidth(), getHeight(), this);

                        // Dessiner les occupants
                        List<Occupant> occupants = j.getG().getPos(new modele.Position(rowIndex, colIndex, j.getG().getMaxX()));
                        if (occupants != null) {
                            for (Occupant o : occupants) {
                                ImageIcon occupantIcon = new ImageIcon("src\\vue\\image\\" + o.getRepresentation() + ".png");
                                Image occupantImage = occupantIcon.getImage();
                                g.drawImage(occupantImage, 0, 0, getWidth(), getHeight(), this);
                                if(o instanceof ObjetMobile){
                                    if(((ObjetMobile)o).isHavetool()){
                                        ImageIcon toolIcon = new ImageIcon("src\\vue\\image\\tool.png");
                                        Image toolImage = toolIcon.getImage();
                                        g.drawImage(toolImage, 0, 0, getWidth(), getHeight(), this);
                                    }
                                }
                            }
                        }
                    }
                };

                // Ajouter le panel à la zone
                Zone.add(panel);
            }
        }
    }

    public static void updateGrid(Jeux j, JPanel Zone) {
        for (int i = 0; i < j.getG().getMaxX(); i++) {
            for (int k = 0; k < j.getG().getMaxY(); k++) {
                JPanel panel = (JPanel) Zone.getComponent(i * j.getG().getMaxX() + k);
                panel.repaint();
            }
        }
    }


    public static void main(String[] args){
        JFrame f=new JFrame();
        f.setResizable(false);
        f.setSize(816,891);
        f.setVisible(true);
        Container c =f.getContentPane();
        c.setLayout(new BorderLayout());

        ///Zone du plateaux
        JPanel Zone=new JPanel();
        c.add(Zone);
        Jeux j=new Jeux();
        Zone.setVisible(true);
        Zone.setPreferredSize(new Dimension(800,800));
        controle c1=new controle(j,Zone);
        createGrid(j,Zone);

        //zone du bas
        String nbclics="action";
        JLabel l=new JLabel("Action : "+nbclics,JLabel.CENTER );
        c.add(l,BorderLayout.SOUTH);

        //
        JPanel NORTH=new JPanel();
        NORTH.setVisible(true);
        f.add(NORTH,BorderLayout.NORTH);
        c1.generateAvancement(NORTH);

        c.revalidate();
        




        


        


        //met à jour la Zone
        Zone.validate();
        Zone.repaint();
        //pause de 1 seconde
        
        
        j.oneTurn();
        updateGrid(j,Zone);
        Zone.validate();
        Zone.repaint();

        j.oneTurn();
        updateGrid(j,Zone);
        Zone.validate();
        Zone.repaint();

        j.oneTurn();
        updateGrid(j,Zone);
        Zone.validate();
        Zone.repaint();
        
    }
}
