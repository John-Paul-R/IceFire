package gun;

import java.awt.geom.Point2D;

import data_management.Bot;
import data_management.DataManager;
import robocode.AdvancedRobot;

public class HeadOn extends Gun
{
	public HeadOn(AdvancedRobot self, DataManager data, BulletPowerSelector powerSelector) {
		super(self, data, powerSelector);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute() 
	{	
		_target = _data.getTargetEnemy();
		Point2D.Double aimLocation = getAimLocation();
		super.aimToCoordinate(aimLocation);
		super.setFireBullet(_powerSelector.getPower(_target));
	}

	@Override
	public Point2D.Double getAimLocation() 
	{
		return new Point2D.Double(_target.getX(), _target.getY());
	}
}
