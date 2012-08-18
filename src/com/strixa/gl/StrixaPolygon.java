/**
 * File:  StrixaPolygon.java
 * Date of Creation:  Jul 29, 2012
 */
package com.strixa.gl;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;
import com.strixa.util.Line;
import com.strixa.util.Point2D;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public abstract class StrixaPolygon extends Strixa2DElement{
    final private List<Point2D<Double>> __points = new ArrayList<Point2D<Double>>();
    
    
    /*Begin Constructors*/
    /**
     * Constructs the polygon
     */
    public StrixaPolygon(){
        super();
    }
    /*End Constructors*/
    
    /*Begin Other Methods*/
    @Override public void draw(GL2 gl){
        super.draw(gl);
        
        gl.glBegin(GL2.GL_POLYGON);
            for(Point2D<Double> point : this.__points){
                gl.glVertex3d(point.getX(),point.getY(),0);
            }
        gl.glEnd();
    }
    
    public void init(){
        super.init();
        
        this._setPoints(this.__points);
    }
    /*End Other Methods*/
    
    /*Begin Abstract Methods*/
    /**
     * Sets the list of points for this polygon to draw.
     * 
     * @param points The list of points for this polygon to draw.
     */
    protected void _setPoints(List<Point2D<Double>> points){
        this.__points.clear();
        this.__points.addAll(points);
    }
    /*End Abstract Methods*/
    
    /*Begin Static Methods*/
    /**
     * By checking to see if any of this polygon's lines are intersecting with the second polygon's lines, this method determines if the given element is colliding with this one.<br />
     * <strong>Note:</strong>  An element whose entire being is within this element is not considered to be colliding.
     * 
     * @param element Element who you're trying to detect if this object is colliding with.
     * 
     * @return Returns true if this object is colliding with the given object, and false, otherwise. 
     */
    public boolean isColliding(StrixaPolygon element){  //TODO_HIGH:  This method needs heavy optimization.  Rather than creating a bunch of new objects, a list could be crated, for example.
        final int this_point_count = this.__points.size();
        final int element_point_count = element.__points.size();
        
        Point2D<Double> adjusted_point_one = null;
        Point2D<Double> adjusted_point_two = null;
        Line polygon_one_line = null;
        Line polygon_two_line = null;
        
        
        if(!this.isCollisionDetectionEnabled()){
            return false;
        }
        
        if(!super.isColliding(element)){  //If it's not colliding using the bounding box method, there's no reason to continue and use a more expensive collision detection algorithm.
            return false;
        }
        
        for(int index=0;index<this_point_count;index++){
            /*Set up the first point*/
            if(index==0){
                adjusted_point_one = new Point2D<Double>(this.__points.get(this_point_count-1));
            }else{
                adjusted_point_one = new Point2D<Double>(this.__points.get(index-1));
                
            }
            adjusted_point_one.setX(adjusted_point_one.getX()+this.getCoordinates().getX());
            adjusted_point_one.setY(adjusted_point_one.getY()+this.getCoordinates().getY());
            
            /*Set up the second point*/
            adjusted_point_two = new Point2D<Double>(this.__points.get(index));
            
            adjusted_point_two.setX(adjusted_point_two.getX()+this.getCoordinates().getX());
            adjusted_point_two.setY(adjusted_point_two.getY()+this.getCoordinates().getY());
            
            /*Create teh first line*/
            polygon_one_line = new Line(adjusted_point_one,adjusted_point_two);
            
            for(int sub_index=0;sub_index<element_point_count;sub_index++){
                if(sub_index==0){
                    adjusted_point_one = new Point2D<Double>(element.__points.get(element_point_count-1));
                }else{
                    adjusted_point_one = new Point2D<Double>(element.__points.get(sub_index-1));
                }
                adjusted_point_one.setX(adjusted_point_one.getX()+element.getCoordinates().getX());
                adjusted_point_one.setY(adjusted_point_one.getY()+element.getCoordinates().getY());
                
                adjusted_point_two = new Point2D<Double>(element.__points.get(sub_index));
                    adjusted_point_two.setX(adjusted_point_two.getX()+element.getCoordinates().getX());
                    adjusted_point_two.setY(adjusted_point_two.getY()+element.getCoordinates().getY());
                
                polygon_two_line = new Line(adjusted_point_one,adjusted_point_two);
                
                if(Line.getIntersectionPoint(polygon_one_line,polygon_two_line)!=null){
                    return true;
                }
            }
        }
        
        return false;
    }
    /*End Static Methods*/
}
