package modele;

import java.util.*;

import modele.occupant.*;
import modele.occupant.objetFixe.*;
import modele.occupant.objetMobile.*;
import vue.TexteAction;

/**
 * The Grille class represents a grid with occupants and borders, and has
 * methods to add and remove
 * occupants, generate walls, and display the grid.
 */
public class Grille {
    private TexteAction texteAction;
    private Map<Position, List<Occupant>> m;
    private int maxX;
    private int maxY;
    private Occupant tresor;
    private boolean murVertical;

    public Grille(int x, int y, TexteAction texteAction) {
        this.texteAction = texteAction;
        m = new HashMap<Position, List<Occupant>>();
        maxX = x;
        maxY = y;
        murVertical = (Math.random() > 0.5);
        generateWall();

    }

    /**
     * This function returns a list of occupants at a given position if it exists in
     * a map, otherwise
     * it returns null.
     * 
     * @param p The parameter "p" is of type "Position", which is likely a class
     *          representing a
     *          position or location in some context. The method "getPos" takes a
     *          Position object as input and
     *          returns a List of Occupant objects associated with that position. If
     *          the input position is not
     *          found in the map
     * @return The method `getPos` returns a `List` of `Occupant` objects that are
     *         located at the
     *         specified `Position` `p`. If there are no occupants at that position,
     *         it returns `null`.
     */
    public List<Occupant> getPos(Position p) {
        if (m.containsKey(p)) {
            return m.get(p);
        }
        return null;
    }

    /**
     * The function generates a wall made of border objects with a specified
     * character and adds them to
     * the grid.
     */
    public void generateWall() {
        for (int i = 0; i < getMaxX(); i++) {
            Occupant bordure = new Border('▓', new Position(i, 0, getMaxX()), this);
            addOccupant(bordure.getPos(), bordure);
            bordure = new Border('▓', new Position(i, getMaxY() - 1, getMaxX()), this);
            addOccupant(bordure.getPos(), bordure);
        }
        for (int i = 1; i < getMaxY() - 1; i++) {
            Occupant bordure = new Border('▓', new Position(0, i, getMaxX()), this);
            addOccupant(bordure.getPos(), bordure);
            bordure = new Border('▓', new Position(getMaxX() - 1, i, getMaxX()), this);
            addOccupant(bordure.getPos(), bordure);
        }
    }

    /**
     * This function adds an occupant to a position in a map, creating a new list if
     * the position is
     * not already in the map.
     * 
     * @param p The parameter "p" is of type "Position", which is likely a class
     *          representing a
     *          position or location in some sort of grid or map.
     * @param o The parameter "o" is an object of the class "Occupant". It is being
     *          added to a list of
     *          occupants at a specific position in a map.
     */
    public void addOccupant(Position p, Occupant o) {
        if (m.containsKey(p)) {
            m.get(p).add(o);
        } else {
            List<Occupant> l = new ArrayList<>();
            l.add(o);
            m.put(p, l);
        }
    }

    /**
     * This Java function removes an occupant from a position in a map if it exists
     * and returns 1,
     * otherwise it returns 0.
     * 
     * @param p The parameter "p" is of type "Position" and represents the position
     *          from which the
     *          occupant needs to be removed.
     * @param o The parameter "o" represents an object of the class "Occupant". It
     *          is the occupant that
     *          needs to be removed from the position "p".
     * @return The method is returning an integer value. If the position `p` exists
     *         in the map `m` and
     *         the occupant `o` is successfully removed from the list of occupants
     *         at that position, the method
     *         returns 1. Otherwise, it returns 0.
     */
    public int removeOccupant(Position p, Occupant o) {
        if (m.containsKey(p)) {
            m.get(p).remove(o);
            return 1;
        }
        return 0;
    }

    /**
     * This is a Java function that returns a string representation of a grid with
     * occupants and their
     * representations.
     * 
     * @return A string representation of a grid with the occupants in their
     *         respective positions.
     */
    @Override
    public String toString() {

        String s = "";

        Position curr = new Position(0, 0, getMaxX());

        s += "\n\\ ";
        for (int x = 0; x < maxY; x++) {

            if (x < 10) {
                s += x + " ";
            } else {
                s += "  ";
            }
        }
        s += "\n";
        for (int x = 0; x < maxX; x++) {

            if (x < 10) {
                s += x;
            } else {
                s += " ";
            }

            for (int y = 0; y < maxY; y++) {
                s += " ";
                curr.setX(x);
                curr.setY(y);
                if (m.containsKey(curr)) {
                    int nbr = 0;
                    for (Occupant el : m.get(curr)) {
                        if (m.get(curr).size() > 1) {
                            if (el instanceof ObjetMobile) {
                                nbr += 1;
                                s += el.getRepresentation();
                                continue;
                            }
                        } else {
                            nbr += 1;
                            s += el.getRepresentation();
                        }
                    }
                    if (nbr == 0) {
                        s += " ";
                    }
                } else {
                    s += " ";
                }

            }

            s += " " + x + "\n";

        }
        s += "  ";
        for (int y = 0; y < maxY; y++) {
            s += y + " ";
        }
        s += "\n";
        return s;
    }

    /**
     * @return return the map
     */
    public Map<Position, List<Occupant>> getMap() {
        return m;
    }

    /**
     * @return int return the maxX
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * @param maxX the maxX to set
     */
    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    /**
     * @return int return the maxY
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * @param maxY the maxY to set
     */
    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * @return Position return the tresor
     */
    public Occupant getTresor() {
        return tresor;
    }

    /**
     * @return boolean return the murVertical
     */
    public boolean isMurVertical() {
        return murVertical;
    }

    /**
     * @param murVertical the murVertical to set
     */
    public void setMurVertical(boolean murVertical) {
        this.murVertical = murVertical;
    }

    /**
     * @param tresor the tresor to set
     */
    public void setTresor(Occupant tresor) {
        this.tresor = tresor;
    }


    /**
     * @return texteAction return the texteAction
     */
    public TexteAction getTexteAction() {
        return texteAction;
    }

    /**
     * @param texteAction the texteAction to set
     */
    public void setTexteAction(TexteAction texteAction) {
        this.texteAction = texteAction;
    }

}
