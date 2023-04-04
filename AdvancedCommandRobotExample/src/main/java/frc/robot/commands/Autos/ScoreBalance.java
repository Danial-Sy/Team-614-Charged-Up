// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PIDCommand.TiltPIDCommand;
import frc.robot.commands.PathPlannerCommands.EventMap;
import frc.robot.commands.PathPlannerCommands.PathPlannerLoadEventMapCommand;
import frc.robot.commands.SimpleCommands.Tilt;

public class ScoreBalance extends SequentialCommandGroup {

  public ScoreBalance() {
    addCommands(
        new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT),
        new PathPlannerLoadEventMapCommand("Score Charge Station", EventMap.ScoreBalance())
            .withTimeout(Constants.AUTO_TIMEOUT));
  }
}
