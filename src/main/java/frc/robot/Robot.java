/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private WPI_TalonSRX left_front = new WPI_TalonSRX(3);
  private WPI_TalonSRX left_back = new WPI_TalonSRX(6);
  private WPI_TalonSRX right_front = new WPI_TalonSRX(12);
  private WPI_TalonSRX right_back = new WPI_TalonSRX(5);
  @Override
  public void robotInit() {
    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);
    left_back.follow(left_front);
    right_back.follow(right_front);
  }

  @Override
  public void teleopPeriodic() {
    double left_speed = m_leftStick.getAxis(0);
    
    left_front.set(speed);
    right_front.set(-speed);
  }
}
