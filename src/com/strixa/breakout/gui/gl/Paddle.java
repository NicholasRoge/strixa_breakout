/**
 * File:  Paddle.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.breakout.gui.gl;

import java.awt.Dimension;

import javax.media.opengl.GL2;

import com.strixa.gl.Strixa2DElement;

/**
 * A paddle whose handle is in the upper left corner.
 *
 * @author Nicholas Rogé
 */
public class Paddle extends Strixa2DElement{
    private Dimension __dimensions;
    
    
    /*Begin Constructors*/
    /**
     * Constructs the paddle with the given dimensions. 
     * 
     * @param paddle_width Width of the paddle.
     * @param paddle_height Height of the paddle.
     */
    public Paddle(double paddle_width,double paddle_height){
        this.setDimensions(paddle_width,paddle_height);
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets the dimensions of the paddle.
     * 
     * @return The dimensions of the paddle.
     */
    public Dimension getDimensions(){
        if(this.__dimensions == null){
            this.__dimensions = new Dimension();  //We just made a new dimension...?  (*  ).(  *)  *derp*
        }
        
        return this.__dimensions;
    }
    
    /**
     * Sets the dimensions of the paddle.
     * 
     * @param width Width of the paddle.
     * @param height Height of the paddle.
     */
    public void setDimensions(double width,double height){
        this.getDimensions().setSize(width,height);
    }
    
    /**
     * Sets the height of the paddle.
     * 
     * @param height Height of the paddle.
     */
    public void setHeight(double height){
        this.setDimensions(this.getDimensions().getHeight(),height);
    }
    
    /**
     * Sets the width of the paddle.
     * 
     * @param width Width of the paddle.
     */
    public void setWidth(double width){
        this.setDimensions(width,this.getDimensions().getHeight());
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    public void draw(GL2 gl){
        final Dimension dimensions = this.getDimensions();
        
        
        super.draw(gl);
        
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3d(0,0,0);
            gl.glVertex3d(dimensions.getWidth(),0,0);
            gl.glVertex3d(dimensions.getWidth(),dimensions.getHeight(),0);
            gl.glVertex3d(0,dimensions.getHeight(),0);
        gl.glEnd();
    }
    /*End Other Methods*/
}
