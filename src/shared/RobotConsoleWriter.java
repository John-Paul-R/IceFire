package shared;

import java.io.PrintStream;

import robocode.AdvancedRobot;

public class RobotConsoleWriter {
	private PrintStream _oStream;
	private String _format;
	private AdvancedRobot _robot;
	
	public RobotConsoleWriter(PrintStream printStream, AdvancedRobot robot)
	{
		_oStream = printStream;
		_format = new String("[%d] %s");
		_robot = robot;
	}
	
	public void print(String s)
	{
		_oStream.printf(_format, _robot.getTime(), s);
	}
	public void println(String s)
	{
		_oStream.printf(_format,  _robot.getTime(), s);
		_oStream.println();
	}
	public void println()
	{
		_oStream.println();
	}
	public void close()
	{
		_oStream.close();
	}
}
