package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.pivotEncoderResetLow;
import frc.robot.commands.Autos.Sling2GPRed;
import frc.robot.commands.Autos.Sling2GPBlue;
import frc.robot.commands.Autos.Sling2GPConeRed;
import frc.robot.commands.Autos.RedScoreGrabAuto;
import frc.robot.commands.Autos.BlueScoreGrabAuto;
import frc.robot.commands.Autos.ScoreBalance;
import frc.robot.commands.Autos.ScoreMobility;
import frc.robot.commands.Autos.Sling2GPConeBlue;
import frc.robot.commands.Autos.MidCubeConeBalance;
import frc.robot.commands.Autos.MidHighCubeBalance;
import frc.robot.commands.Autos.MidCubeConeBalance25;
import frc.robot.commands.Autos.MidHighCubeBalance25;
import frc.robot.commands.Autos.DoNothingAuto;
import frc.robot.commands.PIDCommand.TiltPIDCommand;
import frc.robot.commands.SequentialParallelCommands.GroundIntake;
import frc.robot.commands.SequentialParallelCommands.LoadStation;
import frc.robot.commands.SequentialParallelCommands.PchooOverCS;
import frc.robot.commands.SequentialParallelCommands.ScoreHighCube;
import frc.robot.commands.SequentialParallelCommands.ScoreHybrid;
import frc.robot.commands.SequentialParallelCommands.ScoreMidCone;
import frc.robot.commands.SequentialParallelCommands.ScoreMidCube;
import frc.robot.commands.SimpleCommands.ArcadeDrive;
import frc.robot.commands.SimpleCommands.Extend;
import frc.robot.commands.SimpleCommands.MaxTiltDown;
import frc.robot.commands.SimpleCommands.MaxTiltUp;
import frc.robot.commands.SimpleCommands.Retract;
import frc.robot.commands.SimpleCommands.SetLEDColorCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.Manipulator;
import frc.robot.subsystems.TiltSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotContainer {
    public static DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
    public static CommandXboxController m_CommandXboxController = new CommandXboxController(
            Constants.DRIVER_CONTROLLER_PORT);
    public static CommandXboxController co_CommandXboxController = new CommandXboxController(
            Constants.CO_DRIVER_CONTROLLER_PORT);

    public static Manipulator manipulator = new Manipulator();
    public static ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
    public static TiltSubsystem tiltSubsystem = new TiltSubsystem();
    // public static LimelightSubsystem limelightSubsystem = new
    // LimelightSubsystem();
    public static LEDSubsystem ledSubsystem = new LEDSubsystem();

    private final Command Sling2GPRed = new Sling2GPRed();
    private final Command Sling2GPBlue = new Sling2GPBlue();
    private final Command Sling2GPConeRed = new Sling2GPConeRed();
    private final Command Sling2GPConeBlue = new Sling2GPConeBlue();
    private final Command DoNothing = new DoNothingAuto();
    private final Command RedScoreGrabAuto = new RedScoreGrabAuto();
    private final Command BlueScoreGrabAuto = new BlueScoreGrabAuto();
    private final Command ScoreBalance = new ScoreBalance();
    private final Command ScoreMobility = new ScoreMobility();
    private final Command MidCubeConeBalance = new MidCubeConeBalance();
    private final Command MidHighCubeBalance = new MidHighCubeBalance();
    private final Command MidCubeConeBalance25 = new MidCubeConeBalance25();
    private final Command MidHighCubeBalance25 = new MidHighCubeBalance25();

    SendableChooser<Command> m_chooser = new SendableChooser<>();

    public RobotContainer() {
        configureBindings();
        driveTrainSubsystem.setDefaultCommand(new ArcadeDrive());

        m_chooser.addOption("Blue - Score Grab", BlueScoreGrabAuto);
        m_chooser.addOption("Red - Score Grab", RedScoreGrabAuto);
        m_chooser.addOption("Score Balance", ScoreBalance);
        m_chooser.addOption("Score Mobility", ScoreMobility);
        m_chooser.addOption("Mid Cube Cone Balance", MidCubeConeBalance);
        m_chooser.addOption("Mid High Cube Balance", MidHighCubeBalance);
        m_chooser.addOption("Mid Cube Cone Balance 2.5", MidCubeConeBalance25);
        m_chooser.addOption("Mid High Cube Balance 2.5", MidHighCubeBalance25);
        m_chooser.addOption("Sling2GPRed", Sling2GPRed); 
        m_chooser.addOption("Sling2GPBlue", Sling2GPBlue);
        m_chooser.addOption("Sling2GPConeBlue", Sling2GPConeBlue);  
        m_chooser.addOption("Sling2GPConeRed", Sling2GPConeRed); 

        m_chooser.setDefaultOption("Do Nothing", DoNothing);

        SmartDashboard.putData(m_chooser);
    }

    private void configureBindings() {
        // MAIN DRIVER CONTROLLER BINDS
        m_CommandXboxController.button(Constants.A_BUTTON).onTrue(new ScoreHybrid());
        m_CommandXboxController.button(Constants.Y_BUTTON).onTrue(new ScoreHighCube());
        m_CommandXboxController.button(Constants.X_BUTTON).onTrue(new ScoreMidCube());
        m_CommandXboxController.button(Constants.B_BUTTON).onTrue(new ScoreMidCone());
        m_CommandXboxController.button(Constants.RIGHT_BUMPER).onTrue(new LoadStation());
        m_CommandXboxController.button(Constants.LEFT_BUMPER).onTrue(new GroundIntake());
        m_CommandXboxController.rightTrigger().onTrue(new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT));
        m_CommandXboxController.leftTrigger().onTrue(new PchooOverCS());
        m_CommandXboxController.povUp().whileTrue(new pivotEncoderResetLow());
        m_CommandXboxController.button(Constants.START_BUTTON).whileTrue(new SetLEDColorCommand(0)); // Sets LED's to
                                                                                                        // purple
        m_CommandXboxController.button(Constants.BACK_BUTTON).whileTrue(new SetLEDColorCommand(1)); // Sets LED's to
                                                                                                       // yellow

        // // CO-DRIVER CONTROLLER BINDS
        co_CommandXboxController.button(Constants.A_BUTTON).onTrue(new ScoreHybrid());
        co_CommandXboxController.button(Constants.Y_BUTTON).onTrue(new ScoreHighCube());
        co_CommandXboxController.button(Constants.X_BUTTON).onTrue(new ScoreMidCube());
        co_CommandXboxController.button(Constants.B_BUTTON).onTrue(new ScoreMidCone());
        co_CommandXboxController.button(Constants.RIGHT_BUMPER).onTrue(new LoadStation());
        co_CommandXboxController.button(Constants.LEFT_BUMPER).onTrue(new GroundIntake());
        co_CommandXboxController.rightTrigger().onTrue(new TiltPIDCommand(Constants.TILT_DEFAULT_SETPOINT));
        co_CommandXboxController.leftTrigger().onTrue(new PchooOverCS());
        co_CommandXboxController.axisGreaterThan(1, 0.5).whileTrue(new Retract());
        co_CommandXboxController.axisLessThan(1, -0.5).whileTrue(new Extend());
        co_CommandXboxController.axisGreaterThan(5, 0.5).whileTrue(new MaxTiltDown());
        co_CommandXboxController.axisLessThan(5, -0.5).whileTrue(new MaxTiltUp());
        co_CommandXboxController.povUp().whileTrue(new pivotEncoderResetLow());
        co_CommandXboxController.button(Constants.START_BUTTON).whileTrue(new SetLEDColorCommand(0)); // Sets LED's
                                                                                                         // to purple
        co_CommandXboxController.button(Constants.BACK_BUTTON).whileTrue(new SetLEDColorCommand(1)); // Sets LED's to
                                                                                                        // yellow
    }

    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
    }
}
