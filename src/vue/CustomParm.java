package vue;
import javax.swing.*;

import modele.Jeux;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The CustomParm class is used to create a custom parameter object with different percentage values
 * for different game elements.
 */
public class CustomParm {
    private int gluePourcent;
    private int cartePourcent;
    private int  hunterPourcent;
    private int sagePourcent;
    private int cheaterPourcent;
    private int  toolPourcent;


    public CustomParm(int gluePourcent,int cartePourcent,int hunterPourcent,int sagePourcent,int cheaterPourcent,int toolPourcent){
        this.gluePourcent=gluePourcent;
        this.cartePourcent=cartePourcent;
        this.hunterPourcent=hunterPourcent;
        this.sagePourcent=sagePourcent;
        this.cheaterPourcent=cheaterPourcent;
        this.toolPourcent=toolPourcent;
    }

    public CustomParm(Jeux j,fenettre vue){
        JFrame frame = new JFrame("Paramètres personnalisés");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 350);
        frame.setLayout(new GridLayout(8, 2));
        frame.setResizable(false);
        ImageIcon img = new ImageIcon("src/vue/image/$.png");
        frame.setIconImage(img.getImage());

        // Labels
        JLabel glueLabel = new JLabel("Pourcentage de glue (entre 0 et 100) :");
        JLabel carteLabel = new JLabel("Pourcentage de carte (entre 0 et 100) :");
        JLabel hunterLabel = new JLabel("Pourcentage de hunter (entre 0 et 100) :");
        JLabel sageLabel = new JLabel("Pourcentage de sage (entre 0 et 100) :");
        JLabel cheaterLabel = new JLabel("Pourcentage de cheater (entre 0 et 100) :");
        JLabel toolLabel = new JLabel("Pourcentage de tool (entre 0 et 100) :");
    
        // TextFields
        JTextField glueField = new JTextField();
        JTextField carteField = new JTextField();
        JTextField hunterField = new JTextField();
        JTextField sageField = new JTextField();
        JTextField cheaterField = new JTextField();
        JTextField toolField = new JTextField();
        CustomParm c=this;
        // Button
        JButton generateButton = new JButton("Générer");
    
        // ActionListener pour le bouton de génération
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
    
                try {
                    gluePourcent = Integer.parseInt(glueField.getText());
                    cartePourcent = Integer.parseInt(carteField.getText());
                    hunterPourcent = Integer.parseInt(hunterField.getText());
                    sagePourcent = Integer.parseInt(sageField.getText());
                    cheaterPourcent = Integer.parseInt(cheaterField.getText());
                    toolPourcent = Integer.parseInt(toolField.getText());
    
                    if (gluePourcent < 0 || gluePourcent > 100 ||
                            cartePourcent < 0 || cartePourcent > 100 ||
                            hunterPourcent < 0 || hunterPourcent > 100 ||
                            sagePourcent < 0 || sagePourcent > 100 ||
                            cheaterPourcent < 0 || cheaterPourcent > 100 ||
                            toolPourcent < 0 || toolPourcent > 100) {
                        JOptionPane.showMessageDialog(frame, "Veuillez entrer des pourcentages valides entre 0 et 100.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        frame.dispose();
                        j.generateParam(c);
                        j.setIntialized(true);
                        vue.getCENTER().updateGrid();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer des pourcentages valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        // Ajout des composants à la fenêtre
        frame.add(glueLabel);
        frame.add(glueField);
        frame.add(carteLabel);
        frame.add(carteField);
        frame.add(hunterLabel);
        frame.add(hunterField);
        frame.add(sageLabel);
        frame.add(sageField);
        frame.add(cheaterLabel);
        frame.add(cheaterField);
        frame.add(toolLabel);
        frame.add(toolField);
        frame.add(new JLabel()); // Espace vide pour l'esthétique
        frame.add(generateButton);
        // Affichage de la fenêtre
        frame.setVisible(true);
    }

    /**
     * @return int return the gluePourcent
     */
    public int getGluePourcent() {
        return gluePourcent;
    }

    /**
     * @param gluePourcent the gluePourcent to set
     */
    public void setGluePourcent(int gluePourcent) {
        this.gluePourcent = gluePourcent;
    }

    /**
     * @return int return the cartePourcent
     */
    public int getCartePourcent() {
        return cartePourcent;
    }

    /**
     * @param cartePourcent the cartePourcent to set
     */
    public void setCartePourcent(int cartePourcent) {
        this.cartePourcent = cartePourcent;
    }

    /**
     * @return int return the hunterPourcent
     */
    public int getHunterPourcent() {
        return hunterPourcent;
    }

    /**
     * @param hunterPourcent the hunterPourcent to set
     */
    public void setHunterPourcent(int hunterPourcent) {
        this.hunterPourcent = hunterPourcent;
    }

    /**
     * @return int return the sagePourcent
     */
    public int getSagePourcent() {
        return sagePourcent;
    }

    /**
     * @param sagePourcent the sagePourcent to set
     */
    public void setSagePourcent(int sagePourcent) {
        this.sagePourcent = sagePourcent;
    }

    /**
     * @return int return the cheaterPourcent
     */
    public int getCheaterPourcent() {
        return cheaterPourcent;
    }

    /**
     * @param cheaterPourcent the cheaterPourcent to set
     */
    public void setCheaterPourcent(int cheaterPourcent) {
        this.cheaterPourcent = cheaterPourcent;
    }

    /**
     * @return int return the toolPourcent
     */
    public int getToolPourcent() {
        return toolPourcent;
    }

    /**
     * @param toolPourcent the toolPourcent to set
     */
    public void setToolPourcent(int toolPourcent) {
        this.toolPourcent = toolPourcent;
    }

}
