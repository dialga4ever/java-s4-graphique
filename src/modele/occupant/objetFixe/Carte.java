package modele.occupant.objetFixe;

import modele.*;
import modele.occupant.objetMobile.*;

/**
 * The Carte class is a subclass of ObjetFixe that redirects Hunters towards the
 * treasure and
 * randomizes the direction of other mobile objects.
 */
public class Carte extends ObjetFixe {
    public Carte(char Representation, Position p, Grille g) {
        super(Representation, p, g);
    }

    /**
     * The function redirects an object towards the treasure or a random direction
     * based on its type.
     * 
     * @param h The parameter "h" is an object of type ObjetMobile, which represents
     *          a mobile object in
     *          the game. The method "process" takes this object as a parameter and
     *          performs certain actions
     *          based on its type. If "h" is not an instance of the Hunter class, it
     *          is redirected to
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
        Position nextPos = h.getNextPosition();
        getGrille().addOccupant(nextPos, h);
        getGrille().removeOccupant(h.getPos(), h);
        h.setPos(nextPos);
        if (tresorX > 0) {
            if (tresorY > 0) {
                if (tresorY > 2 * tresorX) {
                    h.setDirY(1);
                    h.setDirX(0);
                } else if (tresorY > 2 * tresorX) {
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
                } else if (tresorX > 2 * (-tresorY)) {
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
        System.out.println("Le hunter " + h.getRepresentation() + " a été redirigé vers le trésor");

        return;
    }

}