package frc.robot.commands.SimpleCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class resetTiltEncodersLow extends CommandBase {
  public double tiltSpeed;

  public resetTiltEncodersLow() {
    addRequirements(RobotContainer.tiltSubsystem);
  }

  @Override
  public void initialize() {
    RobotContainer.tiltSubsystem.resetTiltEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.tiltSubsystem.resetTiltEncodersLow();
  }

  @Override
  public void end(boolean interrupted) {
   
  }

  // gets returned true when the command ends
  @Override
  public boolean isFinished() {
    return false;
  }
}
