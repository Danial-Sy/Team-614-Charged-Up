package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.SimpleCommands.Intake;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    DriveTrainSubsystem.zeroHeading();
    RobotContainer.elevatorSubsystem.resetElevatorEncoders();
    RobotContainer.tiltSubsystem.resetTiltEncoders();
    RobotContainer.driveTrainSubsystem.resetEncoderValues();
    RobotContainer.driveTrainSubsystem.setBreakMode();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Tilt Right Encoder Value", RobotContainer.tiltSubsystem.getRightHeight());
    SmartDashboard.putNumber("Tilt Left Encoder Value", RobotContainer.tiltSubsystem.getLeftHeight());
    SmartDashboard.putNumber("Manipulator Velocity:", RobotContainer.manipulator.getEncoderVelocity());
    SmartDashboard.putNumber("Manipulator Count:", RobotContainer.manipulator.count);
    }

  @Override
  public void disabledInit() {
    Intake.commandActivated = false;
    RobotContainer.manipulator.count = 0;
  }

  @Override
  public void disabledPeriodic() {
    RobotContainer.ledSubsystem.setLedColorOrange();
  }

  @Override
  public void disabledExit() {
  }

  @Override
  public void autonomousInit() {
    RobotContainer.ledSubsystem.setLedColorOrange();
    DriveTrainSubsystem.zeroHeading();

    RobotContainer.driveTrainSubsystem.navX.reset();

    RobotContainer.tiltSubsystem.resetTiltEncoders();
    RobotContainer.driveTrainSubsystem.resetEncoderValues();
    RobotContainer.elevatorSubsystem.resetElevatorEncoders();

    RobotContainer.driveTrainSubsystem.setBreakMode();

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null)
      m_autonomousCommand.schedule();
  }

  public void autonomousPeriodic() {
  }

  public void autonomousExit() {
  }

  @Override
  public void teleopInit() {
    // Cancles any scheduled auto commands in teleop mode
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    RobotContainer.ledSubsystem.setLedColorOrange();
  }

  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void teleopExit() {
  }

  @Override
  public void testInit() {
    // Cancles all scheduled auto commands in test mode
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
    SmartDashboard.putNumber("Current ticks of elevator", RobotContainer.elevatorSubsystem.getHeight());
  }

  @Override
  public void testExit() {
  }
}
