/**
 * File:  StrixaGLElement.java
 * Date of Creation:  Jul 16, 2012
 */
package com.strixa.gl;

import javax.media.opengl.GL2;

import com.strixa.util.Dimension;
import com.strixa.util.Dimension2D;
import com.strixa.util.Point2D;


/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public abstract class StrixaGLElement{
    /**
     * Called when the StrixaGLElement can be initialized
     */
    public void init(){
    }
    
    /**
     * Draws the StixaGLElement to the screen.
     * 
     * @param gl Object which everything should be drawn to.
     */
    public abstract void draw(GL2 gl);
    
    /**
     * Describes the visibility of an object.  
     * 
     * @param context Contains the current environment for this Element.
     * 
     * @return Returns true if this object is visible, and false, otherwise.
     */
    public abstract boolean isVisible(StrixaGLContext context);
    
    /**
     * Gets this object's dimensions.
     * 
     * @return This object's dimensions.
     */
    public abstract Dimension<Double> getDimensions();
}
