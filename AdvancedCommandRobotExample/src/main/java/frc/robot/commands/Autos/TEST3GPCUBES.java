package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PIDCommand.TiltPIDCommand;
import frc.robot.commands.PathPlannerCommands.EventMap;
import frc.robot.commands.PathPlannerCommands.PathPlannerLoadEventMapCommand;
public class TEST3GPCUBES extends SequentialCommandGroup {
  public TEST3GPCUBES() {
    addCommands(
        new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT).withTimeout(0.1),
        new PathPlannerLoadEventMapCommand("TEST3GPCUBES", EventMap.TEST3GPCUBES()).withTimeout(Constants.AUTO_TIMEOUT));
  }
}