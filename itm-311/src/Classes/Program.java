package Classes;

 
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 * 
 * 
 * @author brady house
 */
public class Program extends JApplet implements ActionListener, Runnable {

    int frameNumber;
    int delay;
    Thread animatorThread;

    
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
        /// Get the frame rate and delay
        String str = getParameter("fps");
        int fps = (str != null) ? Integer.parseInt(str) : 10;
	delay = (fps > 0) ? (1000 / fps) : 100;
        
        /// Get the Knight Image path
        String _knightImagePath = getParameter("pKnightImagePath");
        
        Square _startingSquare = new Square(3,4);
        
        /// Create an instance
        /// of the Panel
        ChessBoard _board = new ChessBoard(500,500,_startingSquare);
        /// Add the board to the
        /// Applet window
        add(_board);
        
        // _board.setWhiteKnightLocation(_startingSquare);
       
        
    } // end init
    
    @Override
    public void start() {
	if (animatorThread == null) {
	    animatorThread = new Thread(this);
	    animatorThread.start();
	}
    }
    
    @Override
    public void stop() {
	animatorThread = null;
    }
    
    @Override
    public boolean mouseDown(Event e, int x, int y) {
	if (animatorThread == null) {
	    start();
	}
	else {
	    stop();
	}
	return false;
    }
    
    @Override
    public void run() {
	// Remember the starting time
	long startTime = System.currentTimeMillis();
	while (Thread.currentThread() == animatorThread) {
	    // Display the next frame of animation.
	    repaint();

	    // Delay depending on how far we are behind.
	    try {
		startTime += delay;
		Thread.sleep(Math.max(0, 
				      startTime-System.currentTimeMillis()));
	    } catch (InterruptedException e) {
		break;
	    }

	    // Advance the frame
	    frameNumber++;
	}
    }
    
    
}
