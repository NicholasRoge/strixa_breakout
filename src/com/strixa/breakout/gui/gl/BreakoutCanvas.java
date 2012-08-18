/**
 * File:  BreakoutCanvas.java
 * Date of Creation:  Jul 24, 2012
 */
package com.strixa.breakout.gui.gl;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.media.opengl.GLCapabilities;

import com.strixa.gl.Strixa2DCanvas;
import com.strixa.gl.StrixaGLContext;
import com.strixa.gl.properties.Cuboid;
import com.strixa.util.Dimension2D;
import com.strixa.util.Point2D;


/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class BreakoutCanvas extends Strixa2DCanvas{    
    static private Cursor __blank_cursor = Toolkit.getDefaultToolkit().createCustomCursor(
        new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB),
        new Point(0,0),
        "blank cursor"
    );
    
    /** Field required for object serialization. */
    private static final long serialVersionUID = 2547118150712420128L;
    static private Random __random_generator = new Random(System.currentTimeMillis());

    
    private Ball        __ball;
    private List<Brick> __bricks;
    private Paddle      __paddle;
    
    
    /*Begin Constructors*/
    /**
     * Constructs the Breakout canvas.
     * 
     * @param size Dimensions this canvas should have in pixels.
     * @param capabilities Capabilities this object should use.
     */
    public BreakoutCanvas(Dimension2D<Integer> size,GLCapabilities capabilities){
        super(size, capabilities);
        
        final Cuboid viewable = this.getStrixaGLContext().getViewableArea();
        
        Brick brick = null;
        
        
        this.setMinorAxisUnits(100);
        
        this.__ball = new Ball(1,100);
        this.__ball.setCoordinates(this.getMajourAxisUnits()/2,this.getMinorAxisUnits()/2);
        this.__ball.setMomentum(-25);
        this.__ball.setAngle(0);
        this.addChild(this.__ball);
        
        this.__paddle = new Paddle(10,1);
        this.__paddle.setCoordinates((this.getMajourAxisUnits()/2),5);
        this.__paddle.setColour(.5f,.5f,.5f);
        this.addChild(this.__paddle);
        
        this.__bricks = new ArrayList<Brick>();
        for(int row = 0;row < 5;row++){
            for(int column = 0;column < 10; column++){
                brick = new Brick(8,2);
                brick.setCoordinates(viewable.getPoint(Cuboid.Mask.FRONT,Cuboid.Mask.TOP,Cuboid.Mask.LEFT).getX()+(column*14)+15,viewable.getPoint(Cuboid.Mask.FRONT,Cuboid.Mask.TOP,Cuboid.Mask.LEFT).getY()+((row*6)-36));
                brick.setColour(.5f,0f,0f);
                
                this.__bricks.add(brick);
                this.addChild(brick);
            }
        }
        
        this.setCursor(BreakoutCanvas.__blank_cursor);  //Hide the cursor.
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets the ball object.
     * 
     * @return The ball object.
     */
    public Ball getBall(){
        return this.__ball;
    }
    
    /**
     * Gets the list of bricks remaining.
     * 
     * @return The list of bricks remaining.
     */
    public List<Brick> getBricks(){
        return this.__bricks;
    }
    
    /**
     * Gets the paddle object.
     * 
     * @return The paddle object.
     */
    public Paddle getPaddle(){
        return this.__paddle;
    }
    /*End Getter/Setter Methods*/

    /*Begin Other Methods*/
    protected void _bounceBall(boolean invert_angle,boolean invert_momentum){        
        int new_number = 0;
        
        
        if(invert_angle){
            while(new_number == 0){
                new_number = -(this.getBall().getAngle()+(__random_generator.nextInt(30)-15));
            }
            
            if(new_number < 0){
                this.getBall().setAngle(Math.max(Math.min(new_number,-15),-75));
            }else{
                this.getBall().setAngle(Math.min(Math.max(new_number,15),75));
            }
        }
            
        if(invert_momentum){            
            this.getBall().setMomentum(-this.getBall().getMomentum());
        }
        
        //Increment the momentum
        if(this.getBall().getMomentum() < 0){
            this.getBall().setMomentum(Math.max(this.getBall().getMomentum() - 1,-50));
        }else{
            this.getBall().setMomentum(Math.min(this.getBall().getMomentum() + 1,50));
        }
        
        //TODO:  Play "bounce" sound
    }
    
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
        }else{  //NOTE:  We don't need to check for when the ball's angle is 90 degrees (i.e.:  Ball is moving only on the x axis)
            delta_x = units_per_period*Math.sin((ball.getAngle()*Math.PI)/180);
            delta_y = units_per_period*Math.cos((ball.getAngle()*Math.PI)/180);
        }
        
        new_coordinates.setPoint(x+delta_x,y+delta_y);
        
        
        return new_coordinates;
    }
    
    @Override public void mouseMoved(MouseEvent event){
        final double adjusted_x = ((double)event.getX()/(double)this.getSize().getWidth())*this.getMajourAxisUnits();
        final double half_width = this.getPaddle().getDimensions().getWidth()/2;
        final double max_x = this.getStrixaGLContext().getViewableArea().getPoint(Cuboid.Mask.FRONT,Cuboid.Mask.BOTTOM,Cuboid.Mask.RIGHT).getX();
        final double min_x = this.getStrixaGLContext().getViewableArea().getPoint(Cuboid.Mask.FRONT,Cuboid.Mask.BOTTOM,Cuboid.Mask.LEFT).getX();
        
        int cursor_x = 0;
        int cursor_y = 0;
        
        Robot robot = null;
        
        
        try {
            robot = new Robot();
        } catch (AWTException e){
            e.printStackTrace();
        }
        
        
        if((adjusted_x - half_width) < min_x){
            cursor_x = (int)(((min_x + half_width)/this.getMajourAxisUnits())*this.getSize().getWidth());
        }else if((adjusted_x + half_width) > max_x){
            cursor_x = (int)(((max_x - half_width)/this.getMajourAxisUnits())*this.getSize().getWidth());
        }else{
            cursor_x = event.getX();
        }
        
        this.getPaddle().getCoordinates().setX(adjusted_x);
        cursor_y = (int)(this.getSize().getHeight()-((this.getPaddle().getCoordinates().getY()/this.getMinorAxisUnits())*this.getSize().getHeight())); //This should change.  This should eb able to be optimized out.
        robot.mouseMove(cursor_x,cursor_y);
    }
    
    protected void _performGameLogic(StrixaGLContext context){
        final List<Brick> bricks = this.getBricks();
        
        Brick brick = null;
        Point2D<Double> new_ball_coordinates = this._getNewBallCoordinates(context);
        Point2D<Double> old_ball_coordinates = this.getBall().getCoordinates();
        
        
        this.getBall().setCoordinates(new_ball_coordinates.getX(),new_ball_coordinates.getY());
        
        /*Check to see if our ball has collided with any of teh walls.*/
        if(new_ball_coordinates.getY()-(this.getBall().getDimensions().getHeight()/2)<this.getStrixaGLContext().getViewableArea().getPoint(Cuboid.Mask.FRONT,Cuboid.Mask.BOTTOM,Cuboid.Mask.LEFT).getY()){
            this._bounceBall(true,true);
            
            //TODO_HIGH:  Cause this to trigger a lost ball, game over, et cetera...  But for now, bounce.
        }else if(new_ball_coordinates.getY()+(this.getBall().getDimensions().getHeight()/2)>this.getStrixaGLContext().getViewableArea().getPoint(Cuboid.Mask.FRONT,Cuboid.Mask.TOP,Cuboid.Mask.LEFT).getY()){
            this._bounceBall(true,true);
        }else if(
            new_ball_coordinates.getX()-(this.getBall().getDimensions().getWidth()/2)<this.getStrixaGLContext().getViewableArea().getPoint(Cuboid.Mask.FRONT,Cuboid.Mask.BOTTOM,Cuboid.Mask.LEFT).getX()
            ||
            new_ball_coordinates.getX()+(this.getBall().getDimensions().getWidth()/2)>this.getStrixaGLContext().getViewableArea().getPoint(Cuboid.Mask.FRONT,Cuboid.Mask.BOTTOM,Cuboid.Mask.RIGHT).getX()
        ){
            this._bounceBall(true,false);
        }
        
        if(this.getBall().isColliding(this.getPaddle())){
            switch(this.getPaddle().getRelativeLocation(this.getBall())){
                case EAST:
                case WEST:
                    this._bounceBall(true,false);
                    
                    break;
                case NORTH:
                case SOUTH:
                    this._bounceBall(true,true);

                    break;
                case NORTHWEST:
                    if(this.getBall().getMomentum() < 0 && this.getBall().getAngle() > 0){
                        this._bounceBall(true,true);
                    }else{
                        this._bounceBall(false,true);
                    }
                    
                    break;
                case SOUTHWEST:
                    if(this.getBall().getMomentum() > 0 && this.getBall().getAngle() < 0){
                        this._bounceBall(true,true);
                    }else{
                        this._bounceBall(false,true);
                    }
                    
                    break;
                case NORTHEAST:
                    if(this.getBall().getMomentum() < 0 && this.getBall().getAngle() < 0){
                        this._bounceBall(true,true);
                    }else{
                        this._bounceBall(false,true);
                    }
                    
                    break;
                case SOUTHEAST:
                    if(this.getBall().getMomentum() > 0 && this.getBall().getAngle() > 0){
                        this._bounceBall(true,true);
                    }else{
                        this._bounceBall(false,true);
                    }
                    
                    break;
                case INSIDE:
                    //Gonna have to do something special here to stop the user from pushing the paddle into the ball.
                    this._bounceBall(false,true);
                    
                    break;
            }
            
            this.getBall().setCoordinates(old_ball_coordinates.getX(),old_ball_coordinates.getY());
        }else{
            for(int index = 0;index < bricks.size(); index++){
                brick = bricks.get(index);
                
                if(this.getBall().isColliding(brick)){
                    switch(brick.getRelativeLocation(this.getBall())){
                        case EAST:
                        case WEST:
                            this._bounceBall(true,false);
                            
                            break;
                        case NORTH:
                        case SOUTH:
                            this._bounceBall(true,true);

                            break;
                        case NORTHWEST:
                            if(this.getBall().getMomentum() < 0 && this.getBall().getAngle() > 0){
                                this._bounceBall(false,true);
                            }else{
                                this._bounceBall(true,true);
                            }
                            
                            break;
                        case SOUTHWEST:
                            if(this.getBall().getMomentum() > 0 && this.getBall().getAngle() < 0){
                                this._bounceBall(false,true);
                            }else{
                                this._bounceBall(true,true);
                            }
                            
                            break;
                        case NORTHEAST:
                            if(this.getBall().getMomentum() < 0 && this.getBall().getAngle() < 0){
                                this._bounceBall(false,true);
                            }else{
                                this._bounceBall(true,true);
                            }
                            
                            break;
                        case SOUTHEAST:
                            if(this.getBall().getMomentum() > 0 && this.getBall().getAngle() > 0){
                                this._bounceBall(false,true);
                            }else{
                                this._bounceBall(true,true);
                            }
                            
                            break;
                        default:
                            this._bounceBall(false,true);
                            
                            break;
                    }
                    
                    
                    this.getBricks().remove(index); 
                    this.getChildren().remove(brick);
                    this.getBall().setCoordinates(old_ball_coordinates.getX(),old_ball_coordinates.getY());
                    
                    break;
                }
            }
        }
        
        
    }
    /*End Other Methods*/
}
