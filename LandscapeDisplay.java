import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JLabel;


import java.util.*;

/**
 * Displays a Landscape graphically using Swing.  The Landscape
 * can be displayed at any scale factor.
 * @author bseastwo
 */
public class LandscapeDisplay extends JFrame
{
    protected Landscape scape;
    private LandscapePanel canvas;
    private int gridScale; // width (and height) of each square in the grid

    /**
     * Initializes a display window for a Landscape.
     * @param scape the Landscape to display
     * @param scale controls the relative size of the display
     */
    public LandscapeDisplay(Landscape scape, int scale)
    {
        // setup the window
        super("Hunt The Wumpus Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.scape = scape;
        this.gridScale = scale;

        // create a panel in which to display the Landscape
        this.canvas = new LandscapePanel( (int) this.scape.getW() * this.gridScale,
                                        (int) this.scape.getH() * this.gridScale);

        // add the panel to the window, layout, and display
        this.add(this.canvas, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Saves an image of the display contents to a file.  The supplied
     * filename should have an extension supported by javax.imageio, e.g.
     * "png" or "jpg".
     *
     * @param filename  the name of the file to save
     */
    public void saveImage(String filename)
    {
        // get the file extension from the filename
        String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

        // create an image buffer to save this component
        Component tosave = this.getRootPane();
        BufferedImage image = new BufferedImage(tosave.getWidth(), tosave.getHeight(), 
                                                BufferedImage.TYPE_INT_RGB);

        // paint the component to the image buffer
        Graphics g = image.createGraphics();
        tosave.paint(g);
        g.dispose();

        // save the image
        try
                {
                        ImageIO.write(image, ext, new File(filename));
                }
        catch (IOException ioe)
                {
                        System.out.println(ioe.getMessage());
                }
    }

    /**
     * This inner class provides the panel on which Landscape elements
     * are drawn.
     */
    private class LandscapePanel extends JPanel
    {
        /**
         * Creates the panel.
         * @param width     the width of the panel in pixels
         * @param height        the height of the panel in pixels
         */
        public LandscapePanel(int width, int height)
        {
                super();
                this.setPreferredSize(new Dimension(width, height));
                this.setBackground(new Color(107,142,100));
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen.  The supplied Graphics
         * object is used to draw.
         * 
         * @param g     the Graphics object used for drawing
         */
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            scape.draw( g, gridScale ); // paint all the vertices foreground + hunter

            
        } // end paintComponent
        
    } // end LandscapePanel

    public void update() {
        Graphics g = canvas.getGraphics();
        this.requestFocus();
        canvas.paintComponent( g );
    }

	
	//main method to display Landscape
    public static void main(String[] args) throws InterruptedException {
        
        Landscape scape = new Landscape(5, 5);
        Vertex v = new Vertex();
        Hunter h = new Hunter(v);
        v.visible();
        scape.add(v);
        scape.addHunter(h);
        Random gen = new Random();
        int sim = 0;
        
        LandscapeDisplay display = new LandscapeDisplay(scape, 100);
        
        for(int i=0;i<100;i++) {
            scape.Update();
            //display.update();
            Thread.sleep( 250 );
            display.repaint();
        }
    }
}
