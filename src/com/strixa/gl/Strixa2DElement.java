/**
 * File:  Strixa2DElement.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.gl;

import java.awt.Color;
import javax.media.opengl.GL2;

import com.strixa.gl.properties.Cuboid.Mask;
import com.strixa.util.Dimension2D;
import com.strixa.util.Point2D;


/**
 * Creates an object to be displayed on a 2D plane.
 *
 * @author Nicholas Rogé
 */
public abstract class Strixa2DElement extends StrixaGLElement{
    /**
     * Primarily used for {@link Strixa2DElement#getRelativeLocation(Strixa2DElement)} and {@link Strixa2DElement#getRelativeLocation(Point2D)}; describes an object's position relative to this one.
     *
     * @author Nicholas Rogé
     */
    public enum Location{
        /** If the point given is inside of this object. */
        INSIDE,
        /** If the point is above, but not to either side of this object. */
        NORTH,
        /** If the point is below, but not to either side of this object. */
        SOUTH,
        /** If the point is to the right, but not above of below this object. */
        EAST,
        /** If the point is to the left, but not above or below this object. */ 
        WEST,
        /** If the point is both above, and to the right of this object. */
        NORTHEAST,
        /** If the point is both above, and to the left of this object. */
        NORTHWEST,
        /** If the point is both below, and to the right of this object. */
        SOUTHEAST,
        /** If the point is both below, and to the left of this object. */
        SOUTHWEST
    }
    
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
    
    /**
     * Returns the location of the given element (by it's coordinate) relative to this element.
     * 
     * @param element Element whose relativeness is in question.
     * 
     * @return Returns one of {@link #Location}'s enumerations.  See Location's documentation for further information.
     */
    public Location getRelativeLocation(Strixa2DElement element){
        return this.getRelativeLocation(element.getCoordinates());
    }
    
    /**
     * Returns the location of the given point relative to this element.
     * 
     * @param point Point whose relativeness is in question.
     * 
     * @return Returns one of {@link #Location}'s enumerations.  See Location's documentation for further information.
     */
    public Location getRelativeLocation(Point2D<Double> point){
        if(this.isPointInside(point)){
            return Location.INSIDE;
        }
        
        final Point2D<Double> this_coordinates = this.getCoordinates();
        final double half_height = this.getDimensions().getHeight()/2;
        final double half_width = this.getDimensions().getWidth()/2;
        final double max_y = this_coordinates.getY()+(half_height);
        final double min_y = this_coordinates.getY()-(half_height);
        final double max_x = this_coordinates.getX()+(half_width);
        final double min_x = this_coordinates.getX()-(half_width);
        
        
        if(point.getX() < min_x){
            if(point.getY() > max_y){
                return Location.NORTHWEST;
            }else if(point.getY() < min_y){
                return Location.SOUTHWEST;
            }else{
                return Location.WEST;
            }
        }else if(point.getX() > max_x){
            if(point.getY() > max_y){
                return Location.NORTHEAST;
            }else if(point.getY() < min_y){
                return Location.SOUTHEAST;
            }else{
                return Location.EAST;
            }
        }else if(point.getY() > max_y){
            return Location.NORTH;
        }else{
            return Location.SOUTH;
        }
    }
    
    /**
     * Using the bounding box method, determines if the given element is colliding with this one.<br />
     * <strong>Note:</strong>  An element whose entire being is within this element is not considered to be colliding.
     * 
     * @param element Element who you're trying to detect if this object is colliding with.
     * 
     * @return Returns true if this object is colliding with the given object, and false, otherwise. 
     */
    public boolean isColliding(Strixa2DElement element){
        double element_top_edge = element.getCoordinates().getY()+element.getDimensions().getHeight();
        double element_bottom_edge = element.getCoordinates().getY();
        double element_right_edge = element.getCoordinates().getX()+element.getDimensions().getWidth();
        double element_left_edge = element.getCoordinates().getX();
        double this_top_edge = this.getCoordinates().getY()+this.getDimensions().getHeight();
        double this_bottom_edge = this.getCoordinates().getY();
        double this_right_edge = this.getCoordinates().getX()+this.getDimensions().getWidth();
        double this_left_edge = this.getCoordinates().getX();
        
        
        if(!this.isCollisionDetectionEnabled()){
            return false;
        }
        
        if(
            this_bottom_edge>element_bottom_edge
            &&
            this_top_edge<element_top_edge
            &&
            this_left_edge>element_left_edge
            &&
            this_right_edge<element_right_edge
        ){
            return false;
        }else if(
            this_bottom_edge<element_top_edge
            ||
            this_top_edge>element_bottom_edge
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
    
    /**
     * Boolean check to see if given point is inside this element.
     * 
     * @param point Point to check.
     * 
     * @return Returns true if the given point is in this element's boundaries, and false, otherwise.
     */
    public boolean isPointInside(Point2D<Double> point){
        final Point2D<Double> this_coordinates = this.getCoordinates();
        final double half_height = this.getDimensions().getHeight()/2;
        final double half_width = this.getDimensions().getWidth()/2;
        final double max_y = this_coordinates.getY()+(half_height);
        final double min_y = this_coordinates.getY()-(half_height);
        final double max_x = this_coordinates.getX()+(half_width);
        final double min_x = this_coordinates.getX()-(half_width);
        
        
        if(
            point.getX() < min_x
            ||
            point.getX() > max_x
            ||
            point.getY() < min_y
            ||
            point.getY() > max_y
        ){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean isVisible(StrixaGLContext context){
        final Point2D<Double> bottom_left_vertex = context.getViewableArea().getPoint(Mask.FRONT,Mask.BOTTOM,Mask.LEFT);
        final Point2D<Double> coordinates = this.getCoordinates();
        final Dimension2D<Double> dimensions = this.getDimensions();
        final Point2D<Double> top_right_vertex = context.getViewableArea().getPoint(Mask.FRONT,Mask.TOP,Mask.RIGHT);
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
