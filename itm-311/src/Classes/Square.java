package Classes;

/**
 * Class representing
 * a single chessboard
 * square.  
 */
public class Square extends Object
{
    /*
     * Enumeration used to
     * set the state of the
     * Square. By default,
     * the Square is empty.
     */
    public enum State {
        // Contains a piece
        OCCUPIED, 
        // Does not contain a piece
        EMPTY
    }
    
    private Integer lX,
            lY;
    private State lState;
    private ChessPiece lOccupant;
    private ChessPiece lLastOccupant;       
    
    /*
     * Default constructor.
     */
    public Square()
    {
        this.lX = 0;
        this.lY = 0;
        this.lState = State.EMPTY;
        this.lOccupant = null;
        this.lLastOccupant = null;
    }
    
    /*
     * Overloaded constructor which
     * accepts a specific coordinates
     * to initialize the Square with.
     */
    public Square(Integer pX, Integer pY)
    {
        this.lX = pX;
        this.lY = pY;
        this.lState = State.EMPTY;
        this.lOccupant = null;
        this.lLastOccupant = null;
    }
    
    /*
     * Second Overload which allows the
     * square to be initialized with
     * an occupant piece.
     */
    public Square(Integer pX, Integer pY, ChessPiece pPiece)
    {
        this.lX = pX;
        this.lY = pY;
        this.lState = State.OCCUPIED;
        this.lOccupant = pPiece;
        this.lLastOccupant = null;
    }
    
    /*
     * X coordinate property accessor.
     */
    public Integer getX()
    {
        return this.lX;
    }
    /*
     * X coordinate property mutator.
     */
    public void setX(Integer pX)
    {
        this.lX = pX;
    }
    /*
     * Y coordinate property accessor.
     */
    public Integer getY()
    {
        return this.lY;
    }
    /*
     * Y coordinate property mutator.
     */
    public void setY(Integer pY)
    {
        this.lY = pY;
    }
    /*
     * Occupant property accessor.
     */
    public ChessPiece getOccupant()
    {
        return this.lOccupant;
    }
    
    /*
     * Last occupant name property accessor.
     */
    public ChessPiece getLastOccupant()
    {
        return this.lLastOccupant;
    }
    
    /*
     * Flag indicating whether the square
     * is occupied.
     */
    public Boolean IsOccupied()
    {
        // If the square is occupied
        // then return true
        if (this.lState == State.OCCUPIED)
            return true;
        // Otherwise return false
        return false;
    }
    /*
     * Flag indicating whether the square
     * has ever been occupied
     */
    public Boolean HasBeenOccupied()
    {
        if ( this.lState == State.OCCUPIED ||
             null!= this.lLastOccupant )
            return true;
        else
            return false;
    }
    
    /*
     * Control function used to release the
     * square.  When invoked, this method
     * will set the occupant nam e to null
     * and return the state of the Square
     * to Empty.
     */
    public void release()
    {
        // If the square is occupied
        // then (1) update the last occupant
        // attribute, (2) Reset the current
        // occupant to null, (3) Change
        // the state to empty.
        
        if (this.lState == State.OCCUPIED)
        {
            this.lLastOccupant = this.lOccupant;
            this.lState = State.EMPTY;
            this.lOccupant = null;
        }
    }
    public Boolean setOccupant(ChessPiece pOccupant)
    {
        // If empty, (1) then assign the
        // occupant attribute, (2)
        // update the state to occupied.
        
        if(this.lState == State.EMPTY)
        {
            this.lLastOccupant = this.lOccupant;
            this.lOccupant = pOccupant;
            this.lState = State.OCCUPIED;
            return true;
        }
                
        // Otherwise return false.
        return false;
    }
    
    /*
    * Override of the class to string inorder
    * to return the color and piece type attributes.
    */
    @Override
    public String toString() {
        return this.getX() + ", " +
                this.getY();
    }
    
    /*
     * Value function that abstracts the
     * logic needed by the equals override.
     */
    private boolean areEqual(Square aThis, Square aThat){
        return aThis.IsOccupied() == aThat.IsOccupied() &&
            aThis.getX() == aThat.getX() &&
            aThis.getY() == aThat.getY();
    } // end areEqual
    
    /*
     * Override required in order to override the 
     * default equals operator. 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.lX != null ? this.lX.hashCode() : 0);
        hash = 23 * hash + (this.lY != null ? this.lY.hashCode() : 0);
        return hash;
    } // end hashCode

    /*
     * Override the default object equal operator
     * so that square equality is based on
     * a comparison of the property values.
     */
    @Override public boolean equals(Object aThat) {
        //check for self-comparison
        if ( this == aThat ) return true;
        // check Class Type is different
        if ( !(aThat instanceof Square) ) return false;
        //cast to native object is now safe
        Square lthat = (Square)aThat;
        return areEqual(this,lthat);
    } // end equals
    
}

