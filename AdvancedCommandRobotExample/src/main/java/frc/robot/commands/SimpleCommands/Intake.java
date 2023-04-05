package frc.robot.commands.SimpleCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends CommandBase {
  public double intakeSpeed;
  public Timer timer;
  public static boolean commandActivated = false;
  public Intake(double intakespeed) {
    addRequirements(RobotContainer.manipulator);
    intakeSpeed = intakespeed;
    timer = new Timer();
  }

  @Override
  public void initialize() {
      commandActivated = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.manipulator.set(intakeSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.manipulator.set(Constants.MOTOR_REST_BACK);
  
  }

  // gets returned true when the command ends
  @Override
  public boolean isFinished() {
    return false;
  }
}
