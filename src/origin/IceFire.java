package origin;
import java.util.Map.Entry;

import data_management.DataManager;
import gun.ConstantTurnRate;
import gun.Gun;
import gun.SimplePowerByDist;
import move.Circle_Pattern;
import move.Mover;

import java.awt.Color;
import java.util.Properties;

import radar.Radar;
import radar.Spin;
import robocode.AdvancedRobot;
import robocode.BattleEndedEvent;
import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.DeathEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.KeyEvent;
import robocode.MessageEvent;
import robocode.MouseEvent;
import robocode.PaintEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.SkippedTurnEvent;
import robocode.WinEvent;
import selectors.Closest;
import selectors.TargetSelector;
import shared.RobotConsoleWriter;
import shared.Utils;


public class IceFire extends AdvancedRobot
{
	//Formatted Output
	private final String sourceName = this.getClass().getCanonicalName();
	public final RobotConsoleWriter console = new RobotConsoleWriter(System.out, sourceName, this);
	//Managers
	private DataManager _data;
	private Gun _gun;
	private Mover _mover;
	private Radar _radar;
	//Target Selection (Updated automatically based on selected radar.  Can be overridden in bot_options.properties (May cause unexpected results))
	private TargetSelector _targetSelector;
	
	
	public void run() 
	{
		this.setAdjustGunForRobotTurn(true);
		this.setAdjustRadarForGunTurn(true);
		this.setAdjustRadarForRobotTurn(true);
		
		setColors(new Color(255, 100, 0), new Color(240, 255, 255), Color.white, (Math.random() < 0.5) ? new Color(255, 100, 0) :  new Color(240, 255, 255), Color.orange);

		final double FIELD_BUFFER = 20;
		Utils.setFieldBoundsArrays(this, FIELD_BUFFER); //The number here is the buffer distance you want between the field bounds and the max/min values returned from Utils.getFieldBoundsXxYy()

		_data = new DataManager(this);
		_gun = new ConstantTurnRate(this, _data, new SimplePowerByDist(_data)); //Per enemy - Wait, perhaps one gun, but it stores different, trained systems for each enemy?
		_mover = new Circle_Pattern(this, _data); //Hmm.... perhaps not
		_radar = new Spin(this, _data); //
		_targetSelector = new Closest(this, _data);
		
		console.println("< ------- Begin  Loop ------- >");
		console.println();
		
		while(true)
		{
			setColors(new Color(255, 70, 0), new Color(240, 255, 255), Color.white, /*(Math.random() < 0.5) ? new Color(255, 100, 0) :  */new Color(240, 255, 255), Color.orange);

			long currentTime = getTime();
			_data.execute();
			_targetSelector.chooseTarget();
			_data.setTarget(_targetSelector.getTarget(), _targetSelector.getTargetName());
			
			if (_data.getTargetEnemy() != null) 
			{
				_gun.execute();
			}
			_mover.execute();
			_radar.execute();
			
			execute();
		}
	}
	
	//Event Methods
	
	public void onBattleEnded(BattleEndedEvent e) 			{_data.onBattleEnded(e);}
	public void onBulletHitBullet(BulletHitBulletEvent e) 	{_data.onBulletHitBullet(e);}
	public void onBulletHit(BulletHitEvent e) 				{_data.onBulletHit(e);}
	public void onBulletMissed(BulletMissedEvent e)			{_data.onBulletMissed(e);}
	public void onDeath(DeathEvent e) 						{_data.onDeath(e);}
	public void onHitByBullet(HitByBulletEvent e) 			{_data.onHitByBullet(e);}
	public void onHitRobot(HitRobotEvent e) 				{_data.onHitRobot(e);}
	public void onHitWall(HitWallEvent e) 					{_data.onHitWall(e);}
	public void onKey(KeyEvent e) 							{_data.onKey(e);}
	public void onMessage(MessageEvent e) 					{_data.onMessage(e);}
	public void onMouse(MouseEvent e) 						{_data.onMouse(e);}
	public void onPaint(PaintEvent e) 						{_data.onPaint(e);}
	public void onRobotDeath(RobotDeathEvent e) 			{_data.onRobotDeath(e);}
	public void onScannedRobot(ScannedRobotEvent e) 		{_data.onScannedRobot(e);}
	public void onSkippedTurn(SkippedTurnEvent e) 			{_data.onSkippedTurn(e);}
	//public void onStatus(StatusEvent e) 					{_data.onStatus(e);}
	public void onWin(WinEvent e) 							{_data.onWin(e);}
}
