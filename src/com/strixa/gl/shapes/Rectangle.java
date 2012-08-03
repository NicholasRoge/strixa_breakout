/**
 * File:  Rectangle.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.gl.shapes;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

import com.strixa.gl.Strixa2DElement;
import com.strixa.gl.StrixaPolygon;
import com.strixa.util.Dimension2D;
import com.strixa.util.Point2D;

/**
 * A rectangle whose handle is in the top left corner.
 *
 * @author Nicholas Rogé
 */
public class Rectangle extends StrixaPolygon{
    private Dimension2D<Double> __dimensions;
    
    
    /*Begin Constructors*/
    /**
     * Constructs the rectangle with the given dimensions. 
     * 
     * @param rectangle_width Width of the rectangle.
     * @param rectangle_height Height of the rectangle.
     */
    public Rectangle(double rectangle_width,double rectangle_height){
        final List<Point2D<Double>> points = new ArrayList<Point2D<Double>>();
        
        
        this.setDimensions(rectangle_width,rectangle_height);
        
        
        /*Add points to the polygon*/
        points.add(new Point2D<Double>(-(rectangle_width/2),(rectangle_height/2)));
        points.add(new Point2D<Double>((rectangle_width/2),(rectangle_height/2)));
        points.add(new Point2D<Double>((rectangle_width/2),-(rectangle_height/2)));
        points.add(new Point2D<Double>(-(rectangle_width/2),-(rectangle_height/2)));
        
        this._setPoints(points);
        
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    public Dimension2D<Double> getDimensions(){
        if(this.__dimensions == null){
            this.__dimensions = new Dimension2D<Double>(0.0,0.0);  //We just made a new dimension...?  (*  ).(  *)  *derp*
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
    /*End Getter/Setter Methods*/}
