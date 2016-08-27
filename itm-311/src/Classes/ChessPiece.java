package Classes;

/**
 * Class defining a Chess Piece.
 */
public class ChessPiece {

    /*
     * Enumeration defining 
     * Color of the Chess Piece.
     */
    public enum Color {
        WHITE {
           @Override
            public String toString() {
                return "White";
            }
        }, 
        BLACK {
            @Override
            public String toString() {
                return "Black";
            }
        }
    }
    /*
     * Enumeration defining the
     * type of chess piece.
     */
   public enum Type {
    KNIGHT {
        @Override
        public String toString() {
            return "Knight";
        }
    },
    ROOKE {
        @Override
        public String toString() {
            return "Rooke";
        }
    }, 
    CASTLE {
        @Override
        public String toString() {
            return "Castle";
        }
    } , 
    PAWN {
        @Override
        public String toString() {
            return "Pawn";
        }
    } ,
    KING {
        @Override
        public String toString() {
            return "King";
        }
    } , 
    QUEEN {
        @Override
        public String toString() {
            return "Queen";
        }
    }
   }

   private Type lType;
   private Color lColor;

   /*
    * Class constructor. A chess piece
    * requires that a color and type
    * be defined.
    */
   public ChessPiece(Type pType, Color pColor){

       lType = pType;
       lColor = pColor;
   }

   /*
    * Color property accessor.
    */
   public Color getColor()
   {
       return lColor;
   }
   /*
    * Type property accessor.
    */
   public Type getType()
   {
       return lType;
   }
   /*
    * Override of the class to string inorder
    * to return the color and piece type attributes.
    */
    @Override
    public String toString() {
        return this.lColor.toString() + this.lType.toString();
    }
    
    /*
     * Value function that abstracts the
     * logic needed by the equals override.
     */
    private boolean areEqual(ChessPiece aThis, ChessPiece aThat){
        return aThis.getType() == aThat.getType()&&
               aThis.getColor() == aThat.getColor();
    }

        
    public enum KnightHorizontalMoveType {
        HORIZONTAL_0, HORIZONTAL_1,HORIZONTAL_2,
        HORIZONTAL_3, HORIZONTAL_4,HORIZONTAL_5,
        HORIZONTAL_6, HORIZONTAL_7
    }
    
    public enum KnightVerticalMoveType {
        VERTICAL_0,VERTICAL_1,VERTICAL_2,VERTICAL_3,
        VERTICAL_4,VERTICAL_5,VERTICAL_6,VERTICAL_7
    } 
    
    /*
     * Override the default object equal operator
     * so that square equality is based on
     * a comparison of the property values.
     */
    @Override public boolean equals(Object aThat) {
        //check for self-comparison
        if ( this == aThat ) return true;
        // check Class Type is different
        if ( !(aThat instanceof ChessPiece)) return false;
        //cast to native object is now safe
        ChessPiece lthat = (ChessPiece)aThat;
        return areEqual(this,lthat);
    }
}
