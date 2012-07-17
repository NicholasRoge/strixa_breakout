/**
 * File:  GamePanel.java
 * Date of Creation:  Jul 16, 2012
 */
package com.strixa.breakout.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import com.strixa.breakout.gui.gl.Paddle;
import com.strixa.gl.StrixaGLCanvas;
import com.strixa.gui.StrixaWindow;
import com.strixa.gui.panel.StrixaPanel;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class GamePanel extends StrixaPanel implements Runnable{
    private StrixaGLCanvas __canvas;
    private Paddle  __paddle;
    private boolean __exiting;
    private int    __fps;
    private Thread __game_loop;
    
    
    /*Begin Initialization Methods*/
    protected void _initializeGUI(){
        final GLCapabilities capabilities = new GLCapabilities(GLProfile.getDefault());
        
        
        capabilities.setRedBits(8);
        capabilities.setGreenBits(8);
        capabilities.setBlueBits(8);
        capabilities.setAlphaBits(8);
        
        capabilities.setHardwareAccelerated(true);
        capabilities.setDoubleBuffered(true);
        
        this.__canvas = new StrixaGLCanvas(new Dimension(640,480),capabilities);
        this.__paddle = new Paddle(.5,.05);
        this.__canvas.addChild(this.__paddle);
        
        this.setSize(new Dimension(640,480));
        this.setLayout(new BorderLayout());
        
        this.add(this.__canvas,BorderLayout.CENTER);
    }
    /*End Initialization Methods*/
    
    /*Begin Constructors*/
    public GamePanel(StrixaWindow parent){
        super(parent);
        
        this._initializeGUI();
        
        this.__exiting = false;
        this.setFPS(10);
        
        this.__game_loop = new Thread(this);
        this.__game_loop.start();
    }
    /*End Constructors*/
    
    /*Begin Overridden Methods*/
    @Override public void onPanelClose(){
        this.__exiting = true;
    }
    /*End Override Methods*/
    
    /*Begin Getter/Setter Methods*/
    public void setFPS(int fps){
        this.__fps=fps;
    }
    
    public int getFPS(){
        return this.__fps;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    protected void _performGameLogic(){
        
    }
    
    public void run(){
        long period = -1;
        long sleep_time = -1;
        long start_time = -1;
        long time_taken = -1;
        
        
        while(!this.__exiting){
            start_time = System.currentTimeMillis();
            period = 1000 / this.getFPS();
            
            this._performGameLogic();
            this.__canvas.display();

            time_taken = System.currentTimeMillis() - start_time;
            sleep_time = period-time_taken;
            
            try{
                if(sleep_time>0){
                    Thread.sleep(sleep_time);  //If sleep time is less than or equal to 0, we may as well not even sleep.
                }
            }catch(InterruptedException e){
                System.out.println("Thread interrupted for some reason.");  //TODO:  Oh yeah...  That's a really helpful message you have there.  What do you THINK the todo is for?
            }
        }
        
        return;
    }
    /*End Other Methods*/
}
