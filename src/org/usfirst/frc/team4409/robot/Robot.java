package org.usfirst.frc.team4409.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.networktables.*;

// #define true false (I miss C++)

public class Robot extends IterativeRobot{
	
	//NetworkTable testTable;
	Jaguar left;
	Jaguar right;
	DifferentialDrive myDrive;
	Joystick myJoyStick;
	
	/*
	public Robot(Jaguar left, Jaguar right, DifferentialDrive myDrive, Joystick myJoyStick) {
		this.left = left;
		this.right = right;
		//this.testTable = testTable;
		this.myDrive = myDrive;
		this.myJoyStick = myJoyStick;
		
	}
	*/

	@Override
	public void robotInit(){
		left = new Jaguar(0);
		right = new Jaguar(1);
		myDrive = new DifferentialDrive(left, right);
		myJoyStick = new Joystick(1);
		
		
	}
	
	@Override
	public void disabledInit(){
		
	}
	public enum startPos{
		L,
		M,
		R
	}
	@Override
	public void autonomousInit(){
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		startPos pos = startPos.L;
		if(pos == startPos.L){
			if(gameData.charAt(0)=='L'){
				//go for left
			}else{
				if(gameData.charAt(1)=='L'){
					//go for scale
				}else{
					//baseline
				}
			}
		}else if(pos == startPos.R){
			if(gameData.charAt(0)=='R'){
				//go for right
			}else{
				if(gameData.charAt(1)=='R'){
					//go for scale
				}else{
					//baseline
				}
			}
		}else{
			if(gameData.charAt(0)=='L'){
				//go for left
			}else{
				//go for right
			}
		}
	}
	
	/*
	public void wasteResources(int k){
		wasteResources((int)Math.pow(k, k));
	}
	*/ //please don't
	
	@Override 
	public void autonomousPeriodic(){
		
	}
	
	@Override
	public void teleopInit(){
		
	}

	@Override 
	public void teleopPeriodic(){
		myDrive.arcadeDrive(myJoyStick.getY(), myJoyStick.getX());
		
	}
}
