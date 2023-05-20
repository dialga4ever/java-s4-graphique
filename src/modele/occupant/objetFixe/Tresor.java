package modele.occupant.objetFixe;

import modele.*;
import modele.occupant.objetMobile.*;

/**
 * The "Tresor" class updates the position of a mobile object on a grid and sets
 * a flag indicating if
 * it has reached its destination.
 */
public class Tresor extends ObjetFixe {
    public Tresor(char Representation, Position p, Grille g) {
        super(Representation, p, g);
    }

    /**
     * The function updates the position of a mobile object on a grid and sets a
     * flag indicating if it
     * has reached its destination.
     * 
     * @param h The parameter "h" is an object of type "ObjetMobile", which
     *          represents a mobile object
     *          that can move around on a grid.
     */
    public void process(ObjetMobile h) {
        Position nextPos = h.getNextPosition();
        getGrille().removeOccupant(h.getPos(), h);
        getGrille().addOccupant(nextPos, h);

        h.setPos(nextPos);
        h.setWin(true);

        if (h instanceof Hunter) {
            System.out.println("le hunter " + h.getRepresentation() + " a changé de position et trouve le trésor");
        }
    }
}
