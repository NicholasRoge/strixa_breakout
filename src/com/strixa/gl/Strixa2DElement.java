/**
 * File:  Strixa2DElement.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.gl;

import java.awt.Color;

import javax.media.opengl.GL2;

import com.strixa.gl.properties.Cuboid.PointMask;
import com.strixa.util.Dimension2D;
import com.strixa.util.Point2D;


/**
 * Creates an object to be displayed on a 2D plane.
 *
 * @author Nicholas Rogé
 */
public abstract class Strixa2DElement extends StrixaGLElement{
    private double __alpha;
    private Color  __colour;
    private Point2D<Double>  __coordinates;
    
    
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
            this.__colour = new Color(1f,1f,1f);
        }
        
        return this.__colour;
    }
    
    /**
     * Gets this element's x/y coordinates in the form of a Point object.
     * 
     * @return This element's x/y coordinates in the form of a Point object.
     */
    public Point2D<Double> getCoordinates(){
        if(this.__coordinates == null){
            this.__coordinates = new Point2D<Double>(0.0,0.0);
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
        this.getCoordinates().setPoint(x,y);
    }
    
    /**
     * Sets this element's x coordinate.
     * 
     * @param x X coordinate this object should be moved to.
     */
    public void setCoordinateX(double x){
        this.getCoordinates().setX(x);
    }
    
    /**
     * Sets this element's y coordinate.
     * 
     * @param y Y coordinate this object should be moved to.
     */
    public void setCoordinateY(double y){
        this.getCoordinates().setY(y);
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    public void draw(GL2 gl){
        final Point2D<Double> coordinate = this.getCoordinates();
        final float[] colour_components = this.getColour().getColorComponents(new float[3]);
        
        gl.glColor4f(colour_components[0],colour_components[1],colour_components[2],(float)this.getAlpha());
        gl.glTranslated(coordinate.getX(),coordinate.getY(),0);
    }
    
    public boolean isColliding(Strixa2DElement element){
        double element_top_edge = element.getCoordinates().getY()+element.getDimensions().getHeight();
        double element_bottom_edge = element.getCoordinates().getY();
        double element_right_edge = element.getCoordinates().getX()+element.getDimensions().getWidth();
        double element_left_edge = element.getCoordinates().getX();
        double this_top_edge = this.getCoordinates().getY()+this.getDimensions().getHeight();
        double this_bottom_edge = this.getCoordinates().getY();
        double this_right_edge = this.getCoordinates().getX()+this.getDimensions().getWidth();
        double this_left_edge = this.getCoordinates().getX();
        
        
        
        
        if(
            this_top_edge>element_bottom_edge
            ||
            this_bottom_edge<element_top_edge
            ||
            this_left_edge<element_right_edge
            ||
            this_right_edge>element_left_edge
        ){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isVisible(StrixaGLContext context){
        final Point2D<Double> bottom_left_vertex = context.getViewableArea().getPoint(PointMask.FRONT,PointMask.BOTTOM,PointMask.LEFT);
        final Point2D<Double> coordinates = this.getCoordinates();
        final Dimension2D<Double> dimensions = this.getDimensions();
        final Point2D<Double> top_right_vertex = context.getViewableArea().getPoint(PointMask.FRONT,PointMask.TOP,PointMask.RIGHT);
        final Dimension2D<Double> viewable_area = new Dimension2D<Double>(0.0,0.0);
        
        
        viewable_area.setWidth(top_right_vertex.getX()-bottom_left_vertex.getX());
        viewable_area.setHeight(top_right_vertex.getY()-bottom_left_vertex.getY());
        
        if(
            (coordinates.getX()+dimensions.getWidth()) > bottom_left_vertex.getX() && coordinates.getX() < (bottom_left_vertex.getX()+viewable_area.getWidth())
            &&
            (coordinates.getY()+dimensions.getHeight()) > bottom_left_vertex.getY() && coordinates.getY() < (bottom_left_vertex.getY()+viewable_area.getHeight())
        ){
            return true;
        }else{
            return false;
        }
    }
    /*End Other Methods*/
    
    /*Begin Abstract Methods*/
    public abstract Dimension2D<Double> getDimensions();
    /*End Abstract Methods*/
}
