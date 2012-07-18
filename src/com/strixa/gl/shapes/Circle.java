/**
 * File:  Circle.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.gl.shapes;

import javax.media.opengl.GL2;

import com.strixa.gl.Strixa2DElement;
import com.strixa.util.Point;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class Circle extends Strixa2DElement{
    private int    __fan_count;
    private double __radius;
    
    
    /*Begin Constructors*/
    public Circle(double radius,int fan_count){
        this.__radius = radius;
        this.__fan_count = fan_count;
    }
    /*End Constructors*/
    
    /*Begin Overridden Methods*/
    @Override public void draw(GL2 gl){
        Point<Double> end_point = null;
        
        
        super.draw(gl);
        
        for(int i=0;i<this.__fan_count;i++){            
            gl.glBegin(GL2.GL_TRIANGLES);
                gl.glVertex3d(0,0,0);
                
                end_point = this._getTriangleEndPoint(i);
                gl.glVertex3d(end_point.getX(),end_point.getY(),0);
                
                if(i == this.__fan_count-1){
                    end_point = this._getTriangleEndPoint(0);
                }else{
                    end_point = this._getTriangleEndPoint(i+1);
                }
                gl.glVertex3d((Double)end_point.getX(),(Double)end_point.getY(),0);
            gl.glEnd();
            
            gl.glRotated((i/this.__fan_count)*360,0,0,1);
        }
    }
    /*End Overridden Methods*/
    
    /*Begin Other Methods*/
    protected Point<Double> _getTriangleEndPoint(int point){
        final Point<Double> end_point = new Point<Double>((double)0,(double)0);
        
        double degrees = 0;
        double radians = 0;
        
        
        degrees = (point/(double)this.__fan_count)*360;
        radians = (degrees*Math.PI)/180;
        
        end_point.setPoint(Math.sin(radians)*this.__radius,Math.cos(radians)*this.__radius);
        
        return end_point;
    }
    /*End Other Methods*/
}

/*
With n number of triangles:
3 - 
*/