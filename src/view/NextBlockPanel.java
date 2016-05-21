package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Block;
import model.Board;
import model.Point;
import model.TetrisPiece;

/**
 * Creates a panel to display the next block.
 * @author Zachariah Wingo
 * @version 1.0
 */
public class NextBlockPanel extends JPanel implements Observer {

    /** Screen Resolution width. */
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    
    /** Block Size. */
    private static final int BLOCK_SIZE =  SCREEN_WIDTH / 50;
    
    /** Horizontal Offset. */
    private static final int HORIZONTAL_OFFSET = 1;
    
    /** Vertical Offset. */
    private static final int VERTICAL_OFFSET = 4;
    
    /** Number of Blocks. */
    private static final int NUMBER_OF_BLOCKS = 4;
    
    /** The default width value. */
    private static final int WIDTH = BLOCK_SIZE * 6; 
    
    /** The default height value. */
    private static final int HEIGHT = BLOCK_SIZE * 6;
                    
    /** The minimum size this component should be. */
    private static final Dimension MIN_SIZE = new Dimension(WIDTH, HEIGHT);
    
    /** Auto generated serial version UID. */
    private static final long serialVersionUID = 3175993342008706807L;
    
    /** The Tetris piece. */
    private TetrisPiece myTetrisPiece;
    
    /**
     * Constructs a panel to display the next block.
     * @param theBoard the Tetris board model.
     */
    public NextBlockPanel(final Board theBoard) {
        super();

        final Board gameBoard = theBoard;
        gameBoard.addObserver(this);
        setBackground(Color.WHITE);
        setOpaque(true);
    }
    
    /**
     * Get minimum size.
     */
    @Override
    public Dimension getMinimumSize() {
        return MIN_SIZE;
    }
 
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        if (myTetrisPiece != null) {
            g2d.setColor(getBlockColor(myTetrisPiece.getBlock()));
            
            // getCords() returns a two dimensional integer array with the cords.
            final int[][]  p = getCords();

            for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
                g2d.fill(new Rectangle2D.Double(p[i][0], p[i][1], BLOCK_SIZE, BLOCK_SIZE));
            }
            
            // Sets color to white before we draw the outline of each block.
            g2d.setPaint(Color.WHITE);
            
            for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
                g2d.draw(new Rectangle2D.Double(p[i][0], p[i][1], BLOCK_SIZE, BLOCK_SIZE));
            }
        }
    }
    
    
    
    /**
     * Checks a block for its type and returns its color.
     * @param theBlock takes a block to check its type.
     * @return returns a color for the specified block.
     */
    private Color getBlockColor(final Block theBlock) {
        Color color;
        
        switch (theBlock) {
            case I:
                color = Color.decode("#47d2ff");
                break;
            case J:
                color = Color.decode("#3495b9");
                break;
            case L:
                color = Color.decode("#f4e45f");
                break;
            case O:
                color = Color.decode("#e54da7");
                break;
            case S:
                color = Color.decode("#57d969");
                break;
            case T:
                color = Color.decode("#f34444");
                break;
            case Z:
                color = Color.decode("#f17439");
                break;
            default:
                color = Color.DARK_GRAY;
                break;
        }
        return color;
    }
    
    /**
     * Helper method to calculate x, y cords.
     * @return returns a two-dimensional array of points for each block.
     */
    private int[][] getCords() {
        
        // Two-Dimensional Array to make points easier to access in loop.
        final int[][] newPoints = new int[NUMBER_OF_BLOCKS][2];
        
        // Gets an array of points from the Tetris piece.
        final Point[] points = myTetrisPiece.getPoints();
        
        // Gets the x, y cords and stores them in the points array.
        for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
            newPoints[i][0] = (HORIZONTAL_OFFSET + points[i].x()) * BLOCK_SIZE;
            newPoints[i][1] = (VERTICAL_OFFSET - points[i].y()) * BLOCK_SIZE;
        }
        
        return newPoints;
    }
    
    /**
     * Get preferred size.
     */
    @Override
    public Dimension getPreferredSize() {
        return MIN_SIZE;
    }

    @Override
    public void update(final Observable theO, final Object theArg) {
        
        if (theArg instanceof TetrisPiece) {
            myTetrisPiece = (TetrisPiece) theArg;
            repaint();
        }
    }
    
    
}