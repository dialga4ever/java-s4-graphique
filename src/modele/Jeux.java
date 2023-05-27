package modele;
import java.util.*;


import modele.occupant.*;
import modele.occupant.objetFixe.*;
import modele.occupant.objetMobile.*;
import vue.CustomParm;
import vue.TexteAction;
import vue.fenettre;

/* 
import Occupant.*;
import Occupant.objetMobile.*;
import Occupant.objetFixe.*;
*/
public class Jeux {
    private int nbrTour=0;
    private List<ObjetMobile> mouvable;
    private Grille g;
    private int nbrSag;
    private int nbrCheat;
    private boolean intialized=false;
    //iterateur de mouvable
    private int it=0;
    public Jeux(TexteAction t){
        nbrSag=0;
        nbrCheat=0;
        int h=10;
        int l=10;
        mouvable=new LinkedList<ObjetMobile>();
        g=new Grille(h,l,t);
        
    }
    public Jeux(int h,int l,TexteAction t){
        nbrSag=0;
        nbrCheat=0;
        mouvable=new LinkedList<ObjetMobile>();
        g=new Grille(h,l,t);
        
    }


    /**
     * The function initializes and prompts the user to select a parameter for generation, then
     * generates a custom or preset setup based on the selected parameter.
     */
    public void initialisation(int i,fenettre vue){

        switch(i){
            case 0:
                generateCustomParam(vue);
                break;
            case 1:
                generateDefaultParam();
                break;
            case 2:
                generateDemoSetupA();
                break;
            case 3:
                generateDemoSetupB();
                break;
            case 4:
                generateDemoSetupC();
                break;
            case 5:
                generateDemoSetupD();
                break;
        }
    }


    /**
     * This Java function generates custom parameters for a game by prompting the user to input
     * percentages for different items.
     */
    public void generateCustomParam(fenettre vue) {
        
        generateWall();
        createTesor();
        // Création de la fenêtre

        CustomParm c=new CustomParm(this,vue);
        
    }

    
    /**
     * The function generates a demo setup in a game by creating a treasure, moving it to a new
     * position, setting a vertical wall, creating a hunter, and generating a wall at a specific
     * position.
     */
    public void generateDemoSetupA(){
        createTesor();
        g.removeOccupant(g.getTresor().getPos(), g.getTresor());
        g.getTresor().getPos().setX(7);
        g.getTresor().getPos().setY(8);
        g.addOccupant(g.getTresor().getPos(), g.getTresor());
        g.setMurVertical(true);
        createHunter(3,4,1,1);
        generateWallAt(new Position(2, 7,g.getMaxX()), new Position(7, 7,g.getMaxX()));
        
        intialized = true;
        
    }
    /**
     * The function generates a demo setup for a game by creating a treasure, moving it to a new
     * position, adding a hunter and a tool, setting a vertical wall, and generating a wall between two
     * positions.
     */
    public void generateDemoSetupB(){
        createTesor();
        g.removeOccupant(g.getTresor().getPos(), g.getTresor());
        g.getTresor().getPos().setX(7);
        g.getTresor().getPos().setY(8);
        g.addOccupant(g.getTresor().getPos(), g.getTresor());
        g.setMurVertical(true);
        createHunter(3,4,1,1);
        createTool(4,5);
        generateWallAt(new Position(2, 7,g.getMaxX()), new Position(7, 7,g.getMaxX()));
        
        intialized = true;
        
    }
    /**
     * The function generates a demo setup for a game by creating a treasure, moving it to a new
     * position, setting a vertical wall, creating a hunter, a map, a tool, and generating a wall at a
     * specific position.
     */
    public void generateDemoSetupC(){
        createTesor();
        g.removeOccupant(g.getTresor().getPos(), g.getTresor());
        g.getTresor().getPos().setX(7);
        g.getTresor().getPos().setY(8);
        g.addOccupant(g.getTresor().getPos(), g.getTresor());
        g.setMurVertical(true);
        createHunter(3,4,-1,-1);
        createCarte(1,2);
        createTool(4,5);
        generateWallAt(new Position(2, 7,g.getMaxX()), new Position(7, 7,g.getMaxX()));
        
        intialized = true;
        
    }

    /**
     * The function generates a demo setup for a game by creating a treasure, moving it to a new
     * position, setting a vertical wall, creating a hunter, a map, a tool, and generating a wall at a
     * specific position.
     */
    public void generateDemoSetupD(){
        createTesor();
        g.removeOccupant(g.getTresor().getPos(), g.getTresor());
        g.getTresor().getPos().setX(8);
        g.getTresor().getPos().setY(1);
        g.addOccupant(g.getTresor().getPos(), g.getTresor());
        g.setMurVertical(false);
        createHunter(1,4,1,0);
        createGlue(4,1);
        generateWallAt(new Position(3,2,g.getMaxX()), new Position(3, 7,g.getMaxX()));
        
        intialized = true;
        
    }

    /**
     * This function generates default parameters for a game by setting percentages for different types
     * of game elements.
     */
    public  void generateDefaultParam(){
        generateWall();
        createTesor();
        
        int gluePourcent=3;
        int cartePourcent=3;
        int hunterPourcent=3;
        int sagePourcent=2;
        int cheaterPourcent=2;
        int toolPourcent=2;
        CustomParm c=new CustomParm(gluePourcent,cartePourcent,hunterPourcent,sagePourcent,cheaterPourcent,toolPourcent);
        generateParam(c);
        
        intialized = true;
        
    }


/**
 * The function generates game parameters based on the input custom parameters and adds corresponding
 * occupants to the game board.
 * 
 * @param c A CustomParm object that contains the percentages of different types of occupants to be
 * generated in the game grid. These include glue, carte, hunter, sage, cheater, and tool.
 */
    public void generateParam(CustomParm c){
        int gluePourcent=c.getGluePourcent();
        int cartePourcent=c.getCartePourcent();
        int hunterPourcent=c.getHunterPourcent();
        int sagePourcent=c.getSagePourcent();
        int cheaterPourcent=c.getCheaterPourcent();
        int toolPourcent=c.getToolPourcent();


        int total=g.getMaxX()*g.getMaxY();
        int nbrHunter=total/100*hunterPourcent;
        int nbrSage=total/100*sagePourcent;
        int nbrCheater=total/100*cheaterPourcent;
        int nbrTool=total/100*toolPourcent;
        if((nbrHunter)>24){
            nbrHunter=24;// 26 nombre total de lettre de a à z moins s et c qui sont les sages et cheaters
        }
        for(int i=0;i<(g.getMaxX()*g.getMaxY()/100*gluePourcent);i++){
            Position P=randomEmptyPosition();
            if(P==null){
                break;
            }
            Occupant glue=new Glue('&',P,g);
            g.addOccupant(glue.getPos(), glue);
        }
        for(int i=0;i<(g.getMaxX()*g.getMaxY()/100*cartePourcent);i++){
            Position P=randomEmptyPosition();
            if(P==null){
                break;
            }
            Occupant carte=new Carte('%',P,g);
            g.addOccupant(carte.getPos(), carte);
        }
        for(int i=0;i<nbrHunter;i++){
            createHunter();
        }
        for(int i=0;i<nbrSage;i++){
            nbrSag+=1;
            createSage();
        }
        for(int i=0;i<nbrCheater;i++){
            nbrCheat+=1;
            createCheater();
        }
        for(int i=0;i<nbrTool;i++){
            Position P=randomEmptyPosition();
            if(P==null){
                break;
            }
            Occupant tools=new Tool('@',P,g);
            g.addOccupant(tools.getPos(), tools);
        }
    }
    /**
     * This function creates a Carte object with a specified position and adds it to a game board.
     * 
     * @param x The x-coordinate of the position where the Carte object will be created.
     * @param y The y parameter represents the vertical position of the Carte object being created.
     */
    public void createCarte(int x,int y){
        Position P=new Position(x,y,g.getMaxX());
        Occupant carte=new Carte('%',P,g);
        g.addOccupant(carte.getPos(), carte);
    }
    /**
     * This function checks if there are any empty positions left on a game map.
     * 
     * @return The method is returning a boolean value. It returns true if there are no more empty
     * positions on the game map, and false otherwise.
     */
    public boolean noMoreEmptyPostion(){
        Position P=new Position(0,0,g.getMaxX());
        List<Occupant> l= g.getMap().get(P);
        while(!(l==null||l.isEmpty())){
            P.setX(P.getX()+1);
            if(P.getX()>=g.getMaxX()){
                P.setX(0);
                P.setY(P.getY()+1);
            }
            if(P.getY()>=g.getMaxY()){
                return true;
            }
            l= g.getMap().get(P);
        }
        return false;
    }

    /**
     * The function returns a random empty position on a game map.
     * 
     * @return The method is returning a Position object, which represents a random empty position on
     * the game map.
     */
    public Position randomEmptyPosition(){
        if(noMoreEmptyPostion()){
            return null;
        }


        Position P=new Position(0,0,g.getMaxX());
        List<Occupant> l= g.getMap().get(P);
        while(!(l==null||l.isEmpty())){
            P.setX((int)(Math.random()*g.getMaxX()));
            P.setY((int)(Math.random()*g.getMaxY()));
            l= g.getMap().get(P);
        }
        return P;
    }
    /**
     * This function checks if there is a Hunter object that has won among the mouvable objects.
     * 
     * @return The method `isWin()` returns a boolean value. The method `isWin()` is checking if there
     * is a `Hunter` object in the `mouvable` list and if that `Hunter` object has won the game. If
     * there is a `Hunter` object that has won, then the method returns `true`. Otherwise, it returns
     * `false`.
     */
    public boolean isWin(){
        for(ObjetMobile o:mouvable){
            if(o.isWin()&&o instanceof Hunter){
                return true;
            }
        }
        return false;
    }

    /**
     * This function creates a treasure object at a random empty position on a game board.
     */
    public void createTesor(){
        Position P=randomEmptyPosition();
        if(P==null){
            return;
        }
        Occupant tresor=new Tresor('$',P,g);
        g.addOccupant(tresor.getPos(), tresor);
        g.setTresor(tresor);
    }
    /**
     * This Java function creates a Cheater object at a random empty position on a game board.
     */
    public void createCheater(){
        Position P=randomEmptyPosition();
        if(P==null){
            return;
        }
        Occupant cheater=new Cheater((char)('C'),P,g);
        g.addOccupant(cheater.getPos(), cheater);
        mouvable.add((ObjetMobile)cheater);
    }
    /**
     * The function creates a new Hunter object with a given position and direction, adds it to a list
     * of mobile objects, and assigns it a unique identifier.
     * 
     * @param x The x-coordinate of the position where the Hunter object will be created.
     * @param y The y parameter represents the vertical position of the Hunter object being created.
     * @param dirX dirX is the horizontal direction of the Hunter's movement. It can be either -1
     * (left), 0 (no horizontal movement), or 1 (right).
     * @param dirY dirY is the direction of movement in the y-axis for the Hunter object being created.
     * It is an integer value that can be either positive or negative, indicating the direction of
     * movement upwards or downwards respectively.
     */
    public void createHunter(int x, int y,int dirX,int dirY){
        int nbr=mouvable.size()-nbrCheat-nbrSag;
        if(nbr>1){
            nbr++;
            if(nbr>17){
                nbr++;
            }
        }
        Occupant perso=new Hunter((char)('A'+nbr),new Position(x, y,g.getMaxX()),g);
        g.addOccupant(perso.getPos(), perso);
        mouvable.add((ObjetMobile)perso);
        ((ObjetMobile)perso).setDirX(dirX);
        ((ObjetMobile)perso).setDirY(dirY);
    }
    /**
     * The function creates a Sage object with a given position and direction and adds it to a list of
     * mobile objects.
     * 
     * @param x The x-coordinate of the position where the Sage object will be created.
     * @param y The y parameter represents the vertical position of the Sage object being created.
     * @param dirX dirX is the direction of movement in the x-axis for the Sage object being created.
     * It is an integer value that can be either positive or negative, indicating the direction of
     * movement to the right or left, respectively.
     * @param dirY dirY is the direction of movement in the y-axis for the Sage object being created.
     * It is an integer value that can be either positive or negative, indicating the direction of
     * movement upwards or downwards respectively.
     */
    public void createSage(int x, int y,int dirX,int dirY){
        int nbr=mouvable.size()-nbrCheat-nbrSag;
        if(nbr>1){
            nbr++;
            if(nbr>17){
                nbr++;
            }
        }
        Occupant sage=new Sage((char)('S'+nbr),new Position(x, y,g.getMaxX()),g);
        g.addOccupant(sage.getPos(), sage);
        mouvable.add((ObjetMobile)sage);
        ((ObjetMobile)sage).setDirX(dirX);
        ((ObjetMobile)sage).setDirY(dirY);
    }

    /**
     * This function creates a new Hunter object and adds it to a list of mobile objects if there is an
     * available position on the game board.
     */
    public void createHunter(){
        int nbr=mouvable.size()-nbrCheat-nbrSag;
        if(nbr>1){
            nbr++;
            if(nbr>17){
                nbr++;
            }
        }
        Position P=randomEmptyPosition();
        if(P==null){
            return;
        }
        
        Occupant perso=new Hunter((char)('A'+nbr),P,g);
        g.addOccupant(perso.getPos(), perso);
        mouvable.add((ObjetMobile)perso);
    }
    /**
     * This function creates a Sage object at a random empty position on a game board.
     */
    void createSage(){
        Position P=randomEmptyPosition();
        if(P==null){
            return;
        }
        Occupant perso=new Sage((char)('S'),P,g);
        g.addOccupant(perso.getPos(), perso);
        mouvable.add((ObjetMobile)perso);
    }



/**
 * The function iterates through a list of movable objects and performs their movements until a hunter
 * wins or there are no more movable objects.
 * 
 * @return The method is returning a boolean value. It returns true if all the movable objects have
 * been moved and a hunter has won the game, and false otherwise.
 */
    public boolean nextTour(){
        if(mouvable.isEmpty()){
            return true;
        }


        if(it>=mouvable.size()){
            nbrTour++;
            it=0;
        }
        if(it<0){
            it=0;
        }
        while(it<mouvable.size()){
            mouvable.get(it).mouvement(g);
            
            if( mouvable.get(it).isWin()&& mouvable.get(it) instanceof Hunter){
                return true;
            }
            it++;
            
        }
        return false;
    }

    /**
    * This function executes one turn of a game by moving an object and checking if it has won.
    * 
    * @return The method `oneTurn()` returns a boolean value. If all the movable objects have been
    * moved and there are no more moves left, it returns `true`. If a Hunter object has reached the
    * target and won the game, it also returns `true`. Otherwise, it returns `false`.
    */
    public boolean oneTurn(){
        
        if(mouvable.isEmpty()){
            return true;
        }


        if(it>=mouvable.size()){
            nbrTour++;
            it=0;
        }
        if(it<0){
            it=mouvable.size()-1;
        }
        ObjetMobile o=mouvable.get(it);
        System.out.println(this);
        System.out.println(o.getPos());
        o.mouvement(g);
        it=it+1;
        if(o.isWin()&&o instanceof Hunter){
            return true;
        }
        return false;
        
    }


    /**
     * This Java function generates a random integer between a minimum and maximum value, inclusive.
     * 
     * @param min The minimum value that can be generated by the method.
     * @param max The maximum value that can be generated by the method.
     * @return The method is returning an integer value that is randomly generated between the minimum
     * and maximum values provided as parameters.
     */
    public int generate(int min,int max){

        if(max<min){
            return max;
        }

        return (int)(Math.random()*(max-min+1)+min);
    }
    /**
     * The function generates a random number of walls either vertically or horizontally and places
     * them on the game board.2
     */
    public void generateWall(){
        //vertical ou horizontal
        int nbrMur;
        if(g.isMurVertical()){
            nbrMur=(int)(Math.random()*g.getMaxX()/3)+1;
        }
        else{
            nbrMur=(int)(Math.random()*g.getMaxY()/3)+1;
        }
        int min=3;
        int x;
        int y;
        int x2;
        int y2;
        for(int i=0;i<nbrMur;i++){
            if(g.isMurVertical()){
                x=generate(2,g.getMaxX()-3);
                y=generate(min,g.getMaxY()-(2+nbrMur-i));
                x2=generate(x,g.getMaxX()-3);
                y2=y;
                if(y>=min){
                    generateWallAt(new Position(x,y,g.getMaxX()),new Position(x2,y2,g.getMaxX()));
                }
                min=y2+2;
                
                
            }
            else{
                x=generate(min,g.getMaxX()-(2+nbrMur-i));
                y=generate(2,g.getMaxY()-3);
                x2=x;
                y2=generate(y,g.getMaxY()-3);
                if(y>=min){
                    generateWallAt(new Position(x,y,g.getMaxX()),new Position(x2,y2,g.getMaxX()));
                }
                min=x+2;
            }
        }
    }

    

    /**
     * This Java function creates a tool object at a specified position and adds it to a game board.
     * 
     * @param x The x-coordinate of the position where the tool will be created.
     * @param y The parameter "y" represents the vertical position of the tool being created on the
     * game board.
     */
    public void createTool(int x,int y){
        Position P=new Position(x,y,g.getMaxX());
        Occupant tools=new Tool('@',P,g);
        g.addOccupant(tools.getPos(), tools);
    }

    /**
     * This Java function creates a glue object at a specified position and adds it to a game board.
     * 
     * @param x The x-coordinate of the position where the glue will be created.
     * @param y The parameter "y" represents the vertical position of the glue being created on the
     * game board.
     */
    public void createGlue(int x,int y){
        Position P=new Position(x,y,g.getMaxX());
        Occupant glue=new Glue('&',P,g);
        g.addOccupant(glue.getPos(), glue);
    }

    /**
     * The function generates a wall of stones between two given positions in a game.
     * 
     * @param start The starting position of the wall to be generated. It is an object of the class
     * Position, which contains the x and y coordinates of the starting point.
     * @param end The "end" parameter is a Position object that represents the end point of the wall to
     * be generated.
     */
    public void generateWallAt(Position start,Position end){
        int x=start.getX();
        int y=start.getY();
        int x2=end.getX();
        int y2=end.getY();
        if(x==x2){
            for(int i=y;i<=y2;i++){
                Occupant bordure=new Pierre('#',new Position(x,i,g.getMaxX()),g);
                g.addOccupant(bordure.getPos(), bordure);
            }
        }else{
            for(int i=x;i<=x2;i++){
                Occupant bordure=new Pierre('#',new Position(i,y,g.getMaxX()),g);
                g.addOccupant(bordure.getPos(), bordure);
            }
        }
    }


    /**
     * @return return the liste of mouvable object
     */
    public List<ObjetMobile> getmouvable() {
        return mouvable;
    }

    /**
     * @param mouvable the mouvable to set
     */
    public void setmouvable(List<ObjetMobile> mouvable) {
        this.mouvable = mouvable;
    }

    /**
     * @return Grille return the g
     */
    public Grille getG() {
        return g;
    }

    /**
     * @param g the g to set
     */
    public void setG(Grille g) {
        this.g = g;
    }

    /**
     * @return int return the nbrSag
     */
    public int getNbrSag() {
        return nbrSag;
    }

    /**
     * @param nbrSag the nbrSag to set
     */
    public void setNbrSag(int nbrSag) {
        this.nbrSag = nbrSag;
    }

    /**
     * @return int return the nbrCheat
     */
    public int getNbrCheat() {
        return nbrCheat;
    }

    /**
     * @param nbrCheat the nbrCheat to set
     */
    public void setNbrCheat(int nbrCheat) {
        this.nbrCheat = nbrCheat;
    }

    /**
     * This function overrides the default toString() method to return the value of the variable g as a
     * string.
     * 
     * @return The method is returning the value of the variable `g` as a string.
     */
    @Override
    public String toString() {
        return ""+g;
    }

    /**
     * @return List<ObjetMobile> return the mouvable
     */
    public List<ObjetMobile> getMouvable() {
        return mouvable;
    }

    /**
     * @param mouvable the mouvable to set
     */
    public void setMouvable(List<ObjetMobile> mouvable) {
        this.mouvable = mouvable;
    }

    /**
     * @return Boolean return the intialized
     */
    public Boolean getIntialized() {
        return intialized;
    }

    /**
     * @param intialized the intialized to set
     */
    public void setIntialized(Boolean intialized) {
        this.intialized = intialized;
    }


    /**
     * @return int return the nbrTour
     */
    public int getNbrTour() {
        return nbrTour;
    }

    /**
     * @param nbrTour the nbrTour to set
     */
    public void setNbrTour(int nbrTour) {
        this.nbrTour = nbrTour;
    }

    /**
     * @return boolean return the intialized
     */
    public boolean isIntialized() {
        return intialized;
    }

    /**
     * @return int return the it
     */
    public int getIt() {
        return it;
    }
    public Occupant getwinner(){
        for(ObjetMobile o:mouvable){
            if(o.isWin()&&o instanceof Hunter){
                return (Occupant)o;
            }
        }
        return null;
    }
    /**
     * @param it the it to set
     */
    public void setIt(int it) {
        this.it = it;
    }

}
