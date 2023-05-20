package modele.occupant.objetFixe;

import modele.*;
import modele.occupant.objetMobile.*;

/**
 * The Border class processes the movement of a mobile object and redirects it
 * if it encounters a
 * border in a grid.
 */
public class Border extends ObjetFixe {
    public Border(char Representation, Position p, Grille g) {
        super(Representation, p, g);
    }

    /**
     * The function processes the movement of a mobile object and redirects it if it
     * encounters a
     * border.
     * 
     * @param h h is an object of type ObjetMobile, which represents a mobile object
     *          in a grid. The
     *          method process() takes this object as a parameter and checks if it
     *          has encountered any borders
     *          in the grid. If it has, the method changes the direction of the
     *          object's movement accordingly.
     */
    public void process(ObjetMobile h) {
        String s=h.getRepresentation() + " a rencontré une bordure ";
        Position nextPos = h.getNextPosition();
        if (nextPos.getX() >= getGrille().getMaxX() - 1) {
            s+=("à droite ");
            h.setDirX(-h.getDirX());
        }
        if (nextPos.getY() >= getGrille().getMaxY() - 1) {
            h.setDirY(-h.getDirY());
            s+=("en bas ");
        }
        if (nextPos.getX() <= 0) {
            s+=("à gauche ");
            h.setDirX(-h.getDirX());
        }
        if (nextPos.getY() <= 0) {
            s+=("en hauts ");
            h.setDirY(-h.getDirY());
        }
        s+=("et a été redirigé\n");
        getGrille().getTexteAction().setTexte(s);
    }
}
