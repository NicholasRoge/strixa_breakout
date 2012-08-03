/**
 * File:  Strixa2DCanvas.java
 * Date of Creation:  Jul 19, 2012
 */
package com.strixa.gl;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.glu.GLU;

import com.strixa.gl.properties.Cuboid;
import com.strixa.gl.properties.Cuboid.PointMask;
import com.strixa.util.Dimension2D;
import com.strixa.util.Point2D;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public abstract class Strixa2DCanvas extends StrixaGLCanvas{
    private List<Strixa2DElement> __children;
    private double                __minor_axis_units;
    private double                __majour_axis_units;
    private Point2D<Double>         __bottom_left_vertex;
    
    
    /*Begin Constructors*/
    public Strixa2DCanvas(GLCapabilities capabilities){
        this(new Dimension2D<Integer>(0,0),capabilities);
    }
    
    public Strixa2DCanvas(Dimension2D<Integer> size,GLCapabilities capabilities){
        super(size,capabilities);
        
        this.setMinorAxisUnits(100);
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    //TODO:  Think of a better name for this.
    public Point2D<Double> getBottomLeftVertex(){
        if(this.__bottom_left_vertex == null){
            this.__bottom_left_vertex = new Point2D<Double>(0.0,0.0);
        }
        
        return this.__bottom_left_vertex;
    }
    
    /**
     * Gets this object's StrixaGLElement children.
     * 
     * @return This object's StrixaGLElement children.
     */
    public List<Strixa2DElement> getChildren(){
        if(this.__children==null){
            this.__children = new ArrayList<Strixa2DElement>();
        }
        
        return this.__children;
    }
    
    public double getMajourAxisUnits(){
        return this.__majour_axis_units;
    }
    
    public void setMajourAxisUnits(double num_units){
        this.setMajourAxisUnits(num_units,true);
    }
    
    public void setMajourAxisUnits(double num_units,boolean adjust_minor){
        this.__majour_axis_units = num_units;
        if(adjust_minor){
            this.__minor_axis_units = this.__majour_axis_units/(this.getSize().getWidth()/this.getSize().getHeight());
        }
        
        this._refreshViewableArea();
    }
    
    public double getMinorAxisUnits(){
        return this.__minor_axis_units;
    }
    
    public void setMinorAxisUnits(double num_units){
        this.setMinorAxisUnits(num_units,true);
    }
    
    public void setMinorAxisUnits(double num_units,boolean adjust_majour){        
        this.__minor_axis_units = num_units;       
        if(adjust_majour){
            this.__majour_axis_units = this.__minor_axis_units*(this.getSize().getWidth()/this.getSize().getHeight());
        }
        
        this._refreshViewableArea();
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Essential Methods*/
    /**
     * Adds a child to this canvas.
     * 
     * @param child Child to be added to the canvas.
     */
    public void addChild(Strixa2DElement child){
        final List<Strixa2DElement> children = this.getChildren();
        
        
        if(!children.contains(child)){
            children.add(child);
        }
    }
    
    public void _drawChildren(GL2 gl){
        if(this.getChildren().size()==0){
            return;
        }
        
        final Point2D<Double>       bottom_left_vertex = this.getStrixaGLContext().getViewableArea().getPoint(PointMask.FRONT,PointMask.BOTTOM,PointMask.LEFT);
        final List<Strixa2DElement> children = this.getChildren();
        final GLU                   glu = new GLU();
        final Point2D<Double>       top_right_vertex = this.getStrixaGLContext().getViewableArea().getPoint(PointMask.FRONT,PointMask.TOP,PointMask.RIGHT); 
        final Dimension2D<Double>   viewable_area = new Dimension2D<Double>(top_right_vertex.getX()-bottom_left_vertex.getX(),top_right_vertex.getY()-bottom_left_vertex.getY());
        
        Point2D<Double> viewing_area_center = new Point2D<Double>(0.0,0.0);
        
        
        /*Set up the camera*/
        viewing_area_center.setX(bottom_left_vertex.getX()+(viewable_area.getWidth()/2));
        viewing_area_center.setY(bottom_left_vertex.getY()+(viewable_area.getHeight()/2));
        
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        
        glu.gluPerspective(90,this.getSize().getWidth()/this.getSize().getHeight(),1,1000);
        glu.gluLookAt(viewing_area_center.getX(),viewing_area_center.getY(),this.getMinorAxisUnits()/2,viewing_area_center.getX(),viewing_area_center.getY(),0,0,1,0);
        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        
        /*Draw the models!*/
        synchronized(children){
            for(Strixa2DElement child:children){
                if(child.isVisible(this.getStrixaGLContext())){
                    gl.glPushMatrix();                    
                        child.draw(gl);
                    gl.glPopMatrix();
                }
            }
        }
        
        this.swapBuffers();
    }
    
    protected void _refreshViewableArea(){
        final Point2D<Double> bottom_left_vertex = this.getBottomLeftVertex();
        final Cuboid          viewable_area = this.getStrixaGLContext().getViewableArea(); 
        final boolean         x_is_majour_axis = (this.getSize().getWidth()/this.getSize().getHeight())>1;
        
        Point2D<Double> point = null;
        
        
        if(x_is_majour_axis){
            point = viewable_area.getPoint(PointMask.FRONT,PointMask.TOP,PointMask.LEFT);
            point.setY(bottom_left_vertex.getY()+this.__minor_axis_units);
            
            point = viewable_area.getPoint(PointMask.FRONT,PointMask.TOP,PointMask.RIGHT);
            point.setY(bottom_left_vertex.getY()+this.__minor_axis_units);
            
            point = viewable_area.getPoint(PointMask.BACK,PointMask.TOP,PointMask.LEFT);
            point.setY(bottom_left_vertex.getY()+this.__minor_axis_units);
            
            point = viewable_area.getPoint(PointMask.BACK,PointMask.TOP,PointMask.RIGHT);
            point.setY(bottom_left_vertex.getY()+this.__minor_axis_units);
        }else{
            point = viewable_area.getPoint(PointMask.FRONT,PointMask.BOTTOM,PointMask.RIGHT);
            point.setX(bottom_left_vertex.getX()+this.__minor_axis_units);
            
            point = viewable_area.getPoint(PointMask.FRONT,PointMask.TOP,PointMask.RIGHT);
            point.setX(bottom_left_vertex.getX()+this.__minor_axis_units);
            
            point = viewable_area.getPoint(PointMask.BACK,PointMask.BOTTOM,PointMask.RIGHT);
            point.setX(bottom_left_vertex.getX()+this.__minor_axis_units);
            
            point = viewable_area.getPoint(PointMask.BACK,PointMask.TOP,PointMask.RIGHT);
            point.setX(bottom_left_vertex.getX()+this.__minor_axis_units);
        }
        
        if(x_is_majour_axis){
            point = viewable_area.getPoint(PointMask.FRONT,PointMask.BOTTOM,PointMask.RIGHT);
            point.setX(bottom_left_vertex.getX()+this.__majour_axis_units);
            
            point = viewable_area.getPoint(PointMask.FRONT,PointMask.TOP,PointMask.RIGHT);
            point.setX(bottom_left_vertex.getX()+this.__majour_axis_units);
            
            point = viewable_area.getPoint(PointMask.BACK,PointMask.BOTTOM,PointMask.RIGHT);
            point.setX(bottom_left_vertex.getX()+this.__majour_axis_units);
            
            point = viewable_area.getPoint(PointMask.BACK,PointMask.TOP,PointMask.RIGHT);
            point.setX(bottom_left_vertex.getX()+this.__majour_axis_units);
        }else{
            point = viewable_area.getPoint(PointMask.FRONT,PointMask.TOP,PointMask.LEFT);
            point.setY(bottom_left_vertex.getY()+this.__majour_axis_units);
            
            point = viewable_area.getPoint(PointMask.FRONT,PointMask.TOP,PointMask.RIGHT);
            point.setY(bottom_left_vertex.getY()+this.__majour_axis_units);
            
            point = viewable_area.getPoint(PointMask.BACK,PointMask.TOP,PointMask.LEFT);
            point.setY(bottom_left_vertex.getY()+this.__majour_axis_units);
            
            point = viewable_area.getPoint(PointMask.BACK,PointMask.TOP,PointMask.RIGHT);
            point.setY(bottom_left_vertex.getY()+this.__majour_axis_units);
        }
    }
    
    /**
     * Removes a child from this canvas.
     * 
     * @param child Child to be removed from the canvas.
     */
    public void removeChild(StrixaGLElement child){
        final List<Strixa2DElement> children = this.getChildren();
        
        
        if(children.contains(child)){
            children.remove(child);
        }
    }
    
    public void shiftViewingArea(double x_modification,double y_modification){
        final Point2D<Double> bottom_left_vertex = this.getBottomLeftVertex(); 
        final double delta_x = bottom_left_vertex.getX()-x_modification;
        final double delta_y = bottom_left_vertex.getY()-y_modification;
        
        
        if(delta_x<0){
            throw new IllegalArgumentException("Argument 'x_modification' must not cause the viewing_area's left edge to be less than 0.");//TODO:  Reword this...
        }else if(delta_y<0){
            throw new IllegalArgumentException("Argument 'y_modification' must not cause the viewing_area's bottom edge to be less than 0.");//TODO:  Reword this...
        }
        
        bottom_left_vertex.setX(delta_x);
        bottom_left_vertex.setY(delta_y);
        this._refreshViewableArea();
    }
    /*Begin Other Essential Methods*/
}
