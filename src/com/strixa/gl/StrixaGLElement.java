/**
 * File:  StrixaGLElement.java
 * Date of Creation:  Jul 16, 2012
 */
package com.strixa.gl;

import javax.media.opengl.GL2;

import com.strixa.util.Dimension;
import com.strixa.util.Point;


/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public abstract class StrixaGLElement{
    private boolean __collision_detection_enabled;
    
    
    /*Begin Constructors*/
    /**
     * Constructs the Element
     **/
    public StrixaGLElement(){
        this.__collision_detection_enabled = true;
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Boolean check to determine whether or not collision detection is enabled for this object.
     * 
     * @return Returns true if collision detection is enabled, and false otherwise.
     */
    public boolean isCollisionDetectionEnabled(){
        return this.__collision_detection_enabled;
    }
    
    /**
     * Sets the collision detection allowance of this object.
     * 
     * @param enabled This should be true if this object should be able to collide with other objects, and false, otherwise.
     */
    public void setCollisionDetectionEnabled(boolean enabled){
        this.__collision_detection_enabled = enabled;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Abstract Methods*/
    /**
     * Called when the StrixaGLElement can be initialized
     */
    public void init(){}
    
    /**
     * Check to see if this object is colliding with the given object.<br />
     * <strong>Note:  It is highly encouraged that you implement the collision detection enabled status of this object into your definition.</strong>
     * 
     * @param element Element to check against.
     * 
     * @return Returns if the two elements are colliding.
     */
    public boolean isColliding(StrixaGLElement element){return false;}
    
    /**
     * Draws the StixaGLElement to the screen.
     * 
     * @param gl Object which everything should be drawn to.
     */
    public abstract void draw(GL2 gl);
    
    /**
     * Describes the visibility of an object.  
     * 
     * @param context Contains the current environment for this Element.
     * 
     * @return Returns true if this object is visible, and false, otherwise.
     */
    public abstract boolean isVisible(StrixaGLContext context);
    
    /**
     * Gets this object's coordinates.
     * 
     * @return Returns this object's coordinates.
     */
    public abstract Point<Double> getCoordinates();
    
    /**
     * Gets this object's dimensions.
     * 
     * @return This object's dimensions.
     */
    public abstract Dimension<Double> getDimensions();
    /*End Abstract Methods*/
}
