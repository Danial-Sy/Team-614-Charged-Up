package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PIDCommand.TiltPIDCommand;
import frc.robot.commands.PathPlannerCommands.EventMap;
import frc.robot.commands.PathPlannerCommands.PathPlannerLoadEventMapCommand;

public class Sling2GPBlue extends SequentialCommandGroup {
  public Sling2GPBlue() {
    addCommands(
        new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT).withTimeout(0.1),
        new PathPlannerLoadEventMapCommand("Sling2GPBlue", EventMap.Sling3GP()).withTimeout(Constants.AUTO_TIMEOUT));
  }
}