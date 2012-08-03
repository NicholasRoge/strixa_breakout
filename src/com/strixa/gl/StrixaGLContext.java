/**
 * File:  StrixaGLContext.java
 * Date of Creation:  Jul 19, 2012
 */
package com.strixa.gl;

import com.strixa.gl.properties.Cuboid;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class StrixaGLContext{
    private int    __current_fps;
    private Cuboid __viewable_area;
    
    
    public Cuboid getViewableArea(){
        if(this.__viewable_area == null){
            this.__viewable_area = new Cuboid();
        }
        
        return this.__viewable_area;
    }
    
    public int getCurrentFPS(){
        return this.__current_fps;
    }
    
    public void setCurrentFPS(int fps){
        this.__current_fps = fps;
    }
}
