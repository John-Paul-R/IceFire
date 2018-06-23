package data_management;

import java.awt.geom.Point2D;
import java.util.LinkedList;

import shared.Utils;

public class Bot
{
	protected LinkedList<BotState> _states;
	protected BotState cState;
	
	public Bot()
	{
		_states = new LinkedList<BotState>();
	}
	
	public void addState(BotState state) 
	{
		cState = state;
		_states.add(state);
	}
	
	public BotState getState()
	{
		return cState;
	}
	public BotState getState(int index)
	{
		return _states.get(index);
	}
	
	
	//Getters
	public double getX() {
		return cState.getX();
	}

	public double getY() {
		return cState.getY();
	}

	public double getEnergy() {
		return cState.getEnergy();
	}

	public double getHeading() {
		return cState.getHeading();
	}

	public double getVelocity() {
		return cState.getVelocity();
	}

	public long getTime() {
		return cState.getTime();
	}

	public int cIndex() 
	{
		return _states.size()-1;
	}
	public double getTurn()
	{
		double prevHeading = getState(cIndex()-1).getHeading();
		return  cState.getHeading() - prevHeading;
	}

	public double getBearing(Point2D.Double origin) {
		return Utils.pointToAngle(origin, this.getLocation());
	}
	public double getBearing(Bot bot) {
		return Utils.pointToAngle(bot.getLocation(), this.getLocation());
	}
	public double getBearing(BotState botstate) {
		return Utils.pointToAngle(botstate.getLocation(), this.getLocation());
	}

	public Point2D.Double getLocation() 
	{
		return cState.getLocation();
	}
}
