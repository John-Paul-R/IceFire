package radar;

import java.awt.geom.Point2D;

import data_management.Bot;
import data_management.DataManager;
import data_management.Enemy;
import robocode.AdvancedRobot;
import shared.Utils;

public class Lock extends Radar
{

	public Lock(AdvancedRobot self, DataManager data) {
		super(self, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() 
	{
		Enemy target = _data.getTargetEnemy();
		final double FACTOR = 2.1;
		if (target != null && target.getTime() == _data.getSelf().getTime())
    	{
			double absBearing = target.getBearing();
			_self.setTurnRadarRightRadians( FACTOR * robocode.util.Utils.normalRelativeAngle(absBearing - _self.getRadarHeadingRadians()) );
    	}
		else
		{
			System.out.println("Radar (Lock): TARGET IS NULL");
			_self.setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
		}
	}

}
