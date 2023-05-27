package modele.occupant.objetFixe;

import java.util.List;

import modele.*;
import modele.occupant.*;
import modele.occupant.objetMobile.*;

/**
 * The Pierre class extends the ObjetFixe class and contains methods for
 * checking if a position
 * contains a Pierre, an ObjetMobile, or an ObjetFixe, as well as a process
 * method for handling the
 * movement of an ObjetMobile on the grid.
 */
public class Pierre extends ObjetFixe {
    public Pierre(char Representation, Position p, Grille g) {
        super(Representation, p, g);
    }

    /**
     * This function checks if a given position in a grid contains a stone.
     * 
     * @param p The parameter "p" is of type Position, which represents a position
     *          on a grid or board.
     * @param m The parameter "m" is of type "Grille", which is likely a class
     *          representing a grid or
     *          board in a game or simulation.
     * @return The method `containPierre` returns a boolean value, either `true` or
     *         `false`.
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
     * This Java function checks if a mobile object is present at a given position
     * in a grid and
     * returns the object if found.
     * 
     * @param p The position of the object being searched for in the grid.
     * @param m m is an object of the class Grille, which represents a grid or a
     *          board. It is used to
     *          retrieve the occupants at a specific position on the grid.
     * @return The method `containObjetMobile` returns an object of type `Occupant`
     *         which represents
     *         the first mobile object found at the given position in the grid. If
     *         no mobile object is found,
     *         it returns `null`.
     */
    public Occupant containObjetMobile(Position p, Grille m) {
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
     * This Java function checks if a given position in a grid contains a fixed
     * object and returns it
     * if it does.
     * 
     * @param p The parameter "p" is of type Position and represents the position in
     *          the grid where we
     *          want to check if there is an Occupant of type ObjetFixe.
     * @param m m is an object of the class Grille, which represents a grid or a
     *          board. It is used to
     *          retrieve the occupants at a specific position on the grid.
     * @return The method `containObjetFixe` returns an object of type `Occupant`
     *         which represents the
     *         first `ObjetFixe` found in the `Grille` `m` at the given `Position`
     *         `p`. If no `ObjetFixe` is
     *         found, it returns `null`.
     */
    public Occupant containObjetFixe(Position p, Grille m) {
        List<Occupant> l = m.getPos(p);
        if (l == null || l.isEmpty()) {
            return null;
        }
        for (Occupant o : l) {
            if (o instanceof ObjetFixe) {
                return o;
            }
        }
        return null;
    }

    /**
     * This function checks if a given position in a grid contains a stone.
     * 
     * @param p The parameter "p" is of type Position, which represents a position
     *          on a grid or board.
     * @return The method `stoneAtPosition` returns a boolean value, either `true`
     *         or `false`.
     */
    public boolean stoneAtPosition(Position p) {
        List<Occupant> l = getGrille().getPos(p);
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
     * This function returns the position of the closest direction to bypassed the wall
     * 
     * 
     * 
     * @param h The parameter "h" is an instance of the class "ObjetMobile", which
     *          represents a mobile
     *          object in a grid.
     * @return The method `getClosestDirection` returns a Position, which represents
     */
    public Position getClosestDirection(ObjetMobile h) {
        Position tempPos = new Position(getPos());
        Position currPos=h.getPos();
        //à verifié
        if(getGrille().isMurVertical()){
            int upperDistance = -1;
            int lowerDistance = -1;
            while(stoneAtPosition(tempPos)){
                tempPos.setX(tempPos.getX()+1);
                upperDistance++;
            }
            tempPos.setX(getPos().getX());
            while(stoneAtPosition(tempPos)){
                tempPos.setX(tempPos.getX()-1);
                lowerDistance++;
            }
            if(lowerDistance<upperDistance){
                if(lowerDistance==0&&h.getPos().getX()<=this.getPos().getX()){
                    return new Position(currPos.getX(),currPos.getY()+1,getGrille().getMaxX());
                }
                else{
                    return new Position(currPos.getX()-1,currPos.getY(),getGrille().getMaxX());
                }
            }else{
                if(upperDistance==0&&h.getPos().getX()>=this.getPos().getX()){
                    return new Position(currPos.getX(),currPos.getY()+1,getGrille().getMaxX());
                }
                else{
                    return new Position(currPos.getX()+1,currPos.getY(),getGrille().getMaxX());
                }
                
            }
        }
        else{
            int leftDistance = 0;
            int rightDistance = 0;
            while(stoneAtPosition(tempPos)){
                tempPos.setY(tempPos.getY()+1);
                rightDistance++;
            }
            tempPos.setY(getPos().getY()-1);
            while(stoneAtPosition(tempPos)){
                tempPos.setY(tempPos.getY()-1);
                leftDistance++;
            }
            if(leftDistance<rightDistance){

                if(leftDistance==0&&h.getPos().getY()<=this.getPos().getY()){

                    System.out.println(rightDistance);
                    return new Position(currPos.getX()+1,currPos.getX(),getGrille().getMaxX());
                }
                else{

                    System.out.println(leftDistance);
                    return new Position(currPos.getX(),currPos.getY()-1,getGrille().getMaxX());
                }
            }else{

                System.out.println("b");
                if(rightDistance==0&&h.getPos().getY()>=this.getPos().getY()){
                    return new Position(currPos.getX()+1,currPos.getY(),getGrille().getMaxX());
                }
                else{
                    return new Position(currPos.getX(),currPos.getY()+1,getGrille().getMaxX());
                }
                
            }
        }


        
            
    }




    /**
     * This function processes the movement of a mobile object on a grid, taking
     * into account walls and
     * other occupants, and updates the grid accordingly.
     * 
     * @param h The parameter "h" is an instance of the class "ObjetMobile", which
     *          represents a mobile
     *          object in a grid.
     */
    public void process(ObjetMobile h) {
        
        Position nextPos = h.getNextPosition();
        if (h.isHavetool()) {
            getGrille().addOccupant(nextPos, h);
            getGrille().removeOccupant(h.getPos(), h);
            h.setPos(nextPos);
            getGrille().getTexteAction().setTexte(h.getRepresentation() + " passe sur le mur");
            return;
        } else {

            nextPos=getClosestDirection(h);
            Occupant o = containObjetMobile(nextPos, getGrille());
            if (o != null) {
                o.process(h);
                return;
            } else {
                o = containObjetFixe(nextPos, getGrille());
                if (o == null) {
                    getGrille().getTexteAction().setTexte(h.getRepresentation() + " a changé de position");
                    getGrille().addOccupant(nextPos, h);
                    getGrille().removeOccupant(h.getPos(), h);
                    h.setPos(nextPos);
                } else {

                    if(!(o instanceof Border)){
                        getGrille().removeOccupant(h.getPos(), h);
                        h.setPos(new Position(nextPos.getX() - h.getDirX(), nextPos.getY() - h.getDirY(),getGrille().getMaxX()));
                        getGrille().addOccupant(h.getPos(), h);
                    }
                    o.process(h);
                    //ICI
                    

                    System.out.println(h.getGrille());
                }

            }
            return;

        }

    }
}
