package selectors;

import data_management.Bot;
import data_management.DataManager;
import data_management.Enemy;

public abstract class TargetSelector
{
	protected DataManager _data;
	protected Enemy _target;
	protected String _targetName;

	public TargetSelector(DataManager data)
	{
		_data = data;
	}
	
	public abstract void chooseTarget();
	public abstract String getTargetName();
	public abstract Enemy getTarget();
}
