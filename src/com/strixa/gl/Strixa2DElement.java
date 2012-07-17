/**
 * File:  Strixa2DElement.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.gl;

import java.awt.Color;
import java.awt.Point;

import javax.media.opengl.GL2;


/**
 * Creates an object to be displayed on a 2D plane.
 *
 * @author Nicholas Rogé
 */
public abstract class Strixa2DElement implements StrixaGLElement{
    private double __alpha;
    private Color  __colour;
    private Point  __coordinates;
    
    
    /*Begin Constructor*/
    /**
     * Constructs a basic Strixa2DElement.
     */
    public Strixa2DElement(){
        this.setCoordinates(0,0);
        this.setAlpha(1);
    }
    /*End Constructor*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets the alpha for this element.
     * 
     * @return The alpha for this element.
     */
    public double getAlpha(){
        return this.__alpha;
    }
    
    /**
     * Gets the colour this element is set to.
     * 
     * @return The colour this element is set to.
     */
    public Color getColour(){
        if(this.__colour == null){
            this.__colour = new Color(1,1,1);
        }
        
        return this.__colour;
    }
    
    /**
     * Gets this element's x/y coordinates in the form of a Point object.
     * 
     * @return This element's x/y coordinates in the form of a Point object.
     */
    public Point getCoordinates(){
        if(this.__coordinates == null){
            this.__coordinates = new Point();
        }
        
        return this.__coordinates;
    }
    
    /**
     * Sets this element's alpha.
     * 
     * @param alpha Alpha transparency this element should take on.  This should be a value between 0 and 1.
     */
    public void setAlpha(double alpha){
        if(alpha<0 || alpha>1){
            throw new IllegalArgumentException("Value for argument 'alpha' must be no less than 0 and no greater than 1.");
        }
        
        this.__alpha = alpha;
    }
    
    /**
     * Sets this element's colour.
     * 
     * @param red Red component of this element's colour.  This should be a value between 0 and 1.
     * @param green Red component of this element's colour.  This should be a value between 0 and 1.
     * @param blue Red component of this element's colour.  This should be a value between 0 and 1.
     */
    public void setColour(float red,float green,float blue){
        if(red<0 || red>1){
            throw new IllegalArgumentException("Value for argument 'red' must be no less than 0 and no greater than 1.");
        }else if(green<0 || green>1){
            throw new IllegalArgumentException("Value for argument 'green' must be no less than 0 and no greater than 1.");
        }else if(blue<0 || blue>1){
            throw new IllegalArgumentException("Value for argument 'blue' must be no less than 0 and no greater than 1.");
        }
        
        this.__colour = new Color(red,green,blue);
    }
    
    /**
     * Sets this element's x/y coordinates.
     * 
     * @param x X coordinate this object should be moved to.
     * @param y Y coordinate this object should be moved to.
     */
    public void setCoordinates(double x,double y){
        this.getCoordinates().setLocation(x,y);
    }
    
    /**
     * Sets this element's x coordinate.
     * 
     * @param x X coordinate this object should be moved to.
     */
    public void setCoordinateX(double x){
        this.getCoordinates().setLocation(x,this.getCoordinates().getY());
    }
    
    /**
     * Sets this element's y coordinate.
     * 
     * @param y Y coordinate this object should be moved to.
     */
    public void setCoordinateY(double y){
        this.getCoordinates().setLocation(this.getCoordinates().getX(),y);
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    public void draw(GL2 gl){
        final Point coordinate = this.__coordinates;
        final float[] colour_components = this.getColour().getColorComponents(new float[3]);
        
        
        gl.glColor4f(colour_components[0],colour_components[1],colour_components[2],(float)this.getAlpha());
        gl.glTranslated(coordinate.getX(),coordinate.getY(),0);
    }
    /*End Other Methods*/
}
