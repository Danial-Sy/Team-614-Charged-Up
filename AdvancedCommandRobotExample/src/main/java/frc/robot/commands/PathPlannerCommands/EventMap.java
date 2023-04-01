package frc.robot.commands.PathPlannerCommands;

import java.util.HashMap;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.PIDCommand.TiltPIDCommand;
import frc.robot.commands.SequentialParallelCommands.GroundIntake;
import frc.robot.commands.SequentialParallelCommands.ScoreMidCone;

public class EventMap {

  public EventMap() {
  }

  public static HashMap<String, Command> ScoreGrab() {
    HashMap<String, Command> ScoreGrab = new HashMap<>();
    ScoreGrab.put("intake", new GroundIntake());
    ScoreGrab.put("armup", new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT));
    ScoreGrab.put("score2", new ScoreMidCone());
    return ScoreGrab;
  }

  public static HashMap<String, Command> ScoreBalance() {
    HashMap<String, Command> ScoreBalance = new HashMap<>();
    ScoreBalance.put("balance", new AutoBalance());
    return ScoreBalance;
  }
}
