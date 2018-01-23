//WrittenByConor

package org.usfirst.frc.team4409.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.networktables.*;
import java.util.*;

// #define true false (I miss C++)

public class Robot extends IterativeRobot {

	// NetworkTable testTable;
	Jaguar left;
	Jaguar right;
	Jaguar elevator;
	DifferentialDrive myDrive;
	Joystick myJoyStick;
	Compressor pump;
	DoubleSolenoid claw;
	ArrayList<ArrayList<Double>> auto;
	double arm, lift,drive, wait;
	Timer timer;

	/*
	 * public Robot(Jaguar left, Jaguar right, DifferentialDrive myDrive,
	 * Joystick myJoyStick) { this.left = left; this.right = right;
	 * //this.testTable = testTable; this.myDrive = myDrive; this.myJoyStick =
	 * myJoyStick;
	 * 
	 * }
	 */

	@Override
	public void robotInit() {
		left = new Jaguar(0);
		right = new Jaguar(1);
		elevator = new Jaguar(2);
		myDrive = new DifferentialDrive(left, right);
		myJoyStick = new Joystick(1);
		pump = new Compressor(0);
		pump.setClosedLoopControl(true);
		claw = new DoubleSolenoid(0, 1);
		arm = 0; 
		lift = 1; 
		drive = 2;
		wait = 3;
	}

	@Override
	public void disabledInit() {

	}

	public enum startPos {
		L, M, R
	}
	public boolean drive(ArrayList<Double> parameters,boolean isFirst){
		//parameter 0 is not used here
		switch ((int)Math.floor(parameters.get(1))){
			case 0:
				//drive (CHANGE TO ENCODERS)
				myDrive.arcadeDrive(parameters.get(2), parameters.get(3));
				return true;
		case 1:
				//turn
				return true;
		}
		return false;
	}
	
	private boolean wait(ArrayList<Double> parameters, boolean isFirst) {
		// TODO Auto-generated method stub
		if(isFirst){
			timer.reset();
			timer.start();
		}
		if(timer.hasPeriodPassed(parameters.get(1))){
			timer.stop();
			return true;
		}
		return false;
	}

	private boolean lift(ArrayList<Double> parameters, boolean isFirst) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean arm(ArrayList<Double> parameters, boolean isFirst) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void autonomousInit() {
		timer = new Timer();
		auto = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> parameters;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();//check to see if this message is recieved
		
		startPos pos = startPos.L;
		if (pos == startPos.L) {
			if (gameData.charAt(0) == 'L') {
				// go for left
				
			} else {
				if (gameData.charAt(1) == 'L') {
					// go for scale
				} else {
					// baseline
					//check to see that this is run
					parameters = new ArrayList<Double>();
					parameters.add(drive);
					parameters.add(0.00);
					parameters.add(1.00);
					parameters.add(0.00);
					auto.add(parameters);
					parameters = new ArrayList<Double>();
					parameters.add(wait);
					parameters.add(1.00);
					auto.add(parameters);
					parameters = new ArrayList<Double>();
					parameters.add(drive);
					parameters.add(0.00);
					parameters.add(0.00);
					parameters.add(0.00);
					auto.add(parameters);
				}
			}
		} else if (pos == startPos.R) {
			if (gameData.charAt(0) == 'R') {
				// go for right
			} else {
				if (gameData.charAt(1) == 'R') {
					// go for scale
				} else {
					// baseline
					parameters = new ArrayList<Double>();
					parameters.add(drive);
					parameters.add(0.00);
					parameters.add(1.00);
					parameters.add(0.00);
					auto.add(parameters);
					parameters = new ArrayList<Double>();
					parameters.add(wait);
					parameters.add(1.00);
					auto.add(parameters);
					parameters = new ArrayList<Double>();
					parameters.add(drive);
					parameters.add(0.00);
					parameters.add(0.00);
					parameters.add(0.00);
					auto.add(parameters);
				}
			}
		} else {
			if (gameData.charAt(0) == 'L') {
				// go for left
			} else {
				// go for right
			}
		}
	}

	/*
	 * public void wasteResources(int k){ wasteResources((int)Math.pow(k, k)); }
	 */ // please don't

	@Override
	public void autonomousPeriodic() {
		int index = 0;
		boolean isComplete = true;
		boolean isFirst = true;
		switch((int)Math.floor(auto.get(0).get(0))){
			case 0:
				isComplete = arm(auto.get(0),isFirst);
			case 1:
				isComplete = lift(auto.get(0),isFirst);
			case 2:
				isComplete = drive(auto.get(0),isFirst);
			case 3:
				isComplete = wait(auto.get(0),isFirst);
		}
		isFirst = false;
		if(isComplete){
			isComplete = false;
			isFirst = true;
			if(auto.size() > index){
				index++;
			}
		}
	}

	

	@Override
	public void teleopInit() {

	}

	public enum Buttons {
		Placeholder, Trigger, StickBot, StickTop, StickL, StickR, LeftBack, LeftFront, FrontLeft, FrontRight, RightFront, RightBack,
	}

	@Override
	public void teleopPeriodic() {
		myDrive.arcadeDrive(myJoyStick.getY(), myJoyStick.getX());
		if (myJoyStick.getRawButton(Buttons.Trigger.ordinal())) {
			claw.set(DoubleSolenoid.Value.kForward);
		} else {
			claw.set(DoubleSolenoid.Value.kReverse);
		}

		if (myJoyStick.getRawButton(Buttons.StickBot.ordinal())) {
			elevator.set(-1);
		} else {
			elevator.set(0);
		}

		if (myJoyStick.getRawButton(Buttons.StickTop.ordinal())) {
			elevator.set(1);
		} else {
			elevator.set(0);
		}
	}
}
