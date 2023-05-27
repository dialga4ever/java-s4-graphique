package modele.occupant.objetMobile;

import java.util.*;

import modele.*;
import modele.occupant.*;
import modele.occupant.objetFixe.*;

/**
 * The ObjetMobile class is an abstract class that represents a mobile object in
 * a grid, with
 * attributes such as waiting time, direction, and tool possession.
 */
public abstract class ObjetMobile extends Occupant {
    private int tourAttente;
    private boolean havetool;
    private int dirX;
    private int dirY;
    private boolean win;

    public ObjetMobile(char Representation, Position p, Grille g) {
        super(Representation, p, g);
        win = false;
        randomDir();

        havetool = false;
        tourAttente = 0;
    }

    /**
     * This function checks if a given position in a grid contains a stone.
     * 
     * @param p The parameter "p" is of type "Position" and represents a position on
     *          a grid.
     * @param m The parameter "m" is of type "Grille", which is likely a class
     *          representing a grid or
     *          board in a game or simulation. It is being used to retrieve a list
     *          of occupants at a given
     *          position "p".
     * @return The method `containPierre` returns a boolean value. It returns `true`
     *         if there is a
     *         `Pierre` object at the given `Position` in the `Grille` object, and
     *         `false` otherwise.
     */
    public boolean containPierre(Position p, Grille m) {
        List<Occupant> l = m.getPos(p);
        if (l == null || l.isEmpty()) {
            return false;
        }
        for (Occupant o : l) {
            if (o instanceof Pierre) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function checks if a mobile object is present at a given position in a
     * grid.
     * 
     * @param p The position being checked for the presence of an ObjetMobile
     *          occupant.
     * @param m m is an object of the class Grille, which represents a grid or a
     *          board.
     * @return A boolean value indicating whether there is an instance of the class
     *         ObjetMobile at the
     *         given position in the Grille object.
     */
    public boolean containObjetMobile(Position p, Grille m) {
        List<Occupant> l = m.getPos(p);
        if (l == null || l.isEmpty()) {
            return false;
        }
        for (Occupant o : l) {
            if (o instanceof ObjetMobile) {
                return true;
            }
        }
        return false;
    }

    /**
     * The function sets the direction of movement for an object randomly in one of
     * eight possible
     * directions.
     */
    public void randomDir() {
        Random r = new Random();
        int i = r.nextInt(8);
        switch (i) {
            case 0:
                dirX = 1;
                dirY = 0;
                break;
            case 1:
                dirX = -1;
                dirY = 0;
                break;
            case 2:
                dirX = 0;
                dirY = 1;
                break;
            case 3:
                dirX = 0;
                dirY = -1;
                break;
            case 4:
                dirX = 1;
                dirY = 1;
                break;
            case 5:
                dirX = -1;
                dirY = -1;
                break;
            case 6:
                dirX = 1;
                dirY = -1;
                break;
            case 7:
                dirX = -1;
                dirY = 1;
                break;
        }
    }

    /**
     * This function returns a new position object with updated X and Y coordinates
     * based on the
     * current direction and maximum X value of the grid.
     * 
     * @return A new Position object with the X and Y coordinates incremented by the
     *         values of dirX and
     *         dirY respectively, and the maxX value of the grid.
     */
    public Position getNextPosition() {
        return new Position(getPos().getX() + dirX, getPos().getY() + dirY, getGrille().getMaxX());
    }

    /**
     * This Java function returns an instance of an ObjetMobile if it exists at a
     * given position in a
     * Grille object.
     * 
     * @param p The position of the object mobile that we want to retrieve from the
     *          grid.
     * @param m The parameter "m" is of type "Grille", which represents a grid or a
     *          board. It is likely
     *          that this method is part of a larger program that involves a game or
     *          a simulation where objects
     *          can move around on a grid. The "m" parameter is used to access the
     *          grid and
     * @return The method is returning an object of type `Occupant` which represents
     *         a mobile object
     *         located at a specific position in a grid. If there is no mobile
     *         object at the given position,
     *         the method returns `null`.
     */
    public Occupant getObjetMobile(Position p, Grille m) {
        List<Occupant> l = m.getPos(p);
        if (l == null || l.isEmpty()) {
            return null;
        }
        for (Occupant o : l) {
            if (o instanceof ObjetMobile) {
                return o;
            }
        }
        return null;
    }

    /**
     * This function controls the movement of an occupant on a grid, checking for
     * collisions with other
     * occupants and objects.
     * 
     * @param g Grille object, which represents the game board/grid.
     */
    public void mouvement(Grille g) {
        if (tourAttente > 0) {
            getGrille().getTexteAction().setTexte(this.getRepresentation() + " est en attente pour " + tourAttente + " tour(s)");
            tourAttente -= 1;
            return;
        }
        Map<Position, List<Occupant>> m = g.getMap();

        Position nextPos = new Position(getPos().getX() + dirX, getPos().getY() + dirY, getGrille().getMaxX());

        List<Occupant> l = m.get(getPos());
        if (containPierre(this.getPos(), g)) {
            if (!containPierre(nextPos, g)) {
                havetool = false;
            }
        }

        l = m.get(nextPos);
        if (l == null || l.isEmpty()) {
            g.addOccupant(nextPos, this);
            g.removeOccupant(this.getPos(), this);
            setPos(nextPos);
            getGrille().getTexteAction().setTexte(this.getRepresentation() + " a changé de position");
            return;
        }

        if (containPierre(nextPos, g)) {

            if (havetool) {

                if (containObjetMobile(nextPos, g)) {

                    getObjetMobile(nextPos, g).process(this);
                    return;
                }
            }
        }
        Occupant mobileMaybe = getObjetMobile(nextPos, g);
        if (mobileMaybe != null) {
            mobileMaybe.process(this);
            return;
        }

        for (Occupant o : l) {
            o.process(this);
            return;
        }
    }

    /**
     * @return boolean return the havetool
     */
    public boolean isHavetool() {
        return havetool;
    }

    /**
     * @param havetool the havetool to set
     */
    public void setHavetool(boolean havetool) {
        this.havetool = havetool;
    }

    /**
     * @return int return the tourAattendre
     */
    public int getTourAattendre() {
        return tourAttente;
    }

    /**
     * @param tourAattendre the tourAattendre to set
     */
    public void setTourAattendre(int tourAattendre) {
        this.tourAttente = tourAattendre;
    }

    /**
     * @return int return the dirX
     */
    public int getDirX() {
        return dirX;
    }

    /**
     * @param dirX the dirX to set
     */
    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    /**
     * @return int return the dirY
     */
    public int getDirY() {
        return dirY;
    }

    /**
     * @param dirY the dirY to set
     */
    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    /**
     * @return int return the tourAttente
     */
    public int getTourAttente() {
        return tourAttente;
    }

    /**
     * @param tourAttente the tourAttente to set
     */
    public void setTourAttente(int tourAttente) {
        this.tourAttente = tourAttente;
    }

    /**
     * @return boolean return the win
     */
    public boolean isWin() {
        return win;
    }

    /**
     * @param win the win to set
     */
    public void setWin(boolean win) {
        this.win = win;
    }

}