/**
 * File:  BreakoutCanvas.java
 * Date of Creation:  Jul 24, 2012
 */
package com.strixa.breakout.gui.gl;

import javax.media.opengl.GL2;
import javax.media.opengl.GLCapabilities;

import com.strixa.gl.Strixa2DCanvas;
import com.strixa.gl.Strixa2DElement;
import com.strixa.gl.StrixaGLContext;
import com.strixa.gl.StrixaPolygon;
import com.strixa.gl.properties.Cuboid;
import com.strixa.gl.shapes.Circle;
import com.strixa.gl.shapes.Rectangle;
import com.strixa.util.Dimension2D;
import com.strixa.util.Point2D;


/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class BreakoutCanvas extends Strixa2DCanvas{
    private Ball   __ball;
    private Paddle __paddle;
    
    
    /*Begin Constructors*/
    /**
     * 
     * 
     * @param size
     * @param capabilities
     */
    public BreakoutCanvas(Dimension2D<Integer> size,GLCapabilities capabilities){
        super(size, capabilities);
        
        
        this.setMinorAxisUnits(100);
        
        this.__ball = new Ball(1,100);
        this.__ball.setCoordinates(this.getMajourAxisUnits()/2,this.getMinorAxisUnits()/2);
        this.__ball.setMomentum(12);
        this.__ball.setAngle(0);
        this.addChild(this.__ball);
        
        this.__paddle = new Paddle(10,1);
        this.__paddle.setCoordinates((this.getMajourAxisUnits()/2),5);
        this.__paddle.setColour(.5f,.5f,.5f);
        this.addChild(this.__paddle);
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    public Ball getBall(){
        return this.__ball;
    }
    
    public Paddle getPaddle(){
        return this.__paddle;
    }
    /*End Getter/Setter Methods*/

    /*Begin Other Methods*/
    protected Point2D<Double> _getNewBallCoordinates(StrixaGLContext context){
        final Ball            ball = this.getBall();
        final Point2D<Double> new_coordinates = new Point2D<Double>(0.0,0.0);
        final double          units_per_period = (double)this.__ball.getMomentum()/context.getCurrentFPS();

        double delta_x = 0;
        double delta_y = 0;
        double x = ball.getCoordinates().getX();
        double y = ball.getCoordinates().getY();
        
        
        /*Check for special cases*/
        if(ball.getAngle()==0){
            delta_x = 0;
            delta_y = units_per_period;
        }else{
            delta_x = units_per_period*Math.sin((ball.getAngle()*Math.PI)/180);
            delta_y = delta_x/(units_per_period*Math.tan((ball.getAngle()*Math.PI)/180));
        }
        
        new_coordinates.setPoint(x+delta_x,y+delta_y);
        
        
        return new_coordinates;
    }
    
    protected void _performGameLogic(StrixaGLContext context){
        Point2D<Double> new_ball_coordinates = this._getNewBallCoordinates(context);
        
        
        /*Check to see if our ball has collided with any of teh walls.*/
        if(new_ball_coordinates.getY()-(this.getBall().getDimensions().getHeight()/2)<this.getStrixaGLContext().getViewableArea().getPoint(Cuboid.PointMask.FRONT,Cuboid.PointMask.BOTTOM,Cuboid.PointMask.LEFT).getY()){
            this.getBall().setMomentum(-this.getBall().getMomentum());
            this.getBall().setAngle(-this.getBall().getAngle());
            
            //TODO_HIGH:  Cause this to trigger a lost ball, game over, et cetera...
        }else if(new_ball_coordinates.getY()+(this.getBall().getDimensions().getHeight()/2)>this.getStrixaGLContext().getViewableArea().getPoint(Cuboid.PointMask.FRONT,Cuboid.PointMask.TOP,Cuboid.PointMask.LEFT).getY()){
            this.getBall().setMomentum(-this.getBall().getMomentum());
            this.getBall().setAngle(-this.getBall().getAngle());
        }else if(new_ball_coordinates.getX()-(this.getBall().getDimensions().getWidth()/2)<this.getStrixaGLContext().getViewableArea().getPoint(Cuboid.PointMask.FRONT,Cuboid.PointMask.BOTTOM,Cuboid.PointMask.LEFT).getX()){
            this.getBall().setAngle(-this.getBall().getAngle());
        }else if(new_ball_coordinates.getX()+(this.getBall().getDimensions().getWidth()/2)>this.getStrixaGLContext().getViewableArea().getPoint(Cuboid.PointMask.FRONT,Cuboid.PointMask.BOTTOM,Cuboid.PointMask.RIGHT).getX()){
            this.getBall().setAngle(-this.getBall().getAngle());
        }else if(this.getBall().isColliding(getPaddle())){
            this.getBall().setMomentum(-this.getBall().getMomentum());
            this.getBall().setAngle(-this.getBall().getAngle());
        }else{
            this.getBall().setCoordinates(new_ball_coordinates.getX(),new_ball_coordinates.getY());
        }
        
        
    }
    /*End Other Methods*/
}
