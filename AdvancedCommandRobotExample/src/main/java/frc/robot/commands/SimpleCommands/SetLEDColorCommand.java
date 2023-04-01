package frc.robot.commands.SimpleCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class SetLEDColorCommand extends CommandBase {

  private final int m_color;

  public SetLEDColorCommand(int color) {
    m_color = color;

    addRequirements(RobotContainer.ledSubsystem);
  }

  @Override
  public void execute() {
    if (m_color == Constants.ID_0) {
      RobotContainer.ledSubsystem.setLedColorPurple();
    } else if (m_color == Constants.ID_1) {
      RobotContainer.ledSubsystem.setLedColorYellow();
    } else if (m_color == Constants.ID_2) {
      RobotContainer.ledSubsystem.setLedColorGreen();
    } else if (m_color == Constants.ID_3) {
      RobotContainer.ledSubsystem.setLedColorOrange();
    } else
      RobotContainer.ledSubsystem.setLedColorRainbow();
  }
}
