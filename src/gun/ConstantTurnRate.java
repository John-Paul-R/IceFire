package gun;

import java.awt.geom.Point2D;

import data_management.Bot;
import data_management.DataManager;
import data_management.Enemy;
import robocode.AdvancedRobot;
import robocode.Rules;
import shared.Utils;

public class ConstantTurnRate extends Gun
{
	public ConstantTurnRate(AdvancedRobot self, DataManager data, BulletPowerSelector powerSelector) {
		super(self, data, powerSelector);
	}

	public void setTarget(Enemy enemy)
	{
		_target = enemy;
	}

	@Override
	public void execute() 
	{
		_target = _data.getTargetEnemy();
		if (_target.cIndex() > 1) // ensures there is enough data to run the prediction calculation (2 data points needed)
		{
			Point2D.Double aimLocation = getAimLocation();
			super.aimToCoordinate(aimLocation);
			super.setFireBullet(_powerSelector.getPower(_target)); //TODO > Remove unnecessary recalculation of bulletPower
		}
	}

	@Override
	public Point2D.Double getAimLocation() 
	{
    	double turnRate = _target.getTurn();
    	double relX = _target.getX() - _self.getX();
    	double relY = _target.getY() - _self.getY();
    	double cHeading = _target.getHeading();
    	double velocity = _target.getVelocity();
    	
    	double selfX = _self.getX();
    	double selfY = _self.getY();
    	
    	double deltaTime = 0;
    	double dataAgeOffset = _self.getTime() - _target.getTime();
    	while (dataAgeOffset > 0)
    	{
    		relX += (velocity * Math.sin(cHeading + turnRate));
    		relY += (velocity * Math.cos(cHeading + turnRate));
    		cHeading += turnRate;
    		dataAgeOffset -= 1;
    	}
    	
    	final double bulletVelocity = Rules.getBulletSpeed(_powerSelector.getPower(_target));
    	   	
    	while ((bulletVelocity * deltaTime) < Math.abs(Point2D.distance(0, 0, relX, relY)))
    	{
    		relX += (velocity * Math.sin(cHeading + turnRate));
    		relY += (velocity * Math.cos(cHeading + turnRate));
    		cHeading += turnRate;
    		deltaTime += 1;
    	}
		
    	Point2D.Double targetCoord = Utils.limitCoordinateToMap(new Point2D.Double(relX + selfX, relY +selfY));
    	return targetCoord;
	}
}
