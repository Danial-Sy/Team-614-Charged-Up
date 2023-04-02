package frc.robot.commands.PathPlannerCommands;

import java.util.HashMap;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.PIDCommand.TiltPIDCommand;
import frc.robot.commands.SequentialParallelCommands.GroundIntake;
import frc.robot.commands.SequentialParallelCommands.ScoreHybrid;
import frc.robot.commands.SequentialParallelCommands.ScoreMidCone;
import frc.robot.commands.SequentialParallelCommands.ScoreMidCubeAuto;

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

  public static HashMap<String, Command> Sling3GP() {
    HashMap<String, Command> Sling3GP = new HashMap<>();
    Sling3GP.put("stow", new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT));
    Sling3GP.put("intake", new GroundIntake());
    Sling3GP.put("armup", new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT));
    Sling3GP.put("scoremid", new ScoreMidCubeAuto());
    Sling3GP.put("intake1", new GroundIntake());
    Sling3GP.put("armup1", new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT));
    Sling3GP.put("scorelow", new ScoreHybrid());
    return Sling3GP();
  }
}
