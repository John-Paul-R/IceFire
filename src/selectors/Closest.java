package selectors;

import java.util.Map;

import data_management.Bot;
import data_management.DataManager;
import data_management.Enemy;
import robocode.AdvancedRobot;

public class Closest extends TargetSelector
{
	public Closest(AdvancedRobot self, DataManager data) 
	{
		super(self, data);
	}

	@Override
	public void chooseTarget() 
	{
        String cClosestName = "";
        Enemy cClosestEnemy = null;
        double cClosestDist = Integer.MAX_VALUE;
        for (Map.Entry<String, Enemy> entry : _data.getEnemies().entrySet())
        {
            if (entry.getValue().getDistance() < cClosestDist)
            {
                cClosestName = entry.getKey();
                cClosestEnemy = entry.getValue();
                cClosestDist = cClosestEnemy.getDistance();
            }
        }
        _target = cClosestEnemy;
        _targetName = cClosestName;
	}

	@Override
	public String getTargetName() 
	{
		return _targetName;
	}

	@Override
	public Enemy getTarget()
	{
		return _target;
	}
	
}
