package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Block;
import model.Board;

/**
 * Creates a Tetris playing board.
 * @author Zachariah WIngo
 * @version 1.0
 */
public class BoardPanel extends JPanel implements Observer {

    /** Auto generated serial version UID. */
    private static final long serialVersionUID = 7784901084261352281L;
    
    /** Screen Resolution width. */
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    
    /** Font size for the game over panel. */
    private static final int GAME_OVER_FONT_SIZE = 40;
    /** Block Size. */
    private static final int BLOCK_SIZE =  SCREEN_WIDTH / 50;
    
    /** General Constant 2. */
    //private static final int TWO = 2;
    
    /** General Constant  3. */
    private static final int THREE = 3;
    
    /** General Constant  4. */
    private static final int FOUR = 4;
    
    /** General Constant  5. */
    //private static final int FIVE = 5;
    
    /** General Constant  6. */
    private static final int SIX = 6;
    
    /** General Constant  7. */
    //private static final int SEVEN = 7;
    
    /** General Constant  8. */
    private static final int EIGHT = 8;
    
    /** General Constant  9. */
    //private static final int NINE = 9;
    
    /** General Constant  10. */
    private static final int TEN = 10;
    
    /** The default width value. */
    private static final int WIDTH = BLOCK_SIZE * 10; 
    
    /** The default height value. */
    private static final int HEIGHT = BLOCK_SIZE * 20;
    
    /** The minimum size this component should be. */
    private static final Dimension MIN_SIZE = new Dimension(WIDTH, HEIGHT);
    
    /** The default speed of the blocks in milliseconds. */
    private static final int DEFAULT_SPEED = 500;
    
    /** The height needed for displaying board pieces. */
    private final int myDisplayHeight;
    
    /** Timer for game. */
    private final Timer myGameTimer;
    
    /** Tetris Board model. */
    private final Board myGameBoard;
    
    /** Game over status. */
    private boolean myGameOver;
    
    /** Game pause status. */
    private boolean myGamePaused;
    
    /** Current Board. */
    private List<?> myBoardData;
    
    /**
     * Constructs a panel for a Tetris board.
     * @param theGameBoard takes a Tetris Board.
     */
    public BoardPanel(final Board theGameBoard) {
        super();
        myGameBoard = theGameBoard;
        myGameBoard.addObserver(this);
        myGameOver = false;
        myGamePaused = true;
        myGameTimer = new Timer(DEFAULT_SPEED, new GameListener());
        myBoardData = new ArrayList<Block[]>();
        setBackground(Color.GRAY);
        setOpaque(true);
        //myGameBoard.newGame();
        myDisplayHeight = myGameBoard.getHeight() - 1;
    }
    
    /**
     * Is game running.
     * @return returns true if timer is running and false otherwise.
     */
    public boolean isRunning() {
        return myGameTimer.isRunning();
    }
    
    /**
     * Pause game.
     */
    public void pauseGame() {
        
        if (myGamePaused && !myGameOver) {
            myGameTimer.start();
            myGamePaused = false;
        } else {
            myGameTimer.stop();
            myGamePaused = true;
        }
    }

    /**
     * Increase speed by 50 milliseconds.
     */
    public void increaseSpeed() {
        final int time = myGameTimer.getDelay() - 50;
        myGameTimer.setDelay(time);
    }
    
    /**
     * Specifies if game is paused.
     * @return returns true if paused and false if not.
     */
    public boolean isPaused() {
        return myGamePaused;
    }
    
    /**
     * End game.
     */
    public void gameOver() {
        pauseGame();
        myGameTimer.stop();
        myGameOver = true;
        repaint();
    }
    
    /**
     * Restarts the game.
     */
    public void newGame() {
        if (!myGameTimer.isRunning()) {
            myGameTimer.setDelay(DEFAULT_SPEED);
            myGameTimer.restart();
            myGameOver = false;
            myGamePaused = false;
            myGameBoard.newGame();
        }
    }
    
    /**
     * Specifies if game is over.
     * @return returns true if game-over and false if not.
     */
    public boolean isGameOver() {
        return myGameOver;
    }
    
    /**
     * Get minimum size.
     */
    @Override
    public Dimension getMinimumSize() {
        return MIN_SIZE;
    }
 
    
    /**
     * Get preferred size.
     */
    @Override
    public Dimension getPreferredSize() {
        return MIN_SIZE;
    }
    
    @Override
    public void update(final Observable theO, final Object theArgs) {
        
        if (theArgs instanceof ArrayList) {
            myBoardData = (ArrayList<?>) theArgs;
            repaint();
        } else if (theArgs instanceof Boolean) {
            gameOver();
        }
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (int rows = 0; rows < myBoardData.size(); rows++) {
            final Block[] row = (Block[]) myBoardData.get(rows);
            
            for (int col = 0; col < row.length; col++) {
                
                if (row[col] == null) {
                    g2d.setColor(Color.WHITE);
                } else if (row[col] != null) {
                    g2d.setColor(getBlockColor(row[col]));
                }
                
                g2d.fill(new Rectangle2D.Double(col * BLOCK_SIZE,
                                               (myDisplayHeight - rows) * BLOCK_SIZE, 
                                               BLOCK_SIZE, BLOCK_SIZE));
                g2d.setColor(Color.WHITE);
                g2d.draw(new Rectangle2D.Double(col * BLOCK_SIZE,
                                               (myDisplayHeight - rows) * BLOCK_SIZE,
                                               BLOCK_SIZE, BLOCK_SIZE));
            }
        }
        
        if (myGameOver) {
            g2d.setColor(Color.black);
            g2d.fill(new Rectangle2D.Double(BLOCK_SIZE, BLOCK_SIZE * FOUR, 
                                                BLOCK_SIZE * EIGHT + TEN + TEN, 
                                                BLOCK_SIZE * THREE));
            g2d.setColor(Color.red);
            g2d.setFont(new Font(Font.SANS_SERIF, 1, GAME_OVER_FONT_SIZE));
            g2d.drawString("GAME OVER", BLOCK_SIZE * 2, BLOCK_SIZE * SIX);
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
    
     // ***********   Inner Class Listener   *****************/
     
    /**
     * When timer increments this is called and the action is performed.
     * @author Zachariah Wingo
     *
     */
    private class GameListener implements ActionListener {
    
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myGameBoard.step();
        }
        
    }
    

}
