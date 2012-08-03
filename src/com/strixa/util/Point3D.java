/**
 * File:  Point3D.java
 * Date of Creation:  Jul 23, 2012
 */
package com.strixa.util;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class Point3D <T extends Number> extends Point2D<T>{
    public T __z;
    
    /*Begin Constructors*/
    public Point3D(T x,T y,T z){
        super(x,y);
        
        this.__z = z;
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    public T getZ(){
        return this.__z;
    }
    
    public void setZ(T z){
        this.__z = z;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
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
