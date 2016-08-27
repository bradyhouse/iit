package Classes;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import java.net.*; 

/**
 *
 * @author b
 */
public class ChessBoard extends  Applet implements ActionListener 
{
    /*
    * Private variable controlling the state of the
    * applet where:
    * 0 = Debug
    * 1 = Release
    * NOTE - In DEBUG mode all log message are writen
    * to the console. In addition, the routine only runs
    * Scenario 1 and then stops.
    */
    private static int MODE = 1;
    
    private Graphics2D objGraphic2D;
    private int Width;
    private int Height;
    private int squareWidth;
    private int squareHeight;
    private int actualWidth;
    private int actualHeight;
    private int margin = 10;
    private String ImagePath;
    private Square WhiteKnightLocation;
    private Square WhiteKnightPreviousLocation;

    
    /**
     * Class constructor.
     * @param pWidth integer value defining the width of the board in pixels.
     * @param pHeight integer value defining the height of the board in pixels.
     */
    public ChessBoard(int pWidth, int pHeight, Square pKnightLocation) {
     
        this.Width = pWidth;
        this.Height = pHeight;
        
        this.WhiteKnightLocation = pKnightLocation;
        
        double _dblSquareWidth = (pWidth-(this.margin*2)) / 8;
        double _dblSquareHeight = (pHeight-(this.margin*2)) / 8;
        
        this.squareHeight =(int) _dblSquareHeight;
        this.squareWidth = (int) _dblSquareWidth;
        this.actualHeight = (this.squareHeight * 8) + (this.margin * 2);
        this.actualWidth = (this.squareWidth * 8) + (this.margin * 2);
        
    } // end ChessBoard Constructor
    
    /**
     * Base class initialization
     * method override. It is necessary 
     * in order to configure
     * the background and
     * layout.
     */
    @Override
    public void init()
    {
        
    }
    
   
    
    @Override
    public void paint( Graphics pG )
    {
        int _w =this.getSquareWidth(),
            _h =this.getSquareHeight();

        this.objGraphic2D = (Graphics2D) pG;
        this.objGraphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

        this.objGraphic2D.setPaint(Color.gray);
        
        Color _colorWhite = Color.LIGHT_GRAY;
        Color _colorDark = Color.DARK_GRAY;
        Color _colorCurrent = Color.LIGHT_GRAY;
      int _y = this.margin;
      
      /// Iterate through each row
      for (int _r = 0; _r < 7; _r++)
      {
          /// Reset the column to physical
          /// position
          int _x = this.margin;
          /// Iterate through each column
          /// position for the row
          for(int _c = 0; _c < 7; _c++)
          {
               /// Draw Rectangle using the current color
               this.objGraphic2D.setPaint(_colorCurrent);
               
                if (_r == this.WhiteKnightPreviousLocation.getX() &&
                     _c== this.WhiteKnightPreviousLocation.getY())
               {
                   this.objGraphic2D.fill(new Rectangle2D.Double(_x, _y, _w, _h));
               } // end if
               else if (_r == this.WhiteKnightLocation.getX() &&
                       _c == this.WhiteKnightLocation.getY())
               {
                   this.objGraphic2D.fill(new Rectangle2D.Double(_x, _y, _w, _h));
                   this.objGraphic2D.setColor(Color.CYAN);
                   this.objGraphic2D.drawLine(_x,_y,_x+_w,_y+_h);
                   this.objGraphic2D.drawLine(_x, _y+_h, _x+_w, _y);
               } // end if
               else
               {
                   this.objGraphic2D.fill(new Rectangle2D.Double(_x, _y, _w, _h));
               } // end else
               /// Increment Column position
               _x += this.getSquareWidth();
               /// Swap colors for next pass
               if(_colorCurrent == _colorWhite)
                   _colorCurrent = _colorDark;
               else
                   _colorCurrent = _colorWhite;
          } // end for
          /// Increment Row position
          _y+=this.getSquareHeight();
      } // end for
      
    } // end paintComponent function

    /**
     * Getter used to access current location
     * of the white knight chess piece
     * 
     * @return Square object instance
     */
    public Square getWhiteKnightLocation() {
        return WhiteKnightLocation;
    }
    
    /**
     * Setter used to change the current location
     * of the white knight chess piece
     * 
     * @param WhiteKnightLocation square object defining the new
     * location of white knight chesspiece
     */
    public void setWhiteKnightLocation(Square WhiteKnightLocation) {
        this.WhiteKnightPreviousLocation = this.WhiteKnightLocation;
        this.WhiteKnightLocation = WhiteKnightLocation;
    }
    
    /**
     * Getter that returns the previous Square in which the
     * white knight was located
     * 
     * @return Square object instance
     */
    public Square getWhiteKnightPreviousLocation() {
        return WhiteKnightPreviousLocation;
    }
    
    /**
     * @return the Width
     */
    public int getWidth() {
        return Width;
    }
   
    /**
     * @return the Height
     */
    public int getHeight() {
        return Height;
    }

    /**
     * @return the squareWidth
     */
    public int getSquareWidth() {
        return squareWidth;
    }

    /**
     * @return the squareHeight
     */
    public int getSquareHeight() {
        return squareHeight;
    }

    /**
     * @return the margin
     */
    public int getMargin() {
        return margin;
    }

    /**
     * @return the actualWidth
     */
    public int getActualWidth() {
        return actualWidth;
    }

    /**
     * @return the actualHeight
     */
    public int getActualHeight() {
        return actualHeight;
    }
    
    /**
     * Getter used to access the knight image path
     * @return a string containing the configured path of the knight image.
     */
    public String getImagePath() {
        return ImagePath;
    }
    
    /**
     * Setter used to assign the image path of the knight
     * @param ImagePath string value defining the new image path of the knight
     * image
     */
    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
        /*
     * Wrapper method for writing debug output.  When the
     * debug bit is enabled, this method will output messages
     * to console. When it is disabled, the output is disabled
     */
    public static void logPipe(String pMessage)
    {
        try 
        {
            if( MODE == 0 )
            {
                java.util.Date ldate= new java.util.Date();
                java.util.Date ltime = new java.util.Date(ldate.getTime());
                System.out.println(ltime.getSeconds() + " : " + pMessage);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
} // end ChessBoard
