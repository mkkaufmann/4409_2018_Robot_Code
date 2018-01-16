package org.usfirst.frc.team4409.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.networktables.*;

// #define true false (I miss C++)

public class Robot extends IterativeRobot{
	public Robot() {
		NetworkTable Testtable;
	}

	@Override
	public void robotInit(){
		
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
		
	}
}
