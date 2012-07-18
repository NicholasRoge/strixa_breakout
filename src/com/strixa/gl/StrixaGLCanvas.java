/**
 * File:  StrixaGLCanvas.java
 * Date of Creation:  Jul 16, 2012
 */
package com.strixa.gl;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;

/**
 * Creates an object which any Strixa elements should be drawn on.
 *
 * @author Nicholas Rogé
 */
public class StrixaGLCanvas extends GLCanvas implements StrixaGLElement,GLEventListener{
    private static final long serialVersionUID = -6426147154592668101L;
    
    private List<StrixaGLElement> __children;
    
    
    /*Begin Constructors*/
    /**
     * Constructs the objects with the given capabilities.
     * 
     * @param capabilities Capabilities GLCanvas should have.
     */
    public StrixaGLCanvas(GLCapabilities capabilities){
        this(new Dimension(0,0),capabilities);
    }
    
    /**
     * Constructs the objects with the given size and capabilities.
     * 
     * @param size Size this canvas should be.
     * @param capabilities Capabilities GLCanvas should have.
     */
    public StrixaGLCanvas(Dimension size,GLCapabilities capabilities){
        super(capabilities);
        
        this.addGLEventListener(this);
        this.setSize(size);
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets this object's StrixaGLElement children.
     * 
     * @return This object's StrixaGLElement children.
     */
    public List<StrixaGLElement> getChildren(){
        if(this.__children==null){
            this.__children = new ArrayList<StrixaGLElement>();
        }
        
        return this.__children;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    /**
     * Adds a child to this canvas.
     * 
     * @param child Child to be added to the canvas.
     */
    public void addChild(StrixaGLElement child){
        final List<StrixaGLElement> children = this.getChildren();
        
        
        if(!children.contains(child)){
            children.add(child);
        }
    }
    
    public void draw(GL2 gl){
        final List<StrixaGLElement> children = this.getChildren();
        

        synchronized(children){
            for(StrixaGLElement child:children){
                gl.glPushMatrix();
                    child.draw(gl);
                gl.glPopMatrix();
            }
        }
        
        this.swapBuffers();
    }
    
    public void display(GLAutoDrawable drawable){
        drawable.getGL().glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        this.draw(drawable.getGL().getGL2());
    }
    
    public void dispose(GLAutoDrawable drawable){
    }
    
    public void init(GLAutoDrawable drawable){
        final GL2 gl = (GL2)drawable.getGL();
        
        
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glClearColor(0f,0f,0f,1f);
    }
    
    /**
     * Removes a child from this canvas.
     * 
     * @param child Child to be removed from the canvas.
     */
    public void removeChild(StrixaGLElement child){
        final List<StrixaGLElement> children = this.getChildren();
        
        
        if(children.contains(child)){
            children.remove(child);
        }
    }
    
    public void reshape(GLAutoDrawable drawable,int x,int y,int width,int height){
        drawable.getGL().glViewport(x,y,width,height);
    }
    /*End Other Methods*/ 
}
