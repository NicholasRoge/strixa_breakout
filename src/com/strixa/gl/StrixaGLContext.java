/**
 * File:  StrixaGLContext.java
 * Date of Creation:  Jul 19, 2012
 */
package com.strixa.gl;

import com.strixa.gl.properties.Cuboid;

/**
 * Describes the current context of a running StrixaGL application.
 *
 * @author Nicholas Rogé
 */
public class StrixaGLContext{
    private int    __current_fps;
    private Cuboid __viewable_area;
    
    
    /**
     * Returns a {@link Cuboid} which represents the maximum viewable area.
     * 
     * @return A {@link Cuboid} which represents the maximum viewable area.
     */
    public Cuboid getViewableArea(){
        if(this.__viewable_area == null){
            this.__viewable_area = new Cuboid();
        }
        
        return this.__viewable_area;
    }
    
    /**
     * Gets the current maximum framerate.
     * 
     * @return The current maximum framerate.
     */
    public int getCurrentFPS(){
        return this.__current_fps;
    }
    
    /**
     * Sets the current maximum framerate.
     * 
     * @param fps The maximum framerate to allow in this application.
     */
    public void setCurrentFPS(int fps){
        this.__current_fps = fps;
    }
}
