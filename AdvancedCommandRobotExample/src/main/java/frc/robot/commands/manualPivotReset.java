package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class manualPivotReset extends CommandBase {
  public double tiltSpeed;

  public manualPivotReset() {
    addRequirements(RobotContainer.tiltSubsystem);
  }

  @Override
  public void initialize() {
    RobotContainer.tiltSubsystem.resetTiltEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.tiltSubsystem.manualEncoderReset();
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
