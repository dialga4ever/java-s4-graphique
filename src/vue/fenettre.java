package vue;
import java.awt.*;
import javax.swing.*;
import controleur.controle;
import java.io.File;
/**
 * This is a Java class that creates a graphical user interface for a treasure hunt game.
 */
public class fenettre extends JFrame{
    //zone du haut et du bas et du centre
    private grilleDeJeux CENTER;
    private JPanel NORTH;
    private JPanel SOUTH;
    private controle c;
    private TexteAction t;
    public fenettre(){
        super();
        this.setTitle("Chasse au trésor");
        this.setResizable(false);
        this.setSize(816,891);
        this.setVisible(true);
        ImageIcon img = new ImageIcon("src/vue/image/$.png");
        this.setIconImage(img.getImage());
        Container container =this.getContentPane();
        container.setLayout(new BorderLayout());
        container.revalidate();
        container.repaint();
        ///Zone du plateaux
        String text="";
        JLabel l=new JLabel(""+text,JLabel.CENTER );
        this.t=new TexteAction(l,text);
        CENTER=new grilleDeJeux(t);
        container.add(CENTER);
        container.revalidate();
        container.repaint();
        //controle
        c=new controle(CENTER,t,this);
        container.revalidate();
        container.repaint();
        //met à jour la Zone
        CENTER.validate();
        CENTER.repaint();
    }

    /**
     * This function initializes the game zone by creating and adding panels, generating game progress,
     * and refreshing the layout.
     */
    public void inizialitationZoneDeJeux(){
        SOUTH=new JPanel();
        c.setLabel(this.t.getLabel());
        

        SOUTH.add(this.t.getLabel());
        this.add(SOUTH,BorderLayout.SOUTH);

        SOUTH.setVisible(true);
        NORTH=new JPanel();
        NORTH.setVisible(true);
        this.add(NORTH,BorderLayout.NORTH);


        c.generateAvancement(NORTH);
        CENTER.creeGrilleDeJeux();
        SOUTH.revalidate();
        SOUTH.repaint();
        NORTH.revalidate();
        NORTH.repaint();
        CENTER.revalidate();
        CENTER.repaint();
        this.revalidate();
        this.repaint();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }


    public void truc(){
    }

    /**
     * @return grilleDeJeux return the CENTER
     */
    public grilleDeJeux getCENTER() {
        return CENTER;
    }

    /**
     * @param CENTER the CENTER to set
     */
    public void setCENTER(grilleDeJeux CENTER) {
        this.CENTER = CENTER;
    }

    /**
     * @return JPanel return the NORTH
     */
    public JPanel getNORTH() {
        return NORTH;
    }

    /**
     * @param NORTH the NORTH to set
     */
    public void setNORTH(JPanel NORTH) {
        this.NORTH = NORTH;
    }

    /**
     * @return JPanel return the SOUTH
     */
    public JPanel getSOUTH() {
        return SOUTH;
    }

    /**
     * @param SOUTH the SOUTH to set
     */
    public void setSOUTH(JPanel SOUTH) {
        this.SOUTH = SOUTH;
    }

    /**
     * @param c the c to set
     */
    public void setC(controle c) {
        this.c = c;
    }

    /**
     * @return TexteAction return the t
     */
    public TexteAction getT() {
        return t;
    }

}
