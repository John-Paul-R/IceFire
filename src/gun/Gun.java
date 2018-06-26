package gun;

import java.awt.geom.Point2D;

import data_management.Bot;
import data_management.DataManager;
import data_management.Enemy;
import origin.Manager;
import robocode.AdvancedRobot;
import shared.RobotConsoleWriter;
import shared.Utils;

public abstract class Gun implements Manager
{
	protected AdvancedRobot _self;
	protected DataManager _data;
	protected BulletPowerSelector _powerSelector;
	protected Enemy _target;
	protected final RobotConsoleWriter console;
	public Gun(AdvancedRobot self, DataManager data, BulletPowerSelector powerSelector)
	{
		_self = self;
		_data = data;
		_powerSelector = powerSelector;
		console = new RobotConsoleWriter(System.out, this.getClass().getCanonicalName(), _self);
		console.println("Created!");
	}
	
	public void aimToCoordinate(Point2D.Double coords)
	{
		_self.setTurnGunRightRadians(robocode.util.Utils.normalRelativeAngle(
				Math.atan2(coords.getX()-_self.getX(), coords.getY()-_self.getY())
				- _self.getGunHeadingRadians()));
	}
    public void aimToCoordinate(double x, double y)
    {
        _self.setTurnGunRightRadians(robocode.util.Utils.normalRelativeAngle(
	            Math.atan2(x-_self.getX(), y-_self.getY())
	            - _self.getGunHeadingRadians()));
    }
    public void aimToAngle(double absAngle)
    {
        _self.setTurnGunRightRadians(robocode.util.Utils.normalRelativeAngle(absAngle - _self.getGunHeadingRadians()));
    }
	public void setFireBullet(double bulletPower)
	{
		if (_self.getGunHeat() == 0)
		{
			_self.setFireBullet(bulletPower);
		}
	}
	
	public abstract Point2D.Double getAimLocation();
	public double getAimAngle()
	{
		return Utils.pointToAngle(_data.getSelf(), getAimLocation());
	}
}
