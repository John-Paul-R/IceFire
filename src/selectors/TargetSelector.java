package selectors;

import data_management.Bot;
import data_management.DataManager;
import data_management.Enemy;
import robocode.AdvancedRobot;
import shared.RobotConsoleWriter;

public abstract class TargetSelector
{
	protected AdvancedRobot _self;
	protected DataManager _data;
	protected Enemy _target;
	protected String _targetName;
	
	protected final RobotConsoleWriter console;
	
	public TargetSelector(AdvancedRobot self, DataManager data)
	{
		_self = self;
		_data = data;
		console = new RobotConsoleWriter(System.out, this.getClass().getCanonicalName(), _self);
		console.println("Created!");
	}
	
	public abstract void chooseTarget();
	public abstract String getTargetName();
	public abstract Enemy getTarget();
}
