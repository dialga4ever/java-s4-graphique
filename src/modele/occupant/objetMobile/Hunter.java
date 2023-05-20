package modele.occupant.objetMobile;

import modele.*;

/**
 * The Hunter class extends the ObjetMobile class and has a process method that
 * redirects other mobile
 * objects to a random direction.
 */
public class Hunter extends ObjetMobile {
    public Hunter(char Representation, Position p, Grille g) {
        super(Representation, p, g);
    }

    /**
     * The function redirects a mobile object to a random direction.
     * 
     * @param h The parameter "h" is an obj2ect of the class "ObjetMobile" that is
     *          passed as an argument
     *          to the method "process".
     */
    public void process(ObjetMobile h) {
        if (h != this) {
            h.randomDir();
            System.out.println(h.getRepresentation() + " a été redirigé vers une direction aléatoire");
        }
    }

}
