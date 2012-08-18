/**
 * File:  Line.java
 * Date of Creation:  Jul 26, 2012
 */
package com.strixa.util;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class Line{
    private Point2D<Double> __left_endpoint;
    private Point2D<Double> __right_endpoint;
    
    
    /*Begin Constructors*/
    /**
     * Constructs a line with it's endpoints at the given locations.
     * 
     * @param endpoint_one
     * @param endpoint_two
     */
    @SuppressWarnings("javadoc") //There's no need to give the parameters descriptions
    public Line(Point2D<Double> endpoint_one,Point2D<Double> endpoint_two){
        if(endpoint_one==null || endpoint_two==null){
            throw new NullPointerException();
        }
        
        if(endpoint_one.getX()<=endpoint_two.getX()){
            this.__left_endpoint = endpoint_one;
            this.__right_endpoint = endpoint_two;
        }else{
            this.__right_endpoint = endpoint_one;
            this.__left_endpoint = endpoint_two;
        }
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets the leftmost endpoint.
     * 
     * @return The leftmost endpoint.
     */
    public Point2D<Double> getLeftEndpoint(){
        return this.__left_endpoint;
    }
    
    /**
     * Gets the rightmost endpoint.
     * 
     * @return The rightmost endpoint.
     */
    public Point2D<Double> getRightEndpoint(){
        return this.__right_endpoint;
    }
    
    /**
     * Gets this line's slope.
     * 
     * @return This line's slope.
     */
    public double getSlope(){
        return (this.getLeftEndpoint().getX() - this.getRightEndpoint().getX())/(this.getLeftEndpoint().getY() - this.getRightEndpoint().getY());
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    /**
     * Gets the point of intersection between two lines.
     * 
     * @param line1
     * @param line2
     * 
     * @return A point representing the location the given lines intersect at.
     */
    @SuppressWarnings("javadoc") //There's no need to give the parameters descriptions
    public static Point2D<Double> getIntersectionPoint(Line line1,Line line2){
        final Double[] x = {line1.getLeftEndpoint().getX(),line1.getRightEndpoint().getX(),line2.getLeftEndpoint().getX(),line2.getRightEndpoint().getX()};
        final Double[] y = {line1.getLeftEndpoint().getY(),line1.getRightEndpoint().getY(),line2.getLeftEndpoint().getY(),line2.getRightEndpoint().getY()};
        final double denominator = ((y[3] - y[2])*(x[1] - x[0]))-((x[3] - x[2])*(y[1] - y[0]));
        
        double magnitude_fraction_one = 0;
        double magnitude_fraction_two = 0;
        
        
        magnitude_fraction_one = ((x[3] - x[2]) * (y[0] - y[2])) - ((y[3] - y[2]) * (x[0] - x[2]));
        magnitude_fraction_two = ((y[3] - y[2]) * (y[0] - y[2])) - ((y[1] - y[0]) * (x[0] - x[2]));
        
        if(denominator == 0){
            if(magnitude_fraction_one == 0 && magnitude_fraction_two == 0){
                return null;  //TODO_HIGH:  Determine what to do here.  This occurs when infinitely many points could be returned.
            }else{
                return null; //No intersection;
            }
        }
        
        magnitude_fraction_one /= denominator;
        magnitude_fraction_two /= denominator;
        if(magnitude_fraction_one < 0 || magnitude_fraction_one > 1 || magnitude_fraction_two <0 || magnitude_fraction_two > 1){
            return null; //No intersection
        }
        
        return new Point2D<Double>(x[0]+magnitude_fraction_one*(x[1]-x[0]),y[0]+magnitude_fraction_one*(y[1]-y[0]));
    }
    
    public String toString(){
        return "Line segment ("+this.getLeftEndpoint().getX()+","+this.getLeftEndpoint().getY()+") to ("+this.getRightEndpoint().getX()+","+this.getRightEndpoint().getY()+")";
    }
    /*End Other Methods*/
}
