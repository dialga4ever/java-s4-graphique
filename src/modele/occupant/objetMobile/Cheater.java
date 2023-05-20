package modele.occupant.objetMobile;

import modele.*;

/**
 * The "Cheater" class redirects a mobile object towards the opposite direction
 * of a treasure on a
 * grid.
 */
public class Cheater extends ObjetMobile {
    public Cheater(char Representation, Position p, Grille g) {
        super(Representation, p, g);
    }

    /**
     * The function redirects a mobile object towards the opposite direction of a
     * treasure on a grid.
     * 
     * @param h The parameter "h" is an object of type "ObjetMobile", which is being
     *          passed as an
     *          argument to the "process" method.
     */
    public void process(ObjetMobile h) {
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
        h.setDirX(-h.getDirX());
        h.setDirY(-h.getDirY());
        getGrille().getTexteAction().setTexte(h.getRepresentation() + " a été redirigé à l'opposé du trésor");
    }
}
