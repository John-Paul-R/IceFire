package shared;

import java.awt.geom.Point2D;

import data_management.BotState;
import robocode.AdvancedRobot;

public class Utils 
{
	private static double[] fieldBounds;
	private static double[] absoluteFieldBounds;
	public static double pointToAngle(Point2D origin, Point2D target)
	{
		return Math.atan2(target.getX()-origin.getX(), target.getY()-origin.getY());
		
	}
	public static double pointToAngle(BotState self, Point2D target)
	{
		return Math.atan2(target.getX()-self.getX(), target.getY()-self.getY());
		
	}
	public static double limitValueBounds(double value, double min, double max) 
	{
	    return Math.min(max, Math.max(min, value));
	}
	
	public static Point2D.Double limitCoordinateToMap(Point2D.Double coord) 
	{
		final double bounds[] = getAbsoluteFieldBoundsxXyYy();
	    return new Point2D.Double(limitValueBounds(coord.getX(), bounds[0], bounds[1]), limitValueBounds(coord.getY(), bounds[2], bounds[3]));
	}
	public static Point2D.Double limitCoordinateToMap(double x, double y) 
	{
		final double bounds[] = getAbsoluteFieldBoundsxXyYy();
	    return new Point2D.Double(limitValueBounds(x, bounds[0], bounds[1]), limitValueBounds(y, bounds[2], bounds[3]));
	}
	public static void changeCoordinateToMap(Point2D point) 
	{
		final double bounds[] = getAbsoluteFieldBoundsxXyYy();
	    point.setLocation(limitValueBounds(point.getX(), bounds[0], bounds[1]), limitValueBounds(point.getY(), bounds[2], bounds[3]));
	}
	
	public static Point2D.Double limitCoordinateToMapBuffer(Point2D.Double coord) 
	{
		final double bounds[] = getFieldBoundsxXyYy();
	    return new Point2D.Double(limitValueBounds(coord.getX(), bounds[0], bounds[1]), limitValueBounds(coord.getY(), bounds[2], bounds[3]));
	}
	public static Point2D.Double limitCoordinateToMapBuffer(double x, double y) 
	{
		final double bounds[] = getFieldBoundsxXyYy();
	    return new Point2D.Double(limitValueBounds(x, bounds[0], bounds[1]), limitValueBounds(y, bounds[2], bounds[3]));
	}
	public static void changeCoordinateToMapBuffer(Point2D point) 
	{
		final double bounds[] = getFieldBoundsxXyYy();
	    point.setLocation(limitValueBounds(point.getX(), bounds[0], bounds[1]), limitValueBounds(point.getY(), bounds[2], bounds[3]));
	}
	
    public static double[] getAbsoluteFieldBoundsxXyYy()
    {	
    	return absoluteFieldBounds;

    }
    public static double[] getFieldBoundsxXyYy()
    {	
    	return fieldBounds;

    }
    public static Point2D.Double getFieldCenter()
    {	
    	
    	return new Point2D.Double(absoluteFieldBounds[1]/2, absoluteFieldBounds[3]/2);

    }
    public static void setFieldBoundsArrays(AdvancedRobot self, double buffer)
    {
    	absoluteFieldBounds = new double[] {0, self.getBattleFieldWidth(), 0, self.getBattleFieldHeight()};
    	fieldBounds =  new double[] {self.getWidth()/2+buffer, self.getBattleFieldWidth() - (self.getWidth()/2+buffer), self.getHeight()/2+buffer, self.getBattleFieldHeight() - (self.getHeight()/2+buffer)};
    }
}
