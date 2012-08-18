/**
 * File:  Circle.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.gl.shapes;

import java.util.ArrayList;
import java.util.List;

import com.strixa.gl.StrixaPolygon;
import com.strixa.util.Dimension2D;
import com.strixa.util.Point2D;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class Circle extends StrixaPolygon{
    private Dimension2D<Double> __dimensions;
    private int               __fan_count;
    private double            __radius;
    
    
    /*Begin Constructors*/
    /**
     * Constructs a circle with a given radius.
     * 
     * @param radius Radius the circle should take on.
     * @param fan_count This number defines the number of lines on the outside parameter of the circle.
     */
    public Circle(double radius,int fan_count){        
        this.__radius = radius;
        this.__fan_count = fan_count;
        
        this._updateProfile();
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    public Dimension2D<Double> getDimensions(){
        if(this.__dimensions == null){
            this.__dimensions = new Dimension2D<Double>(this.getRadius()*2,this.getRadius()*2);
        }
        
        return this.__dimensions;
    }
    
    /**
     * Gets the fineness of the cicle's edge.
     * 
     * @return The fineness of the circle's edge.
     */
    public int getFanCount(){
        return this.__fan_count;
    }
    
    /**
     * Sets the fineness of the circle's edge.
     * 
     * @param fan_count This number defines the number of lines on the outside parameter of the circle.
     */
    public void setFanCount(int fan_count){
        this.__fan_count = fan_count;
        
        this._updateProfile();
    }
    
    /**
     * Gets this circle's radius.
     * 
     * @return This circle's radius.
     */
    public double getRadius(){
        return this.__radius;
    }
    
    /**
     * Changes this circle's radius.
     * 
     * @param radius New radius this circle should take on.
     */
    public void setRadius(double radius){       
        this.__radius = radius;
        
        this._updateProfile();
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    protected Point2D<Double> _getTriangleEndPoint(int point){
        final Point2D<Double> end_point = new Point2D<Double>((double)0,(double)0);
        final double        radius = this.getRadius();
        
        double degrees = 0;
        double radians = 0;
        
        
        degrees = (point/(double)this.__fan_count)*360;
        radians = (degrees*Math.PI)/180;
        
        end_point.setPoint(Math.sin(radians)*radius,Math.cos(radians)*radius);
        
        return end_point;
    }
    
    protected void _updateProfile(){
        final Dimension2D<Double> dimensions = this.getDimensions();
        final List<Point2D<Double>> points = new ArrayList<Point2D<Double>>();
        
        
        /*Update the circles points*/
        for(int point = 0;point < this.__fan_count;point++){
            points.add(this._getTriangleEndPoint(point));
        }
        
        this._setPoints(points);
        
        /*Update the Dimensions*/
        dimensions.setWidth(this.getRadius()*2);
        dimensions.setHeight(this.getRadius()*2);
    }
    /*End Other Methods*/
}