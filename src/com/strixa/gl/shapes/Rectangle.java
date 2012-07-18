/**
 * File:  Rectangle.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.gl.shapes;

import com.strixa.util.Dimension;

import javax.media.opengl.GL2;

import com.strixa.gl.Strixa2DElement;

/**
 * A rectangle whose handle is in the top left corner.
 *
 * @author Nicholas Rogé
 */
public class Rectangle extends Strixa2DElement{
    private Dimension<Double> __dimensions;
    
    
    /*Begin Constructors*/
    /**
     * Constructs the rectangle with the given dimensions. 
     * 
     * @param rectangle_width Width of the rectangle.
     * @param rectangle_height Height of the rectangle.
     */
    public Rectangle(double rectangle_width,double rectangle_height){
        this.setDimensions(rectangle_width,rectangle_height);
    }
    /*End Constructors*/
    
    /*Begin Overridden Methods*/
    @Override public void draw(GL2 gl){
        final Dimension dimensions = this.getDimensions();
        
        
        super.draw(gl);
        
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3d(0,0,0);
            gl.glVertex3d((Double)dimensions.getWidth(),0,0);
            gl.glVertex3d((Double)dimensions.getWidth(),(Double)dimensions.getHeight(),0);
            gl.glVertex3d(0,(Double)dimensions.getHeight(),0);
        gl.glEnd();
    }
    /*End Overridden Methods*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets the dimensions of the rectangle.
     * 
     * @return The dimensions of the rectangle.
     */
    public Dimension getDimensions(){
        if(this.__dimensions == null){
            this.__dimensions = new Dimension(0,0);  //We just made a new dimension...?  (*  ).(  *)  *derp*
        }
        
        return this.__dimensions;
    }
    
    /**
     * Sets the dimensions of the rectangle.
     * 
     * @param width Width of the rectangle.
     * @param height Height of the rectangle.
     */
    public void setDimensions(double width,double height){
        this.getDimensions().setDimensions(width,height);
    }
    
    /**
     * Sets the height of the rectangle.
     * 
     * @param height Height of the rectangle.
     */
    public void setHeight(double height){
        this.getDimensions().setHeight(this.getDimensions().getHeight());
    }
    
    /**
     * Sets the width of the rectangle.
     * 
     * @param width Width of the rectangle.
     */
    public void setWidth(double width){
        this.getDimensions().setWidth(width);
    }
    /*End Getter/Setter Methods*/
}
