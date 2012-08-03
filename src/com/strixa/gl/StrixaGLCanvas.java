/**
 * File:  StrixaGLCanvas.java
 * Date of Creation:  Jul 16, 2012
 */
package com.strixa.gl;

import com.strixa.util.Dimension2D;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

/**
 * Creates an object which any Strixa elements should be drawn on.
 *
 * @author Nicholas Rogé
 */
public abstract class StrixaGLCanvas extends GLCanvas implements GLEventListener,Runnable{
    private static final long serialVersionUID = -6426147154592668101L;
    
    private StrixaGLContext __context;
    private boolean         __exiting;
    private Thread          __gui_thread;
    
    
    /*Begin Constructors*/
    /**
     * Constructs the objects with the given capabilities.
     * 
     * @param capabilities Capabilities GLCanvas should have.
     */
    public StrixaGLCanvas(GLCapabilities capabilities){
        this(new Dimension2D<Integer>(0,0),capabilities);
    }
    
    /**
     * Constructs the objects with the given size and capabilities.
     * 
     * @param size Size this canvas should be.
     * @param capabilities Capabilities GLCanvas should have.
     */
    public StrixaGLCanvas(Dimension2D<Integer> size,GLCapabilities capabilities){
        super(capabilities);
        

        this.addGLEventListener(this);
        this.setSize(new java.awt.Dimension(size.getWidth(),size.getHeight()));
        
        this.__exiting=false;
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    public void setFPS(int fps){
        this.__context.setCurrentFPS(fps);
    }
    
    public int getFPS(){
        return this.__context.getCurrentFPS();
    }
    
    public Thread getGUIThread(){
        if(this.__gui_thread == null){
            this.__gui_thread = new Thread(this);
        }
        
        return this.__gui_thread;
    }
    
    /**
     * Gets the canvas' context.
     * 
     * @return The canvas' context.
     */
    public StrixaGLContext getStrixaGLContext(){
        if(this.__context == null){
            this.__context = new StrixaGLContext();
        }
        
        return this.__context;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/  
    public void display(GLAutoDrawable drawable){
        final GLU       glu = new GLU();
        
        
        /*Clear everything up.*/
        drawable.getGL().glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        
        /*Draw everything that needs to be drawn.*/
        this._drawChildren(drawable.getGL().getGL2());
    }
    
    public void dispose(GLAutoDrawable drawable){
    }
    
    public void init(GLAutoDrawable drawable){
        final GL2 gl = (GL2)drawable.getGL();
        
        
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glClearColor(0f,0f,0f,1f);
        
        
        
        
        /*Set up and start the game thread*/
        this.getGUIThread().start();
    }
    
    public void reshape(GLAutoDrawable drawable,int x,int y,int width,int height){
        drawable.getGL().glViewport(x,y,width,height);
    }
    
    public void run(){
        long period = -1;
        long sleep_time = -1;
        long start_time = -1;
        long time_taken = -1;
        
        
        while(!this.__exiting){
            start_time = System.currentTimeMillis();
            period = 1000 / this.getFPS();
            
            this._performGameLogic(this.getStrixaGLContext());
            this.display();

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
    
    public void triggerExiting(){
        this.__exiting = true;
        
        //TODO:  Call this this object's onExit listeners
    }
    /*End Other Methods*/
    
    /*Begin Abstract Methods*/
    protected abstract void _drawChildren(GL2 gl);
    
    protected abstract void _performGameLogic(StrixaGLContext context);
    /*End Abstract Methods*/
}
