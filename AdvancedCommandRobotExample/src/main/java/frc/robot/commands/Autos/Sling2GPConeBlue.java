package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PIDCommand.TiltPIDCommand;
import frc.robot.commands.PathPlannerCommands.EventMap;
import frc.robot.commands.PathPlannerCommands.PathPlannerLoadEventMapCommand;

public class Sling2GPConeBlue extends SequentialCommandGroup {
  public Sling2GPConeBlue() {
    addCommands(
        new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT).withTimeout(0.1),
        new PathPlannerLoadEventMapCommand("Sling2GPConeBlue", EventMap.Sling3GPCone()).withTimeout(Constants.AUTO_TIMEOUT));
  }
}