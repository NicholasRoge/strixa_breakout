/**
 * File:  Point3D.java
 * Date of Creation:  Jul 23, 2012
 */
package com.strixa.util;

/**
 * Generic point class which allows for any type of numeric to be used.
 *
 * @author Nicholas Rogé
 */
public class Point3D <T extends Number> extends Point2D<T>{
    private T __z;
    
    /*Begin Constructors*/
    /**
     * Constructs a Point3D object whose coordinates are at the given points.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate
     */
    public Point3D(T x,T y,T z){
        super(x,y);
        
        this.__z = z;
    }
    
    /**
     * Constructs a copy of the given Point2D object.
     * 
     * @param copy Point2D object whose data should be copied.
     */
    public Point3D(Point3D<T> copy){
        super(copy.getX(),copy.getY());
        
        this.__z = copy.getZ();
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets this point's Z coordinate.
     * 
     * @return This point's Z coordinate.
     */
    public T getZ(){
        return this.__z;
    }
    
    /**
     * Sets this point's Z coordinate.
     * 
     * @param z Z coordinate.
     */
    public void setZ(T z){
        this.__z = z;
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
    public boolean equals(Point3D<T> point){
        if(this.getX()==point.getX() && this.getY()==point.getY() && this.getZ() == point.getZ()){
            return true;
        }
        
        return false;
    }
    
    public String toString(){
        return "("+this.getX()+","+this.getY()+","+this.getZ()+")";
    }
    /*End Other Methods*/
}
