package vue;
import javax.swing.JLabel;

public class TexteAction {
    
    private JLabel label;
    private String texte;
    
    public TexteAction(JLabel label, String texte) {
        this.label = label;
        this.texte = texte;
    }
    
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

}
