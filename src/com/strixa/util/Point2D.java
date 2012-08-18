/**
 * File:  Point.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.util;

/**
 * Generic point class which allows for any type of numeric to be used.  
 *
 * @author Nicholas Rogé
 */
public class Point2D<T extends Number> extends Point<T>{
    private T __x;
    private T __y;
    
    
    /*Begin Constructors*/
    /**
     * Constructs a Point2D object whose coordinates are at the given points.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public Point2D(T x,T y){
        this.setPoint(x,y);
    }
    
    /**
     * Constructs a copy of the given Point2D object.
     * 
     * @param copy Point2D object whose data should be copied.
     */
    public Point2D(Point2D<T> copy){
        this.setPoint(copy.getX(),copy.getY());
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets this point's X coordinate.
     * 
     * @return This point's X coordinate.
     */
    public T getX(){
        return this.__x;
    }
    
    /**
     * Gets this point's X coordinate.
     * 
     * @return This point's X coordinate.
     */
    public T getY(){
        return this.__y;
    }
    
    /**
     * Sets this point's coordinates.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public void setPoint(T x,T y){
        this.__x = x;
        this.__y = y;
    }
    
    /**
     * Sets this point's X coordinate.
     * 
     * @param x X coordinate.
     */
    public void setX(T x){
        this.__x = x;
    }
    
    /**
     * Sets this point's Y Coordinate.
     * 
     * @param y Y coordinate.
     */
    public void setY(T y){
        this.__y = y;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    /**
     * Performs a check to see if the given point is at the same location as this one.
     * 
     * @param point Point to compare against.
     * 
     * @return Returns true if the given point is at the same location as this one, and false, otherwise.
     */
    public boolean equals(Point2D<T> point){
        if(this.getX()==point.getX() && this.getY()==point.getY()){
            return true;
        }
        
        return false;
    }
    
    public String toString(){
        return "("+this.getX()+","+this.getY()+")";
    }
    /*End Other Methods*/
}
