package modele.occupant.objetMobile;

import modele.*;

/**
 * The `Sage` class is a subclass of `ObjetMobile` that has a `process` method
 * which redirects
 * non-hunter objects to a random direction and calculates the direction for a
 * hunter object towards
 * the treasure.
 */
public class Sage extends ObjetMobile {
    public Sage(char Representation, Position p, Grille g) {
        super(Representation, p, g);
    }

    /**
     * The function redirects an object towards the treasure if it is a hunter,
     * otherwise it sets a
     * random direction.
     * 
     * @param h The parameter "h" is an object of type ObjetMobile, which represents
     *          a mobile object in
     *          the game. The method "process" takes this object as a parameter and
     *          performs certain actions
     *          based on its type. If the object is not an instance of the Hunter
     *          class, it is redirected to a
     */
    public void process(ObjetMobile h) {
        if (!(h instanceof Hunter)) {
            h.randomDir();
            System.out.println(h.getRepresentation() + " a été redirigé vers une direction aléatoire");
            return;
        }
        Position tresor = getGrille().getTresor().getPos();
        int tresorX = tresor.getX() - h.getPos().getX();
        int tresorY = tresor.getY() - h.getPos().getY();
        if (tresorX > 0) {
            if (tresorY > 0) {
                if (tresorY > 2 * tresorX) {
                    h.setDirY(1);
                    h.setDirX(0);
                } else if (tresorX > 2 * tresorY) {
                    h.setDirY(0);
                    h.setDirX(1);
                } else {
                    h.setDirY(1);
                    h.setDirX(1);
                }
            } else {
                if ((-tresorY) > 2 * tresorX) {
                    h.setDirY(-1);
                    h.setDirX(0);
                } else if ((tresorX) > 2 * -tresorY) {
                    h.setDirY(0);
                    h.setDirX(1);
                } else {
                    h.setDirY(-1);
                    h.setDirX(1);
                }
            }
        } else {
            if (tresorY > 0) {
                if (tresorY > 2 * (-tresorX)) {
                    h.setDirY(1);
                    h.setDirX(0);
                } else if (tresorY > 2 * (-tresorX)) {
                    h.setDirY(0);
                    h.setDirX(-1);
                } else {
                    h.setDirY(1);
                    h.setDirX(-1);
                }
            } else {
                if ((-tresorY) > 2 * (-tresorX)) {
                    h.setDirY(-1);
                    h.setDirX(0);
                } else if ((-tresorY) > 2 * (-tresorX)) {
                    h.setDirY(0);
                    h.setDirX(-1);
                } else {
                    h.setDirY(-1);
                    h.setDirX(-1);
                }
            }
        }
        System.out.println("le hunter " + h.getRepresentation() + " a été redirigé vers le trésor");
        return;
    }
}