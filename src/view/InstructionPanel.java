package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a panel to display games statistics.
 * @author Zachariah Wingo
 * @version 1.0
 */
public class InstructionPanel extends JPanel {

    /** Auto Generated serial version UID. */
    private static final long serialVersionUID = -2886374758484764169L;

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
 
    
    
   

    /**
     * Constructs a panel to display game statistics.
     */
    public InstructionPanel() {
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT));    
        hangKeys();
    }
    
    /**
     * Private helper method for creating labels and hanging them on the panel.
     */
    private void hangKeys() {
        final Font font = new Font(Font.SANS_SERIF, 20, FONT_SIZE);
        final JLabel newGame = new JLabel("New Game: N");
        final JLabel endGame = new JLabel("End Game: E");
        final JLabel left    = new JLabel("Left: Left Aarow");
        final JLabel right   = new JLabel("Right: Right Aarow");
        final JLabel down    = new JLabel("Down: Down Aarow");
        final JLabel rotateCW  = new JLabel("Rotate CW: Up Aarow");
        final JLabel rotateCCW = new JLabel("Rotate CCW: X");
        
        // Hanger
        final JPanel hanger = new JPanel(new GridLayout(8, 1));
        hanger.add(newGame);
        hanger.add(endGame);
        hanger.add(left);
        hanger.add(right);
        hanger.add(down);
        hanger.add(rotateCW);
        hanger.add(rotateCCW);
        
        hanger.setOpaque(true);
        hanger.setBackground(Color.white);
        
        // Set font, font size, etc..
        newGame.setFont(font);
        endGame.setFont(font);
        left.setFont(font);
        right.setFont(font);
        down.setFont(font);
        rotateCW.setFont(font);
        rotateCCW.setFont(font);
        
        add(hanger);
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
        
    
    
}