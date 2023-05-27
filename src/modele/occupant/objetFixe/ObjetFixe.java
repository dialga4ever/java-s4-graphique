package modele.occupant.objetFixe;
import modele.*;
import modele.occupant.*;
/**
 * The abstract class ObjetFixe extends the Occupant class and represents a fixed object in a grid.
 */
public abstract class ObjetFixe extends Occupant{
    public ObjetFixe(char Representation,Position p,Grille g){
        super(Representation, p,g);
    }

    
}
