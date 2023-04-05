package frc.robot.commands.PathPlannerCommands;

import java.util.HashMap;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.Constants;
import frc.robot.commands.PIDCommand.TiltPIDCommand;
import frc.robot.commands.SequentialParallelCommands.GroundIntake;
import frc.robot.commands.SequentialParallelCommands.PchooOverCS;
import frc.robot.commands.SequentialParallelCommands.ScoreHybrid;
import frc.robot.commands.SequentialParallelCommands.ScoreMidCone;
import frc.robot.commands.SequentialParallelCommands.ScoreMidConeAuto;
import frc.robot.commands.SequentialParallelCommands.ScoreMidCube;
import frc.robot.commands.SequentialParallelCommands.ScoreMidCubeAuto;

public class EventMap {

  public EventMap() {
  }

  public static HashMap<String, Command> ScoreGrab() {
    HashMap<String, Command> ScoreGrab = new HashMap<>();
    ScoreGrab.put("intake", new GroundIntake());
    ScoreGrab.put("armup", new TiltPIDCommand(Constants.TILT_MID_SCORE_SETPOINT));
    ScoreGrab.put("score2", new ScoreMidCone());
    return ScoreGrab;
  }

  public static HashMap<String, Command> ScoreGrabScoreConeBalance() {
    HashMap<String, Command> ScoreGrabScoreConeBalance = new HashMap<>();
    ScoreGrabScoreConeBalance.put("intake", new GroundIntake());
    ScoreGrabScoreConeBalance.put("armup", new TiltPIDCommand(Constants.TILT_MID_SCORE_SETPOINT));
    ScoreGrabScoreConeBalance.put("score2", new ScoreMidCone());
    ScoreGrabScoreConeBalance.put("balance", new AutoBalance());
    return ScoreGrabScoreConeBalance;
  }

  public static HashMap<String, Command> ScoreGrabScoreCubeBalance() {
    HashMap<String, Command> ScoreGrabScoreCubeBalance = new HashMap<>();
    ScoreGrabScoreCubeBalance.put("intake", new GroundIntake());
    ScoreGrabScoreCubeBalance.put("armup", new TiltPIDCommand(Constants.TILT_MID_SCORE_SETPOINT));
    ScoreGrabScoreCubeBalance.put("score2", new ScoreMidCube());
    ScoreGrabScoreCubeBalance.put("balance", new AutoBalance());
    return ScoreGrabScoreCubeBalance;
  }

  public static HashMap<String, Command> MidCubeConeBalance25() {
    HashMap<String, Command> MidCubeConeBalance25 = new HashMap<>();
    MidCubeConeBalance25.put("intake", new GroundIntake());
    MidCubeConeBalance25.put("armup", new TiltPIDCommand(Constants.TILT_MID_SCORE_SETPOINT));
    MidCubeConeBalance25.put("score2", new ScoreMidCone());
    MidCubeConeBalance25.put("pchoo", new PchooOverCS());
    MidCubeConeBalance25.put("balance", new AutoBalance());
    return MidCubeConeBalance25;
  }
  
  public static HashMap<String, Command> MidHighCubeBalance25() {
    HashMap<String, Command> MidHighCubeBalance25 = new HashMap<>();
    MidHighCubeBalance25.put("intake", new GroundIntake());
    MidHighCubeBalance25.put("armup", new TiltPIDCommand(Constants.TILT_MID_SCORE_SETPOINT));
    MidHighCubeBalance25.put("score", new ScoreMidCube());
    MidHighCubeBalance25.put("pchoo", new PchooOverCS());
    MidHighCubeBalance25.put("balance", new AutoBalance());
    return MidHighCubeBalance25;
  }

  public static HashMap<String, Command> ScoreBalance() {
    HashMap<String, Command> ScoreBalance = new HashMap<>();
    ScoreBalance.put("balance", new AutoBalance());
    return ScoreBalance;
  }

  public static HashMap<String, Command> Sling3GP() {
    HashMap<String, Command> Sling3GP = new HashMap<>();
    Sling3GP.put("intake", new GroundIntake());
    Sling3GP.put("armup", new TiltPIDCommand(Constants.TILT_MID_SCORE_SETPOINT));
    Sling3GP.put("scoremid", new ScoreMidCubeAuto());
    return Sling3GP;
  }

  public static HashMap<String, Command> Sling3GPCone() {
    HashMap<String, Command> Sling3GPCone = new HashMap<>();
    Sling3GPCone.put("intake", new GroundIntake());
    Sling3GPCone.put("armup", new TiltPIDCommand(Constants.TILT_MID_SCORE_SETPOINT));
    Sling3GPCone.put("scoremid", new ScoreMidConeAuto());
    return Sling3GPCone;
  }
}
