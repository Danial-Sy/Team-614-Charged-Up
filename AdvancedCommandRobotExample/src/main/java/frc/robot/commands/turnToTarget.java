// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Robot;
// import frc.robot.RobotContainer;
// import frc.robot.commands.PIDCommand.RotatePIDCommand;

// public class turnToTarget extends CommandBase {
//   /** Creates a new turnToTarget. */
//   public turnToTarget() {
//     addRequirements(RobotContainer.limelightSubsystem, RobotContainer.driveTrainSubsystem);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {

//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     if (RobotContainer.limelightSubsystem.hasTarget() == 1) {
//       while (RobotContainer.limelightSubsystem.getHorizontalAngle() < -1
//           && RobotContainer.limelightSubsystem.getHorizontalAngle() > 1) {
//         new RotatePIDCommand(RobotContainer.limelightSubsystem.getHorizontalAngle());
//       }
//     }
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     RobotContainer.ledSubsystem.setLedColorOrange();
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
