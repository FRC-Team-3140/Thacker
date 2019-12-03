/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.libs.XboxController;

import java.util.concurrent.Delayed;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  final DoubleSolenoid.Value EXT = Value.kForward;
  final DoubleSolenoid.Value RET = Value.kReverse;
  final DoubleSolenoid.Value OFF = Value.kOff;

  private WPI_TalonSRX left_front = new WPI_TalonSRX(3);
  private WPI_TalonSRX left_back = new WPI_TalonSRX(6);
  private WPI_TalonSRX right_front = new WPI_TalonSRX(12);
  private WPI_TalonSRX right_back = new WPI_TalonSRX(5);

  XboxController xbox = new XboxController(0);

  private Compressor compressor = new Compressor(1);
  private DoubleSolenoid shifter = new DoubleSolenoid(1, 2, 5);

  boolean lastLeftBumper = false;

  @Override
  public void robotInit() {
    left_back.follow(left_front);
	right_back.follow(right_front);
	
	shifter.set(RET);
	shifter.set(OFF);
  }

  @Override
  public void teleopPeriodic() {
	  double leftSpeed = xbox.getMainY(); // gets left joystick value and sets it to "leftSpeed"
	  double rightSpeed = xbox.getAltY(); // gets right joystick value and sets it to "rightSpeed
	
	  // Sets the speed of a motor controller by specifying a value between -1 (full power backward) and 1 (full power forward)
	  left_front.set(leftSpeed); // Sets the left motors to the speed specified by the left joystick
	  right_front.set(-rightSpeed); // Sets the right motors to the speed specified by the right joystick

	 boolean leftBumper = xbox.getRawButton(1);


	  if(leftBumper != lastLeftBumper && leftBumper == true) {
		  shifter.set(EXT);
	  }

	  if(leftBumper != lastLeftBumper && leftBumper == false) {
		  shifter.set(RET);
	  } 
	

	  lastLeftBumper = leftBumper;
  }
}
