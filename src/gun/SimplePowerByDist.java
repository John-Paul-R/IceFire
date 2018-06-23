package gun;

import data_management.Bot;
import data_management.BotState;
import data_management.DataManager;
import data_management.Enemy;
import shared.Utils;

public class SimplePowerByDist extends BulletPowerSelector
{

	public SimplePowerByDist(DataManager data) 
	{
		super(data);
	}

	@Override
	public double getPower(Enemy target) 
	{
    	final double currentDist = Math.sqrt(Math.pow((target.getX() - _data.getSelf().getX()),2) + Math.pow((target.getY() - _data.getSelf().getY()), 2));
    	//final double power = Utils.limitValueBounds(1/Math.pow((currentDist/500),2), 1.1, 3); //set power to have an inverse square relationship with distance.  Set bounds to 1.1 and 3
    	final double power = Utils.limitValueBounds(1/(currentDist/Math.min(Math.max(Utils.getAbsoluteFieldBoundsxXyYy()[0], Utils.getAbsoluteFieldBoundsxXyYy()[2]), 1000.00)), 1.5, 3);//set power to have an inverse relationship with distance.  Set bounds to 1.05 and 3.  The above method is stupid. That would be for 3d space, not 2d - I think.
    	return power;
	}

	@Override
	public double getPower(BotState target) 
	{
		return 0;
	}

}
