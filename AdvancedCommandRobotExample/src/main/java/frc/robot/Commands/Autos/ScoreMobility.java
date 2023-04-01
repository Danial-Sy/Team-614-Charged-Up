package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathPlannerCommands.PathPlannerLoadPathCommand;
import frc.robot.commands.SimpleCommands.Tilt;

public class ScoreMobility extends SequentialCommandGroup {
  public ScoreMobility() {
    addCommands(
        new Tilt(Constants.TILT_DEFAULT_SETPOINT),
        new PathPlannerLoadPathCommand("pathplanner/generatedJSON/Score Mobility.wpilib.json", true).withTimeout(Constants.AUTO_TIMEOUT));
  }
}
