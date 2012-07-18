/**
 * File:  Paddle.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.breakout.gui.gl;

import java.awt.Dimension;

import javax.media.opengl.GL2;

import com.strixa.gl.Strixa2DElement;
import com.strixa.gl.shapes.Rectangle;

/**
 * A paddle whose handle is in the upper left corner.
 *
 * @author Nicholas Rogé
 */
public class Paddle extends Rectangle{
    /*Begin Constructors*/
    /**
     * Constructs the paddle with the given dimensions. 
     * 
     * @param paddle_width Width of the paddle.
     * @param paddle_height Height of the paddle.
     */
    public Paddle(double width,double height){
        super(width,height);
    }
    /*End Constructors*/
}
