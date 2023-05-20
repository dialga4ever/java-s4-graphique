package modele.occupant;

import modele.*;

/**
 * The Occupant class is an abstract class that implements the Questionnable
 * interface and defines
 * common attributes and methods for all occupants in a grid.
 */
public abstract class Occupant implements Questionnable {
    private char Representation;
    private Position p;
    private Grille grille;

    public Occupant(char Representation, Position p, Grille g) {
        this.p = p;
        this.Representation = Representation;
        grille = g;
    }

    /**
     * This function returns a string representation of the object.
     * 
     * @return The `toString()` method is returning a string representation of the
     *         object's
     *         `getRepresentation()` method. The exact content of the returned
     *         string will depend on the
     *         implementation of the `getRepresentation()` method.
     */
    @Override
    public String toString() {
        return "" + getRepresentation();
    }

    /**
     * @return char return the Representation
     */
    public char getRepresentation() {
        return Representation;
    }

    /**
     * @param Representation the Representation to set
     */
    public void setRepresentation(char Representation) {
        this.Representation = Representation;
    }

    /**
     * @return Position return the p
     */
    public Position getPos() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setPos(Position p) {
        this.p = p;
    }

    /**
     * @return Grille return the grille
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * @param grille the grille to set
     */
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

}
