package move;

import data_management.DataManager;
import origin.Manager;
import robocode.AdvancedRobot;

public abstract class Mover implements Manager
{
	protected AdvancedRobot _self;
	protected DataManager _data;
	
	public Mover(AdvancedRobot self, DataManager data)
	{
		_self = self;
		_data = data;
	}
}
