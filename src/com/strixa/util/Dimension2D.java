/**
 * File:  Dimension.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.util;

/**
 * Describes an objects dimensions with the given numeric type using two dimensions:  width and height.  
 *
 * @author Nicholas Rogé
 */
public class Dimension2D<T extends Number> extends Dimension<T>{
    private T __height;
    private T __width;
    
    
    /*Begin Constructors*/    
    /**
     * Constructs a Dimension2D object using the given parameters as its width and height.
     * 
     * @param width Width of this Dimension.
     * @param height Height of this Dimension.
     */
    public Dimension2D(T width,T height){
        this.__width = width;
        this.__height = height;
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets this Dimension's height.
     * 
     * @return This Dimension's height.
     */
    public T getHeight(){
        return this.__height;
    }
    
    /**
     * Gets this Dimension's width.
     * 
     * @return This Dimension's width.
     */
    public T getWidth(){
        return this.__width;
    }
    
    /**
     * Sets this Dimension's width and height.
     * 
     * @param width New width this Dimension should take on.
     * @param height New height this Dimensions should take on.
     */
    public void setDimensions(T width,T height){
        this.__width = width;
        this.__height = height;
    }
    
    /**
     * Sets this Dimension's height.
     * 
     * @param height New height this Dimensions should take on.
     */
    public void setHeight(T height){
        this.__height = height;
    }
    
    /**
     * Sets this Dimension's width.
     * 
     * @param width New width this Dimension should take on.
     */
    public void setWidth(T width){
        this.__width = width;
    }
    /*End Getter/Setter Methods*/
}
