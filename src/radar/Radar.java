package radar;

import data_management.DataManager;
import origin.Manager;
import robocode.AdvancedRobot;
import shared.RobotConsoleWriter;

public abstract class Radar implements Manager
{
	protected AdvancedRobot _self;
	protected DataManager _data;
	
	protected final RobotConsoleWriter console;
	public Radar(AdvancedRobot self, DataManager data)
	{
		_self = self;
		_data = data;
		console = new RobotConsoleWriter(System.out, this.getClass().getCanonicalName(), _self);
		console.println("Created!");
	}
}
