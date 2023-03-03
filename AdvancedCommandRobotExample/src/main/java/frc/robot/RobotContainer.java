package frc.robot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.PIDCommand.ElevatorPIDCommand;
import frc.robot.commands.PIDCommand.TiltPID;
// import frc.robot.commands.ManipulatorDefaultCommand;
import frc.robot.commands.SetLEDColorCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.Manipulator;
import frc.robot.subsystems.TiltSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.commands.PIDCommand.ElevatorPIDCommand;
// import frc.robot.commands.PIDCommand.TiltPID;
import frc.robot.commands.SimpleCommands.Extend;
import frc.robot.commands.SimpleCommands.Intake;
import frc.robot.commands.SimpleCommands.MaxTiltUp;
import frc.robot.commands.SimpleCommands.Retract;
import frc.robot.commands.SimpleCommands.TiltHold;
import frc.robot.commands.SimpleCommands.MaxTiltDown;
import frc.robot.commands.Autonomous.TimedBasedAuto.TimedCommands.WaitCommand;

public class RobotContainer {
    // Subsystem Initalization
    public static DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
    public static Timer autoTimer;
    public static CommandXboxController m_CommandXboxController = new CommandXboxController(
            Constants.DRIVER_CONTROLLER_PORT);
    public static CommandXboxController co_CommandXboxController = new CommandXboxController(
            Constants.CO_DRIVER_CONTROLLER_PORT);
    public static Manipulator manipulator = new Manipulator();
    public static ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
    public static TiltSubsystem tiltSubsystem = new TiltSubsystem();
    public static LimelightSubsystem limelightSubsystem = new LimelightSubsystem();
    public static LEDSubsystem ledSubsystem = new LEDSubsystem();
    SendableChooser<Command> m_chooser = new SendableChooser<>();
    HashMap<String, Command> eventMap = new HashMap<>();

    // private final Command TestAuto = new TestAuto();

    public RobotContainer() {
        configureBindings();
        // Sets the default command for drivetrain subsystem
        driveTrainSubsystem.setDefaultCommand(new ArcadeDrive());
        // manipulator.setDefaultCommand(new
        // ManipulatorDefaultCommand(RobotContainer.manipulator));

        // Add commands to the auto chooser
        // m_chooser.setDefaultOption("Test Auto", TestAuto);
        m_chooser.addOption("Circle",
                loadPathplannerTrajectoryToRamseteCommand("pathplanner/generatedJSON/Circle Path.wpilib.json", true));
        m_chooser.addOption("Straight Path",
                loadPathplannerTrajectoryToRamseteCommand("pathplanner/generatedJSON/Straight-Path.wpilib.json", true));
        m_chooser.addOption("Curly Wirly",
                loadPathplannerTrajectoryToRamseteCommand("pathplanner/generatedJSON/S-Curve-Path.wpilib.json", true));
        m_chooser.addOption("Path Path Path",
                loadPathplannerTrajectoryToRamseteCommand("pathplanner/generatedJSON/New New Path.wpilib.json", true));
        m_chooser.addOption("3 meter",
                loadPathplannerTrajectoryToRamseteCommand("pathplanner/generatedJSON/New New New Path.wpilib.json",
                        true));
        m_chooser.addOption("Straight Path 2",
                loadPathplannerTrajectoryToRamseteCommand("pathplanner/generatedJSON/Forwardandright.wpilib.json",
                        true));
        m_chooser.addOption("Forward and reverse",
                loadPathplannerTrajectoryToRamseteCommand("pathplanner/generatedJSON/New Path.wpilib.json", true));
        m_chooser.addOption("Candy Cane Path",
                loadPathplannerTrajectoryToRamseteCommand("pathplanner/generatedJSON/Candy Cane Path.wpilib.json",
                        true));
        m_chooser.addOption("score grab charge",
                loadPathplannerTrajectoryToRamseteCommand("pathplanner/generatedJSON/Score Grab Charge.wpilib.json",
                        true));
        m_chooser.addOption("test marker path",
                loadPathplannerTrajectoryToRamseteCommand("pathplanner/generatedJSON/inch.wpilib.json", true));

        SmartDashboard.putData(m_chooser);
    }

    public Command loadPathplannerTrajectoryToRamseteCommand(String filename, boolean resetOdomtry) {
        Trajectory trajectory;
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(filename);
            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException exception) {
            DriverStation.reportError("Unable to open trajectory" + filename, exception.getStackTrace());
            System.out.println("Unable to read from file " + filename);
            return new InstantCommand();
        }
        RamseteCommand ramseteCommand = new RamseteCommand(trajectory,
                driveTrainSubsystem::getPose,
                new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
                new SimpleMotorFeedforward(Constants.ksVolts,
                        Constants.kvVoltSecondsPerMeter,
                        Constants.kaVoltSecondsSquaredPerMeter),
                Constants.kDriveKinematics, driveTrainSubsystem::getWheelSpeeds,
                new PIDController(Constants.V_kP, 0, 0),
                new PIDController(Constants.V_kP, 0, 0),
                driveTrainSubsystem::DifferentialDriveVolts,
                driveTrainSubsystem);

        if (resetOdomtry) {
            return new SequentialCommandGroup(
                    new InstantCommand(() -> driveTrainSubsystem.resetOdometry(trajectory.getInitialPose())),
                    ramseteCommand);
        } else {
            return ramseteCommand;
        }
    }

    private void configureBindings() {
        // MAIN DRIVER CONTROLLER BINDS
        m_CommandXboxController.button(Constants.LEFT_BUMPER).onTrue(new Intake(Constants.MANIPULATOR_SPEED_INTAKE));
        m_CommandXboxController.button(Constants.RIGHT_BUMPER).whileTrue(new Intake(Constants.MANIPULATOR_SPEED_OUTTAKE));
        m_CommandXboxController.button(Constants.A_BUTTON).whileTrue(new Intake(Constants.MANIPULATOR_SPEED_BLEH));
        m_CommandXboxController.button(Constants.BACK_BUTTON).onTrue(new TiltPID(Constants.TILT_HYBRID_SCORE_SETPOINT).withTimeout(0.5)
                        .andThen(new Intake(Constants.MANIPULATOR_SPEED_OUTTAKE).withTimeout(0.5))
                        .andThen(new TiltPID(Constants.TILT_UP_SETPOINT).withTimeout(0.7)));

        m_CommandXboxController.button(Constants.START_BUTTON).whileTrue(new Retract());
        // m_CommandXboxController.button(Constants.X_BUTTON).whileTrue(new Retract());
        // m_CommandXboxController.button(Constants.Y_BUTTON).whileTrue(new Extend());
        m_CommandXboxController.button(Constants.Y_BUTTON) // Score Mid Cube
                .onTrue(new TiltPID(Constants.TILT_LOAD_STATION_SETPOINT).withTimeout(0.5)
                        .andThen(new Extend().withTimeout(0.5))
                        .andThen(new Intake(Constants.MANIPULATOR_SPEED_OUTTAKE).withTimeout(0.2))
                        .andThen(new Retract().withTimeout(0.5)) 
                        .andThen(new TiltPID(Constants.TILT_UP_SETPOINT).withTimeout(0.5)));

        m_CommandXboxController.button(Constants.X_BUTTON) // Score High Cube
                .onTrue(new TiltPID(Constants.TILT_MID_SCORE_SETPOINT).withTimeout(0.5)
                        .andThen(new Intake(Constants.MANIPULATOR_SPEED_OUTTAKE))
                        .andThen(new TiltPID(Constants.TILT_UP_SETPOINT).withTimeout(0.5)));

        m_CommandXboxController.rightTrigger().onTrue(new TiltPID(Constants.TILT_UP_SETPOINT));
        // Score Cone
        m_CommandXboxController.leftTrigger().onTrue(new TiltPID(Constants.TILT_LOAD_STATION_SETPOINT).withTimeout(0.1)
                .andThen(new Extend().withTimeout(0.6))
                .andThen(new TiltPID(Constants.TILT_MID_SCORE_SETPOINT).withTimeout(0.11))
                .andThen(new Retract().withTimeout(0.5))
                .andThen(new TiltPID(Constants.TILT_UP_SETPOINT).withTimeout(0.1)));

        m_CommandXboxController.button(Constants.B_BUTTON).onTrue(Commands // Load from load station
                .parallel(new TiltPID(Constants.TILT_LOAD_STATION_SETPOINT),
                        new Intake(Constants.MANIPULATOR_SPEED_INTAKE)));
        // m_CommandXboxController.button(Constants.START_BUTTON).toggleOnTrue(new
        // SetLEDColorCommand(0)); Sets LED's to purple
        // m_CommandXboxController.button(Constants.BACK_BUTTON).toggleOnTrue(new
        // SetLEDColorCommand(1)); Sets LED's to yellow

        // CO-DRIVER CONTROLLER BINDS
        co_CommandXboxController.button(Constants.LEFT_BUMPER)
                .whileTrue(new Intake(Constants.MANIPULATOR_SPEED_INTAKE));
        co_CommandXboxController.button(Constants.RIGHT_BUMPER)
                .whileTrue(new Intake(Constants.MANIPULATOR_SPEED_OUTTAKE));
        co_CommandXboxController.button(Constants.X_BUTTON).whileTrue(new Retract());
        co_CommandXboxController.button(Constants.Y_BUTTON).whileTrue(new Extend());
        co_CommandXboxController.rightTrigger().whileTrue(new MaxTiltUp());
        co_CommandXboxController.leftTrigger().whileTrue(new MaxTiltDown());
        co_CommandXboxController.button(Constants.B_BUTTON).whileTrue(new TiltHold());

    }

    public Command getAutonomousCommand() {
        // Returns the selected auto command
        return m_chooser.getSelected();
        }
    }
}
