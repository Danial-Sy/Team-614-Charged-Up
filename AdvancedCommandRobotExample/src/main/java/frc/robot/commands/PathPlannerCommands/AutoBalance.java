// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.PathPlannerCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AutoBalance extends CommandBase {
  private double error;
  private double currentAngle;
  private double lastAngle = 0;
  private double drivePower;
  private long balanceTimeMili = 0;
  private double ForwardMult = 1.5;
  private double maxSpeed = 0.5;
  private double diferenceInAngle;
  double stopAngle = 10.0;
  boolean driveBackwards;

  public AutoBalance() {
    addRequirements(RobotContainer.driveTrainSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("AutoBalanceStopAngle", stopAngle);

    this.currentAngle = RobotContainer.driveTrainSubsystem.getRoll();
    if (currentAngle > Constants.AUTO_BALANCE_FORWARD_ANGLE) { 
      RobotContainer.driveTrainSubsystem.arcadeDrive(Constants.MOTOR_THREE_SPEED, Constants.MOTOR_ZERO_SPEED);
    } else if (currentAngle < Constants.AUTO_BALANCE_REVERSE_ANGLE) { 
      RobotContainer.driveTrainSubsystem.arcadeDrive(Constants.MOTOR_NEGATIVE_THREE_SPEED, Constants.MOTOR_ZERO_SPEED);
    } else if (currentAngle <= Constants.AUTO_BALANCE_NEGATIVE_FORWARD_ANGLE && currentAngle >= Constants.AUTO_BALANCE_REVERSE_ANGLE) {
      RobotContainer.driveTrainSubsystem.arcadeDrive(Constants.MOTOR_ZERO_SPEED, Constants.MOTOR_ZERO_SPEED);
      RobotContainer.driveTrainSubsystem.setBreakMode();
    }
    SmartDashboard.putNumber("drivePower*FM", (drivePower * ForwardMult));
    SmartDashboard.putNumber("pitch balance angle", currentAngle);
    SmartDashboard.putNumber("ForwardMult", ForwardMult);
    SmartDashboard.putNumber("difInAngle", diferenceInAngle);
    SmartDashboard.putNumber("AutoBalance maxSpeed", maxSpeed);
    SmartDashboard.putBoolean("balancing", true);

    this.lastAngle = currentAngle;
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}