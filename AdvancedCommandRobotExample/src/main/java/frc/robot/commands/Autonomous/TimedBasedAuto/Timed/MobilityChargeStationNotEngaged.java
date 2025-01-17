// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous.TimedBasedAuto.Timed;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.TimedBasedAuto.TimedCommands.TimedForwardDrive;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MobilityChargeStationNotEngaged extends SequentialCommandGroup {
  /** Creates a new MobilityChargeStationNotEngaged. */
  public MobilityChargeStationNotEngaged() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new TimedForwardDrive(0.5, 3),
     new TimedForwardDrive(-0.5, 1.5));
  }
}
