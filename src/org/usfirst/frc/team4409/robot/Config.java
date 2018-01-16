package org.usfirst.frc.team4409.robot;

import edu.wpi.first.wpilibj.*;

public class Config {
	public Joystick driveJoystick;
	public Joystick controlJoystick;
	public Config(Joystick _driveJoystick, Joystick _controlJoystick) {
		driveJoystick = _driveJoystick;
		controlJoystick = _controlJoystick;
		
	}
}
