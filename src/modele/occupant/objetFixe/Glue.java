package modele.occupant.objetFixe;

import modele.*;
import modele.occupant.objetMobile.*;
/**
 * The "Glue" class is a subclass of "ObjetFixe" that represents a stationary object on a grid with a
 * waiting time for mobile objects that come into contact with it.
 */

public class Glue extends ObjetFixe {
    private int tourAttente;

    public Glue(char Representation, Position p, Grille g) {
        super(Representation, p, g);
        this.tourAttente = 3;
    }

    /**
     * This function updates the position of a mobile object on a grid and sets its waiting time.
     * 
     * @param h The parameter "h" is an object of type "ObjetMobile", which represents a mobile object
     * that can move on a grid.
     */
    public void process(ObjetMobile h) {
        Position nextPos = h.getNextPosition();
        h.setTourAttente(tourAttente);
        if(tourAttente>1){
            tourAttente--;
        }

        getGrille().addOccupant(nextPos, h);
        getGrille().removeOccupant(h.getPos(), h);
        System.out.println(getGrille().removeOccupant(h.getPos(), h));
        h.setPos(nextPos);

        System.out.println(
                h.getRepresentation() + " a changé de position et est en attente pour " + tourAttente + "tour(s)");
    }

    
    /**
     * This function returns the value of the variable "tourAttente".
     * 
     * @return The method is returning an integer value of the variable `tourAttente`.
     */
    public int getTourAttente() {
        return tourAttente;
    }


    /**
     * This Java function sets the value of a variable called "tourAttente".
     * 
     * @param tourAttente tourAttente is a parameter of type integer that represents the number of
     * turns a player has to wait before being able to take another action in a game or simulation. The
     * method sets the value of the instance variable "tourAttente" to the value passed as the
     * parameter.
     */
    public void getTourAttente(int tourAttente) {
        this.tourAttente = tourAttente;
    }
}
