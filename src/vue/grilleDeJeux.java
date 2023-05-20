package vue;

import javax.swing.JPanel;

import modele.Jeux;
import java.awt.*;
import javax.swing.*;
import modele.occupant.*;
import modele.occupant.objetMobile.Hunter;
import modele.occupant.objetMobile.ObjetMobile;
import java.util.List;

public class grilleDeJeux extends JPanel{
    private Jeux j;
    public grilleDeJeux(TexteAction t){
        super();
        Jeux j=new Jeux(t);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800,800));
        this.j=j;
    }


    public void creeGrilleDeJeux(){
        this.setLayout(new GridLayout(j.getG().getMaxX(), j.getG().getMaxY()));

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
                                ImageIcon occupantIcon;
                                if(o instanceof Hunter){
                                    occupantIcon = new ImageIcon("src\\vue\\image\\A.png");
                                }
                                else{
                                    occupantIcon = new ImageIcon("src\\vue\\image\\" + o.getRepresentation() + ".png");
                                }
                                
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
                this.add(panel);
            }
        }
    }
    public void updateGrid() {
        for (int i = 0; i < j.getG().getMaxX(); i++) {
            for (int k = 0; k < j.getG().getMaxY(); k++) {
                JPanel panel = (JPanel) this.getComponent(i * j.getG().getMaxX() + k);
                panel.repaint();
            }
        }
    }


    /**
     * @return Jeux return the j
     */
    public Jeux getJ() {
        return j;
    }

    /**
     * @param j the j to set
     */
    public void setJ(Jeux j) {
        this.j = j;
    }

}
