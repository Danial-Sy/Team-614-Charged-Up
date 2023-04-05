package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PIDCommand.TiltPIDCommand;
import frc.robot.commands.PathPlannerCommands.EventMap;
import frc.robot.commands.PathPlannerCommands.PathPlannerLoadEventMapCommand;

public class MidHighCubeBalance extends SequentialCommandGroup {

  public MidHighCubeBalance() {
    addCommands(
      new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT),
      new PathPlannerLoadEventMapCommand("MidHighCubeBalance", EventMap.ScoreGrabScoreCubeBalance())
    );
  }
}
