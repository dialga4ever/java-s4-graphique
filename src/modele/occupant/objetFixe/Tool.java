package modele.occupant.objetFixe;

import modele.*;
import modele.occupant.objetMobile.*;

/**
 * The "Tool" class is a subclass of "ObjetFixe" that updates the position of a
 * mobile object, adds it
 * to the grid, and sets a flag indicating that it has a tool.
 */
public class Tool extends ObjetFixe {
    public Tool(char Representation, Position p, Grille g) {
        super(Representation, p, g);
    }

    /**
     * The function updates the position of a mobile object, adds it to the grid,
     * and sets a flag
     * indicating that it has a tool.
     * 
     * @param h The parameter "h" is an instance of the class "ObjetMobile", which
     *          represents a mobile
     *          object in a grid.
     */
    public void process(ObjetMobile h) {
        Position nextPos = h.getNextPosition();
        h.setHavetool(true);
        getGrille().addOccupant(nextPos, h);
        getGrille().removeOccupant(h.getPos(), h);
        h.setPos(nextPos);
        getGrille().getTexteAction().setTexte(h.getRepresentation() + " a obtenu un outil et a avancé");
        return;
    }
}
