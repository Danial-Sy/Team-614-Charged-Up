package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PIDCommand.TiltPIDCommand;
import frc.robot.commands.PathPlannerCommands.EventMap;
import frc.robot.commands.PathPlannerCommands.PathPlannerLoadEventMapCommand;
import frc.robot.commands.SimpleCommands.Tilt;

public class Sling3GP extends SequentialCommandGroup {
  public Sling3GP() {
    addCommands(
        new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT),
        new PathPlannerLoadEventMapCommand("Sling3GP", EventMap.Sling3GP()).withTimeout(Constants.AUTO_TIMEOUT));
  }
}