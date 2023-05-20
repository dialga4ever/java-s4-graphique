package modele;
/**
 * The class "Position" is being declared but no code has been written yet.
 */
public class Position {
    private int x;
    private int y;
    private int nbLigne;
    public Position(int nbLigne){
        x=0;
        y=0;
        this.nbLigne=nbLigne;
    }
    public Position(int x,int y,int nbLigne){
        this.x=x;
        this.y=y;
        this.nbLigne=nbLigne;
    }
    public Position(Position p){
        this.x=p.getX();
        this.y=p.getY();
        this.nbLigne=p.getNbLigne();
    }
    /**
     * @return int return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return int return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
    * This is an implementation of the equals method in Java that checks if two Position objects have
    * the same x and y values.
    * 
    * @param o The parameter "o" is an object of type Object, which is the superclass of all classes in
    * Java. It is used to check if the object being compared to the current object is an instance of
    * the Position class.
    * @return The method is returning a boolean value. It returns true if the object being compared to
    * is an instance of the Position class and has the same x and y coordinates as the current object,
    * and false otherwise.
    */
    @Override
    public boolean equals(Object o) {
        if(o instanceof Position){
            Position P=(Position) o;
            if(P.getX()==x && P.getY()==y){
                return true;
            }
        }
        return false;
    }
    /**
     * This function overrides the default toString() method to return a string representation of an
     * object's x and y values.
     * 
     * @return A string representation of an object with the values of its x and y properties. The
     * string is formatted as "{x=value of x, y=value of y}".
     */
    @Override
    public String toString() {
        return "{" + "x=" + x + ", y=" + y +  '}';
    }
    /**
     * This is a Java implementation of the hashCode() function that returns a unique integer value
     * based on the values of y and x multiplied by the number of lines.
     * 
     * @return The method is returning an integer value which is calculated by adding the value of y to
     * the product of x and nbLigne.
     */
    @Override
    public int hashCode() {
        //hashcode unique pour chaque position dans la grille
        return x+y*nbLigne;
        
    }

    /**
     * @return int return the nbLigne
     */
    public int getNbLigne() {
        return nbLigne;
    }

    /**
     * @param nbLigne the nbLigne to set
     */
    public void setNbLigne(int nbLigne) {
        this.nbLigne = nbLigne;
    }

}

