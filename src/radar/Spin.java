package radar;

import data_management.DataManager;
import robocode.AdvancedRobot;

public class Spin extends Radar
{

	public Spin(AdvancedRobot self, DataManager data) {
		super(self, data);
	}

	@Override
	public void execute()
	{
		_self.setTurnRadarRight(Double.POSITIVE_INFINITY);
	}
	
}
