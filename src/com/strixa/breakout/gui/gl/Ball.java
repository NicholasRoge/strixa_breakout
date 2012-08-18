/**
 * File:  Ball.java
 * Date of Creation:  Jul 23, 2012
 */
package com.strixa.breakout.gui.gl;

import com.strixa.gl.shapes.Circle;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class Ball extends Circle{
    private int __angle;
    private int __momentum;
    
    
    /*Begin Constructors*/
    /**
     * Creates a ball with the given dimensions.
     * 
     * @param radius Radius the ball should have.
     * @param fan_count Number of 'fans' the underlying circle should be made up of.
     */
    public Ball(double radius,int fan_count){
        super(radius, fan_count);
        
        this.setMomentum(0);
        this.setAngle(0);
    }
    /*Begin Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets this ball's angle.
     * 
     * @return This ball's angle.
     */
    public int getAngle(){
        return this.__angle;
    }
    
    /**
     * Sets the balls angle.
     * 
     * @param momentum This is the angle the ball is moving.  This angle must not be greater than 89 degrees or less than -89 degrees.
     */
    public void setAngle(int angle){
        if(angle < -89 || angle > 89){
            throw new IllegalArgumentException("Argument 'angle' must be no more than 89 degrees and no less than -89 degrees.");
        }
        
        this.__angle = angle;
    }
    
    /**
     * Gets this ball's momentum.
     * 
     * @return This ball's momentum.
     */
    public int getMomentum(){
        return this.__momentum;
    }
    
    /**
     * Sets the balls momentum.
     * 
     * @param momentum This is the distance (in GL units) the ball moves in one second.
     */
    public void setMomentum(int momentum){
        this.__momentum = momentum;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    /**
     * Gets this object's rising/falling status.
     * 
     * @return Returns true if this object is falling, and false, otherwise.
     */
    public boolean isFalling(){
        return this.getMomentum()<0;
    }
    /*End Other Methods*/
}
