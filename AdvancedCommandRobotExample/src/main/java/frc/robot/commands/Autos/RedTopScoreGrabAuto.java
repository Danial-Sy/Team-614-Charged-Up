package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathPlannerCommands.EventMap;
import frc.robot.commands.PathPlannerCommands.PathPlannerLoadEventMapCommand;
import frc.robot.commands.SimpleCommands.Tilt;

public class RedTopScoreGrabAuto extends SequentialCommandGroup {
  public RedTopScoreGrabAuto() {
    addCommands(
        new Tilt(Constants.TILT_DEFAULT_SETPOINT),
        new PathPlannerLoadEventMapCommand("TopRedScoreGrab", EventMap.ScoreGrab())
            .withTimeout(Constants.AUTO_TIMEOUT));
  }
}
