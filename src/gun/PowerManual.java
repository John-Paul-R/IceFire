package gun;

import data_management.Bot;
import data_management.BotState;
import data_management.DataManager;
import data_management.Enemy;

public class PowerManual extends BulletPowerSelector
{
	private double _bulletPower;

	public PowerManual(DataManager data) 
	{
		super(data);
		_bulletPower = 2;
	}
	public PowerManual(DataManager data, double power) 
	{
		super(data);
		_bulletPower = power;
	}

	public void setPower(double power)
	{
		_bulletPower = power;
	}
	@Override
	public double getPower(Enemy target)
	{
		return _bulletPower;
	}

	@Override
	public double getPower(BotState target)
	{
		return _bulletPower;
	}
}
