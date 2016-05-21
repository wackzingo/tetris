package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Board;
/**
 * Creates a GUI for a Tetris game.
 * @author Zachariah Wingo
 * @version 1.0
 */
public class TetrisGUI extends JFrame {

    /** Border color for panels. */
    private static final String BORDER_COLOR = "#ececec";
    
    /** Font size. */
    private static final int FONT_SIZE = 20;
    
    /** Auto generated serial version UID. */
    private static final long serialVersionUID = 9177528923224412823L;
    
    /** Tetris Board. */
    private final Board myBoard;
    
    /** The board panel. */
    private final BoardPanel myBoardPanel;
    
    /** The Next Block Panel. */
    private final NextBlockPanel myNextBlockPanel;
    
    /** The Stats panel. */
    private final StatsPanel myStatsPanel;
    
    /**
     * Constructor for the GUI.
     */
    public TetrisGUI() {
        super();
        setTitle("Tetris - 305 Winter 2016");
        myBoard = new Board();
        myBoardPanel = new BoardPanel(myBoard);
        myStatsPanel = new StatsPanel(myBoardPanel);
        myNextBlockPanel = new NextBlockPanel(myBoard);
    }
    
    /**
     * Starts the GUI by calling helper methods that construct various pieces.
     */
    public void startGUI() {
        // Sets default behavior upon closing.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //final StatsPanel statsPanel = new StatsPanel();
        add(createsTrisBoard(), BorderLayout.WEST);
        add(sidePanel());
        addKeyListener(new ControlListener());

        //Display the window.
        pack();
        setVisible(true); 
    }
    
    
    /**
     * Private helper method sets up the Tetris board.
     * @return returns a panel with the Tetris board.
     */
    private JPanel sidePanel() {
        // Panel to attach side panels like next block and scores.
        final JPanel sidePanel = new JPanel();
        final InstructionPanel instructions = new InstructionPanel();
        final JPanel hanger = new JPanel(new GridLayout(3, 1, 5, 5));
        
        final TitledBorder statsTitle = BorderFactory.createTitledBorder(
                                        BorderFactory.createLineBorder(
                                        Color.decode(BORDER_COLOR), 0), "STATS");
        
        statsTitle.setTitleJustification(TitledBorder.CENTER);
        statsTitle.setTitleFont(new Font(Font.SANS_SERIF, 1, FONT_SIZE));
        myStatsPanel.setBorder(statsTitle);
        myStatsPanel.setBackground(Color.white);
        myStatsPanel.setOpaque(true);
        
    
        final TitledBorder nextTitle = BorderFactory.createTitledBorder(
                                       BorderFactory.createLineBorder(
                                       Color.decode(BORDER_COLOR), 0), "NEXT PIECE");
        
        nextTitle.setTitleJustification(TitledBorder.CENTER);
        nextTitle.setTitleFont(new Font(Font.SANS_SERIF, 1, FONT_SIZE));
        myNextBlockPanel.setBorder(nextTitle);        
        myNextBlockPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        final TitledBorder instructionTitle = BorderFactory.createTitledBorder(
                                              BorderFactory.createLineBorder(
                                              Color.decode(BORDER_COLOR), 0), "Instructions");
        
        instructionTitle.setTitleJustification(TitledBorder.CENTER);
        instructionTitle.setTitleFont(new Font(Font.SANS_SERIF, 1, FONT_SIZE));
        instructions.setBorder(instructionTitle);        
        instructions.setLayout(new FlowLayout(FlowLayout.LEFT));
        instructions.setBackground(Color.white);
        instructions.setOpaque(true);
        
        myBoard.addObserver(myStatsPanel);
        hanger.add(myNextBlockPanel);
        hanger.add(myStatsPanel);
        hanger.add(instructions);
        sidePanel.add(hanger);
        
        return sidePanel;
    }
    
    /**
     * Private helper method sets up the Tetris board.
     * @return returns a panel with the Tetris board.
     */
    private JPanel createsTrisBoard() {
        // Panel to attach the board.
        final JPanel leftSide = new JPanel();
        myBoardPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 0));
        
        // Now we create the actual panel that represents the Tetris board.
        leftSide.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftSide.add(myBoardPanel);
        
        return leftSide;
    }
    
    /**
     * Private Helper Method.
     * @param theEvent is a key event.
     */
    private void movePieces(final KeyEvent theEvent) {
        
        switch (theEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                myBoard.left();
                break;
            case KeyEvent.VK_RIGHT:
                myBoard.right();
                break;
            case KeyEvent.VK_UP:
                myBoard.rotateCW();
                break;
            case KeyEvent.VK_X:
                myBoard.rotateCCW();
                break;
            case KeyEvent.VK_DOWN:
                myBoard.down();
                break;
            case KeyEvent.VK_SPACE:
                myBoard.drop();
                break;
            case KeyEvent.VK_E:
                myBoardPanel.gameOver();
                break;
            default:
                // Do nothing for now.
                break;
        }
    }
    
    // ***********   Inner Class Listener   *****************/
    /**
     * Class listens for keyboard input.
     *
     */
    private class ControlListener extends KeyAdapter {
       
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            super.keyPressed(theEvent);
            
            switch (theEvent.getKeyCode()) {
                
                case KeyEvent.VK_P:
                    myBoardPanel.pauseGame();
                    break;
                case KeyEvent.VK_N:
                    //myBoardPanel.gameOver();
                    myStatsPanel.reset();
                    myBoardPanel.newGame();
                    break;
                default:
                    break;
            }
            
            if (!(myBoardPanel.isGameOver() 
                || myBoardPanel.isPaused() || !myBoardPanel.isRunning())) {
                movePieces(theEvent);
            }
        }
    }
}
