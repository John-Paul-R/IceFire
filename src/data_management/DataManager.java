package data_management;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import origin.Manager;
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
import robocode.StatusEvent;
import robocode.WinEvent;
import shared.RobotConsoleWriter;

public class DataManager implements Manager
{
	
	private AdvancedRobot _self;
	private HashMap<String, Enemy> _enemies;
	private LinkedList<BotState> _selfData;
	private BotState cSelf;
	private Enemy cTarget;
	private String cTargetName;

	protected final RobotConsoleWriter console;
	
	public DataManager(AdvancedRobot self) 
	{
		_self = self;
		_enemies = new HashMap<String, Enemy>();
		_selfData = new LinkedList<BotState>();
		console = new RobotConsoleWriter(System.out, this.getClass().getCanonicalName(), _self);
		console.println("Created!");
	}
	
	//Do
	@Override
	public void execute() // Note:  It is almost always optimal to run the 'execute()' method for DataManager before doing anything else in your main while loop
	{
		cSelf = new BotState(_self.getX(), _self.getY(), _self.getEnergy(), _self.getHeadingRadians(), _self.getVelocity(), _self.getTime());
		_selfData.add(cSelf);
	}
	public void onScannedRobot(ScannedRobotEvent e)
	{
		if (!_enemies.containsKey(e.getName()))
		{
			addEnemy(e);
		}
		addEnemyState(e);
	}
	public void addEnemy(ScannedRobotEvent e)
	{
		Enemy enemy = new Enemy();
		_enemies.put(e.getName(), enemy);
	}
	public void addEnemyState(ScannedRobotEvent e)
	{
		Enemy enemy = _enemies.get(e.getName());
        double eX = (cSelf.getX() + Math.sin((_self.getHeadingRadians() + e.getBearingRadians()) % (2*Math.PI)) * e.getDistance());
        double eY = (cSelf.getY() + Math.cos((_self.getHeadingRadians() + e.getBearingRadians()) % (2*Math.PI)) * e.getDistance());
		EnemyState scanData = new EnemyState(eX, eY, e.getEnergy(), e.getHeadingRadians(), e.getVelocity(), e.getTime(), e.getDistance(), e.getBearingRadians() + cSelf.getHeading());
		enemy.addState(scanData);
	}
	
	
	//Get
	public BotState getSelf() 
	{
		return cSelf;
	}
	public Enemy getEnemy(String name)
	{
		return _enemies.get(name);
	}
	public EnemyState getEnemyState(String name)
	{
		return _enemies.get(name).getState();
	}
	public EnemyState getEnemyState(String name, int index)
	{
		return _enemies.get(name).getState(index);
	}
	
	//EVENTS (Note that some events (onStatus) will trigger before the DataManager is initialized)
	public void onBattleEnded(BattleEndedEvent e) {}
	public void onBulletHitBullet(BulletHitBulletEvent e) {}
	public void onBulletHit(BulletHitEvent e) {}
	public void onBulletMissed(BulletMissedEvent e) {}
	public void onDeath(DeathEvent e) {}
	public void onHitByBullet(HitByBulletEvent e) {}
	public void onHitRobot(HitRobotEvent e) {}
	public void onHitWall(HitWallEvent e) {}
	public void onKey(KeyEvent e) {}
	public void onMessage(MessageEvent e) {}
	public void onMouse(MouseEvent e) {}
	public void onPaint(PaintEvent e) {}
	public void onRobotDeath(RobotDeathEvent e) {}
	//public void onScannedRobot(ScannedRobotEvent e) {}
	public void onSkippedTurn(SkippedTurnEvent e) {}
	//public void onStatus(StatusEvent e) {}
	public void onWin(WinEvent e) {}

	public Enemy getTargetEnemy() 
	{
		return cTarget;
	}
	public String getTargetName() 
	{
		return cTargetName;
	}
	public void setTarget(Enemy cTarget, String cTargetName) 
	{
		this.cTarget = cTarget;
		this.cTargetName = cTargetName;
	}

	public Map<String, Enemy> getEnemies() 
	{
		return Collections.unmodifiableMap(_enemies);
	}
	public HashMap<String, Enemy> getEnemiesModifiable() 
	{
		return (HashMap<String, Enemy>) _enemies;
	}
}
