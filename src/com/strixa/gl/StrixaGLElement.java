/**
 * File:  StrixaGLElement.java
 * Date of Creation:  Jul 16, 2012
 */
package com.strixa.gl;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;


/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public interface StrixaGLElement{
    /**
     * Draws the StixaGLElement to the screen.
     * 
     * @param gl Object which everything should be drawn to.
     */
    void draw(GL2 gl);
}
