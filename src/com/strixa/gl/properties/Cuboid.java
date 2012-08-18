/**
 * File:  Cuboid.java
 * Date of Creation:  Jul 19, 2012
 */
package com.strixa.gl.properties;

import java.util.ArrayList;
import java.util.List;

import com.strixa.util.Point3D;

/**
 * Contains the vertices for a Cuboid object.
 *
 * @author Nicholas Rogé
 */
public class Cuboid{
    /**
     * List of point masks for use with {@link Cuboid#getPoint(byte, byte, byte)}
     *
     * @author Nicholas Rogé
     */
    public static class Mask{
        public static final byte RIGHT  = 0x2;
        public static final byte LEFT   = 0x3;
        public static final byte TOP    = 0x4;
        public static final byte BOTTOM = 0x5;
        public static final byte FRONT  = 0x8;
        public static final byte BACK   = 0x9;
    }
    
    private List<Point3D<Double>> __points;
    
    
    /*Begin Constructors*/
    /**
     * Constructs a cuboid with all vertices at (0,0,0).
     */
    public Cuboid(){
        this.__points = new ArrayList<Point3D<Double>>(8);
        
        for(byte counter = 0;counter < 8;counter++){
            this.__points.add(new Point3D<Double>(0.0,0.0,0.0));
        }
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets all vertices on the Cuboid object.
     * 
     * @return All vertices on the Cuboid object.
     */
    public List<Point3D<Double>> getAllVertices(){
        return this.__points;
    }
    
    /**
     * Gets the point at the vertex specified by the parameters
     * 
     * @param z_axis_mask Location relative to the z-axis.  This should be one of either {@link Mask#FRONT} or {@link Mask#BACK} 
     * @param y_axis_mask Location relative to the y-axis.  This should be one of either {@link Mask#TOP} or {@link Mask#BOTTOM}
     * @param x_axis_mask Location relative to the x-axis.  This should be one of either {@link Mask#RIGHT} or {@link Mask#LEFT}
     * 
     * @return Returns the point at the given vertex.
     */
    public Point3D<Double> getPoint(byte z_axis_mask,byte y_axis_mask,byte x_axis_mask){
        byte point_location = 0x0;
        
        
        if((z_axis_mask&0x8)==0){
            throw new IllegalArgumentException("Argument 'z_axis_mask' must be one of either 'FRONT' or 'BACK'.");
        }else if((y_axis_mask&0x4)==0){
            throw new IllegalArgumentException("Argument 'y_axis_mask' must be one of either 'TOP' or 'BOTTOM'.");
        }else if((x_axis_mask&0x2)==0){
            throw new IllegalArgumentException("Argument 'x_axis_mask' must be one of either 'RIGHT' or 'LEFT'.");
        }
        
        point_location+=((z_axis_mask&0x1)<<2);
        point_location+=((y_axis_mask&0x1)<<1);
        point_location+=(x_axis_mask&0x1);
        
        
        return this.__points.get(point_location);
    }
    /*End Getter/Setter Methods*/
}
