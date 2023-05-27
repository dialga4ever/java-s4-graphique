package vue;
import javax.swing.JLabel;

/**
 * The TexteAction class is a Java class that updates the text of a label and allows for getting and
 * setting the label and text values.
 */
public class TexteAction {
    
    private JLabel label;
    private String texte;
    
    public TexteAction(JLabel label, String texte) {
        this.label = label;
        this.texte = texte;
    }
    
    /**
     * This Java function updates the text of a label.
     */
    public void updateLabel() {
        label.setText(texte);
    }

    /**
     * @return String return the texte
     */
    public String getTexte() {
        return texte;
    }

    /**
     * @param texte the texte to set
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }


    /**
     * @return JLabel return the label
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }

}
