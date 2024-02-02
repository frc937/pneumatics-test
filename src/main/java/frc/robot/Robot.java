// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
 * Asimov's Laws:
 * The First Law: A robot may not injure a human being or, through inaction, allow a human being to come to harm.
 * The Second Law: A robot must obey the orders given it by human beings except where such orders would conflict with the First Law.
 * The Third Law: A robot must protect its own existence as long as such protection does not conflict with the First or Second Law.
 */

package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public DoubleSolenoid solenoid1;
  public DoubleSolenoid solenoid2;
  public static XboxController controller;
  public CANSparkMax leftStickSparkMax;
  public CANSparkMax rightStickSparkMax;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    solenoid1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);
    solenoid2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5);
    controller = new XboxController(0);
    leftStickSparkMax = new CANSparkMax(3, MotorType.kBrushless);
    rightStickSparkMax = new CANSparkMax(5, MotorType.kBrushless);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    if (controller.getPOV() == 0 || controller.getPOV() == 360) {
      solenoid1.set(Value.kForward);
      solenoid2.set(Value.kForward);
    } else if (controller.getPOV() == 180) {
      solenoid1.set(Value.kReverse);
      solenoid2.set(Value.kReverse);
    } else if (controller.getAButton()) {
      solenoid1.set(Value.kOff);
      solenoid2.set(Value.kOff);
    }

    leftStickSparkMax.set(getScaledControllerLeftYAxis());
    rightStickSparkMax.set(getScaledControllerRightYAxis());
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}

  private static double scaleAxis(double axis) {
    double deadbanded = MathUtil.applyDeadband(axis, 0.1);
    return Math.pow(deadbanded, 3);
  }

  /**
   * Gets x-axis of left stick of driver controller.
   *
   * @return x-axis of left stick of driver controller.
   */
  public static double getControllerLeftXAxis() {
    return controller.getLeftX();
  }

  /**
   * Gets scaled x-axis of left stick of driver controller.
   *
   * @return scaled x-axis of left stick of driver controller.
   */
  public static double getScaledControllerLeftXAxis() {
    return scaleAxis(getControllerLeftXAxis());
  }

  /**
   * Gets y-axis of left stick of driver controller.
   *
   * @return y-axis of left stick of driver controller.
   */
  public static double getControllerLeftYAxis() {
    return controller.getLeftY();
  }

  /**
   * Gets scaled y-axis of left stick of driver controller.
   *
   * @return scaled y-axis of left stick of driver controller.
   */
  public static double getScaledControllerLeftYAxis() {
    return scaleAxis(getControllerLeftYAxis());
  }

  /**
   * Gets x-axis of right stick of driver controller.
   *
   * @return x-axis of right stick of driver controller.
   */
  public static double getControllerRightXAxis() {
    return controller.getRightX();
  }

  /**
   * Gets scaled x-axis of right stick of driver controller.
   *
   * @return scaled x-axis of right stick of driver controller.
   */
  public static double getScaledControllerRightXAxis() {
    return scaleAxis(getControllerRightXAxis());
  }

  /**
   * Gets y-axis of right stick of driver controller.
   *
   * @return y-axis of right stick of driver controller.
   */
  public static double getControllerRightYAxis() {
    return controller.getRightY();
  }

  /**
   * Gets scaled y-axis of right stick of driver controller.
   *
   * @return scaled y-axis of right stick of driver controller.
   */
  public static double getScaledControllerRightYAxis() {
    return scaleAxis(getControllerRightYAxis());
  }
}
