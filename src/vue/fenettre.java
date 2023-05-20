package vue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modele.Jeux;
import controleur.controle;
import modele.occupant.*;
import java.util.List;
import java.awt.image.BufferedImage;
public class fenettre {

    public static void createGrid(Jeux j,JPanel Zone){
        Zone.setLayout(new GridLayout(j.getG().getMaxX(),j.getG().getMaxY()));
        
        for(int i=0;i<j.getG().getMaxX();i++){
            for(int k=0;k<j.getG().getMaxY();k++){
                //crée un carré de 50*50 pour chaque case de la grille
                JPanel p=new JPanel();
                p.setBorder(BorderFactory.createLineBorder(Color.black));
                p.setBackground(Color.white);
                //changer la taille pour quelle soit proportionnelle à la taille de la fenettre
                Zone.add(p);
            }
        }
    }
    public static void updateGrid(Jeux j,JPanel Zone){
        List<Occupant> l;
        int targetSize = 80;
        
        for(int i=0;i<j.getG().getMaxX();i++){
            for(int k=0;k<j.getG().getMaxY();k++){
                JPanel p = (JPanel) Zone.getComponent(i * j.getG().getMaxX() + k);
                l = j.getG().getPos(new modele.Position(i, k, j.getG().getMaxX()));
                ImageIcon grassIcon = new ImageIcon("src\\vue\\image\\grass.png");
                JLabel grassLabel = new JLabel(grassIcon);
                grassLabel.setBounds(0, 0, p.getWidth(), p.getHeight());
                p.add(grassLabel);
                p.removeAll();
                if (l != null) {
                    for (Occupant o : l) {
                        JLabel label = new JLabel();
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label.setVerticalAlignment(JLabel.CENTER);
                        ImageIcon icon = new ImageIcon("src\\vue\\image\\"+o.getRepresentation()+".png");
                        Image image = icon.getImage();
                        Image resizedImage = image.getScaledInstance(p.getWidth(), p.getHeight(), Image.SCALE_SMOOTH);
                        label.setIcon(new ImageIcon(resizedImage));
                        p.add(label);
                    }
                }
                //add grass
                p.add(grassLabel);
                p.validate();
                p.repaint();


            }
        }
    }

    public static void main(String[] args){
        String nbclics="action";
        JFrame f=new JFrame();
        f.setSize(900,900);
        f.setVisible(true);
        Container c =f.getContentPane();
        JButton b=new JButton("cliquer!");
        b.setSize(50,50);
        JLabel l=new JLabel("Action : "+nbclics,JLabel.CENTER );
        c.setLayout(new BorderLayout());
        
        c.add(l,BorderLayout.SOUTH);

        JButton b_quitter=new JButton("Quitter");

        ActionListener ecbq=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        };
        JPanel NORTH=new JPanel();
        f.add(NORTH,BorderLayout.NORTH);
        NORTH.setLayout(new FlowLayout());
        NORTH.setVisible(true);
        NORTH.add(b);
        b_quitter.addActionListener(ecbq);
        NORTH.add(b_quitter);
        JPanel Zone=new JPanel();
        c.add(Zone);
        Zone.setBorder(BorderFactory.createLineBorder(Color.black));
        Zone.setBackground(Color.gray);
        Jeux j=new Jeux();
        Zone.setVisible(true);
        //crée une grille de de la longeur du jeux et de la largeur du jeux dans le centre de la fenettre
        
        controle c1=new controle(j,Zone);
        

        createGrid(j,Zone);
        //met à jour la Zone
        Zone.validate();
        updateGrid(j,Zone);
        Zone.repaint();
        Zone.validate();

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
