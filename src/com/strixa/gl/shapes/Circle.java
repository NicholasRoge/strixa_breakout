/**
 * File:  Circle.java
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
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class Circle extends StrixaPolygon{
    private Dimension2D<Double> __dimensions;
    private int               __fan_count;
    private double            __radius;
    
    
    /*Begin Constructors*/
    public Circle(double radius,int fan_count){
        final List<Point2D<Double>> points = new ArrayList<Point2D<Double>>();
        
        
        this.__radius = radius;
        this.__fan_count = fan_count;
        
        /*Add polygon points*/
        for(int point = 0;point < this.__fan_count;point++){
            points.add(this._getTriangleEndPoint(point));
        }
        
        this._setPoints(points);
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    public Dimension2D<Double> getDimensions(){
        if(this.__dimensions == null){
            this.__dimensions = new Dimension2D<Double>(this.getRadius()*2,this.getRadius()*2);
        }
        
        return this.__dimensions;
    }
    
    public double getRadius(){
        return this.__radius;
    }
    
    public void setRadius(double radius){
        final Dimension2D<Double> dimensions = this.getDimensions();
        
        
        this.__radius = radius;
        dimensions.setWidth(radius*2);
        dimensions.setHeight(radius*2);
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
    /*End Other Methods*/
}