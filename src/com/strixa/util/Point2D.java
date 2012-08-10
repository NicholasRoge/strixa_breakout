/**
 * File:  Point.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.util;

/**
 * Generic point class which allows for any type of numeric to be used.  Use of non-numeric type is discouraged  
 *
 * @author Nicholas Rogé
 */
public class Point2D<T extends Number> extends Point<T>{
    private T __x;
    private T __y;
    
    
    /*Begin Constructors*/
    public Point2D(T x,T y){
        this.setPoint(x,y);
    }
    
    public Point2D(Point2D<T> copy){
        this.setPoint(copy.getX(),copy.getY());
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    public T getX(){
        return this.__x;
    }
    
    public T getY(){
        return this.__y;
    }
    
    public void setPoint(T x,T y){
        this.__x = x;
        this.__y = y;
    }
    
    public void setX(T x){
        this.__x = x;
    }
    
    public void setY(T y){
        this.__y = y;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
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
