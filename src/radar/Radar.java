package radar;

import data_management.DataManager;
import origin.Manager;
import robocode.AdvancedRobot;

public abstract class Radar implements Manager
{
	protected AdvancedRobot _self;
	protected DataManager _data;
	
	public Radar(AdvancedRobot self, DataManager data)
	{
		_self = self;
		_data = data;
	}
}
