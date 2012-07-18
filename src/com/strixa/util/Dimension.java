/**
 * File:  Dimension.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.util;

/**
 * Generic dimension class which allows for any type of numeric to be used.  Use of non-numeric type is discouraged  
 *
 * @author Nicholas Rogé
 */
public class Dimension<T>{
    private T __height;
    private T __width;
    
    
    /*Begin Constructors*/
    public Dimension(T width,T height){
        this.__width = width;
        this.__height = height;
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    public T getHeight(){
        return this.__height;
    }
    
    public T getWidth(){
        return this.__width;
    }
    
    public void setDimensions(T width,T height){
        this.__width = width;
        this.__height = height;
    }
    
    public void setHeight(T height){
        this.__height = height;
    }
    
    public void setWidth(T width){
        this.__width = width;
    }
    /*End Getter/Setter Methods*/
}
