/**
 * File:  GamePanel.java
 * Date of Creation:  Jul 16, 2012
 */
package com.strixa.breakout.gui.panel;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import com.strixa.breakout.gui.gl.BreakoutCanvas;
import com.strixa.gl.Strixa2DCanvas;
import com.strixa.gui.StrixaWindow;
import com.strixa.gui.panel.StrixaPanel;
import com.strixa.util.Dimension2D;


/**
 * Panel which contains the canvas for the Breakout game.
 *
 * @author Nicholas Rogé
 */
public class GamePanel extends StrixaPanel{    
    /** Field required for object serialization. */
    private static final long serialVersionUID = -2998874595883563728L;
    
    private Strixa2DCanvas __canvas;
    
    
    /*Begin Initialization Methods*/
    /**
     * Initializes the GUI for this panel.
     */
    protected void _initializeGUI(){
        final GLCapabilities capabilities = new GLCapabilities(GLProfile.getDefault());
        
        
        capabilities.setRedBits(8);
        capabilities.setGreenBits(8);
        capabilities.setBlueBits(8);
        capabilities.setAlphaBits(8);
        
        capabilities.setHardwareAccelerated(true);
        capabilities.setDoubleBuffered(true);
        
        
        this.setSize(new java.awt.Dimension(1920,1200));
        
        this.__canvas = new BreakoutCanvas(new Dimension2D<Integer>(1920,1200),capabilities);
        this.__canvas.setFPS(60);
        this.add(this.__canvas);
    }
    /*End Initialization Methods*/
    
    /*Begin Constructors*/
    /**
     * Constructs a new GamePanel panel.
     * 
     * @param parent The parent window.
     */
    public GamePanel(StrixaWindow parent){
        super(parent);
        
        this._initializeGUI();
    }
    /*End Constructors*/
    
    /*Begin Overridden Methods*/
    @Override public void onPanelClose(){
        this.__canvas.triggerExiting();
    }
    /*End Override Methods*/
}
