/*
 * TCSS 305 - Winter 2016
 * Tetris game.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import model.TetrisPiece;

/**
 * Creates a panel to display games statistics.
 * @author Zachariah Wingo
 * @version 1.0
 */
public class StatsPanel extends JPanel implements Observer {

    /** Screen Resolution width. */
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    
    /** Block Size. */
    private static final int BLOCK_SIZE =  SCREEN_WIDTH / 50;
    
    /** The default width value. */
    private static final int WIDTH = BLOCK_SIZE * 6; 
    
    /** The default height value. */
    private static final int HEIGHT = BLOCK_SIZE * 6;
    
    /** DEFAULT FONT SIZE. */
    private static final int FONT_SIZE = SCREEN_WIDTH / 100;
                    
    /** The minimum size this component should be. */
    private static final Dimension MIN_SIZE = new Dimension(WIDTH, HEIGHT);
    
    /** The number of lines that can be clear. */
    //private static final int LINE_COMBOS = 4;
    
    /** Points per block. */
    private static final int BLOCK_POINTS = 4;
    
    /** Points for completing one line. */
    private static final int ONE_LINE_POINTS = 40;
    
    /** Points for completing two lines. */
    private static final int TWO_LINE_POINTS = 100;
    
    /** Points for completing three lines. */
    private static final int THREE_LINE_POINTS = 300;
    
    /** Points for completing four lines. Tetris! */
    private static final int FOUR_LINE_POINTS = 1200;
    
    /** Line 1. */
    private static final int ONE_LINE = 1;
    
    /** Line 2. */
    private static final int TWO_LINE = 2;
    
    /** Line 3. */
    private static final int THREE_LINE = 3;
    
    /** Line 4. */
    private static final int FOUR_LINE = 4;
    
    /** The number of lines to clear to next level. */
    private static final int CLEAR_TO_NEXT = 5;
    
    /** Auto generated serial version UID. */
    private static final long serialVersionUID = -1864615994620091368L;
    
    /** Total lines cleared count. */
    private int myLinesClearedCount;
    
    /** Label for current number of lines cleared. */
    private final JLabel myLinesClearedLbl;
    
    /** Label for score. */
    private final JLabel myScoreLbl;
    
    /** Label for level. */
    private final JLabel myLevelLbl;
    
    /** Label for lines to go. */
    private final JLabel myLinesToLevelUpLbl;
    
    /** Lines to go for next level. */
    private int myLinesToLevelUp;
    
    /** Score. */
    private int myScore;
    
    /** Current Level. */
    private int myLevel;
    
    /** Tetris Board Panel. */
    private final BoardPanel myBoardPanel;

    /**
     * Constructs a panel to display game statistics.
     * @param thePanel is the game panel.
     */
    public StatsPanel(final BoardPanel thePanel) {
        super();
        myBoardPanel = thePanel;
        myLinesClearedLbl = new JLabel("Lines Cleared: 0");
        myLevelLbl = new JLabel("Level: 1");
        myScoreLbl = new JLabel("Score: 0");
        myLinesToLevelUpLbl = new JLabel("Lines to next level: 5");
        myScore = 0;
        myLevel = 1;
        hangStats();
    }
    
    /**
     * Private helper method to hang labels on the panel.
     */
    private void hangStats() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        final Font font = new Font(Font.SANS_SERIF, 1, FONT_SIZE);
        // Hanger
        final JPanel hanger = new JPanel(new GridLayout(5, 1));
        hanger.add(myScoreLbl);
        hanger.add(myLevelLbl);
        hanger.add(myLinesClearedLbl);
        hanger.add(myLinesToLevelUpLbl);
        
        hanger.setOpaque(true);
        hanger.setBackground(Color.white);
        
        // Set font, font size, etc..
        myLevelLbl.setFont(font);
        myLinesClearedLbl.setFont(font);
        myScoreLbl.setFont(font);
        myLinesToLevelUpLbl.setFont(font);
        add(hanger);
    }
    
    /**
     * Updates the lines cleared label.
     */
    private void updateLinesCleared() {
        myLinesClearedLbl.setText("Lines Cleared: " + myLinesClearedCount);
    }
    
    /**
     * Updates the level.
     */
    private void updateLevel() {
        myLevelLbl.setText("Level: " + myLevel);
    }
    
    /**
     * Updates the label that displays the score.
     */
    private void updateScore() {
        myScoreLbl.setText("Score: " + myScore);
    }
    
    /**
     * Updates lines needed to level up.
     */
    private void updateLinesToGo() {
        myLinesToLevelUpLbl.setText("Lines to next Level: " + myLinesToLevelUp);
    }
    
    /**
     * Calculates the score.
     * @param theClearedLines is the number of lines cleared.
     */
    private void calculateScore(final int theClearedLines) {
        
        switch (theClearedLines) {
            case ONE_LINE:
                myScore += ONE_LINE_POINTS * myLevel;
                myLinesClearedCount += ONE_LINE;
                break;
            case TWO_LINE:
                myScore += TWO_LINE_POINTS * myLevel;
                myLinesClearedCount += TWO_LINE;
                break;
            case THREE_LINE:
                myScore += THREE_LINE_POINTS * myLevel;
                myLinesClearedCount += THREE_LINE;
                break;
            case FOUR_LINE:
                myScore += FOUR_LINE_POINTS * myLevel;
                myLinesClearedCount += FOUR_LINE;
                break;
            default:
                myScore += BLOCK_POINTS;
                break;
        }
        
    }
    
    /**
     * Calculates Level.
     */
    private void calculateLevel() {
        if (myLinesClearedCount >= (CLEAR_TO_NEXT * myLevel)) {
            myLevel++;
            myBoardPanel.increaseSpeed();
            myLinesClearedCount++;
            myLinesToLevelUp = (CLEAR_TO_NEXT * myLevel) - myLinesClearedCount;
        } else {
            myLinesToLevelUp = (CLEAR_TO_NEXT * myLevel) - myLinesClearedCount;
        }
        updateLinesToGo();
        updateLevel();
    }
    
    /**
     * Resets all the stats.
     */
    protected void reset() {
        myLevel = 1;
        myLinesClearedCount = 0;
        myScore = 0;
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
    public void update(final Observable theO, final Object theArg) {
 

        if (theArg instanceof TetrisPiece) {
            updateScore();
            calculateScore(0);
            
        } else if (theArg instanceof Integer[]) {

            final int clearedLines = ((Integer[]) theArg).length; 
            calculateScore(clearedLines);
            calculateLevel();
            updateLinesCleared();
        }
        
        
    }
    
    
}