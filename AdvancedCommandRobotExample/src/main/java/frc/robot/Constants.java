package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  // GLOBAL STOP MOTOR
  public static final double MOTOR_ZERO_SPEED = 0.0;
  public static final double MOTOR_REST_LEADER = -0.05;

  // GLOBAL INVERT
  public static final int GLOBAL_INVERT = -1;

  // DRIVE TRAIN MOTORS
  public static final int DRIVETRAIN_FOLLOWER_RIGHT_MOTOR = 1;
  public static final int DRIVETRAIN_LEADER_RIGHT_MOTOR = 3;
  public static final int DRIVETRAIN_FOLLOWER_LEFT_MOTOR = 14;
  public static final int DRIVETRAIN_LEADER_LEFT_MOTOR = 13;
  public static final int MOTOR_CURRENT_LIMIT = 40;

  // INTAKE MOTORS
  public static final int INTAKE_MOTOR = 12;

  // Xbox Controller
  public static final int DRIVER_CONTROLLER_PORT = 0;

  // Xbox Controller Buttons
  public static final int X_BUTTON = 3;
  public static final int Y_BUTTON = 4;
  public static final int LEFT_BUMPER = 5;
  public static final int RIGHT_BUMPER = 6;
  public static final int LEADER_BUTTON = 7;
  public static final int START_BUTTON = 8;
  public static final int LEFT_STICK_PRESS = 9;
  public static final int RIGHT_STICK_PRESS = 10;

  // Arcade Drive Commands
  public static final double ARCADE_DRIVE_MULTIPLIER = 0.5;
  public static final int POW_VALUE = 3;

  // Manipulator Commands
  public static final double INTAKE_SPEED_FORWARD = 1;
  public static final double INTAKE_SPEED_LEADERWARD = -1;

  // Elevator Commands
  public static final double ELEVATOR_UP_SPEED = 0.5;
  public static final double ELEVATOR_DOWN_SPEED = -0.5;
  public static final int ELEVATOR_CURRENT_LIMIT = 40;

  // Elevator PID Setpoints
  public static final double ELEVATOR_SETPOINT = 10;
  public static final double ELEVATOR_SETPOINT2 = 0;

  // Manipulator PID setpoints (test)
  public static final double MANIPULATOR_SETPOINT = -30; // for testing
  public static final double MANIPULATOR_SETPOINT2 = 25;

  // Thresholds
  public static final double MANIPULATOR_THRESHOLD = 5;

  // Tilt Commands
  public static final double TILT_UP_SPEED = 0.5;
  public static final double TILT_DOWN_SPEED = -0.5;
  public static final double TILT_DOWN_SETPOINT = 0;
  public static final double TILT_UP_SETPOINT = 5;

  // Position-based PID Values
  public static final double P_kP = 44.251;
  public static final double P_kI = 0;
  public static final double P_kD = 3.617;

  // Velocity-based PID Values
  public static final double V_kP = 0.16375;
  public static final double V_kI = 0;
  public static final double V_kD = 0;

  // IDK
  public static final double kRamseteB = 2;
  public static final double kRamseteZeta = 0.7;

  // Timer Based Auto Variables
  public static final double RUN_INITAL_AUTO = 2.0;
  public static final double AUTO_STAGE_2 = 7.0;
  public static final double AUTO_STAGE_3 = 10.0;
  public static final double AUTO_STAGE_4 = 11.0;
  public static final double AUTO_DRIVE_SPEED = 0.5;
  public static final double AUTO_REVERSE_SPEED = -0.5;
  public static final double AUTO_ROTATE_SPEED = 0.0;

  // ELEVATOR MOTOR ID'S
  public static final int ELEVATOR_RIGHT_MOTOR = 50; // 2
  public static final int ELEVATOR_LEFT_MOTOR = 2; // 13

  // TILT MOTOR ID'S
  public static final int TILT_RIGHT_MOTOR = 51;
  public static final int TILT_LEFT_MOTOR = 53;

  public static double kvVoltSecondsPerMeter;
  public static double kaVoltSecondsSquaredPerMeter;
  public static final double kTrackWidthMeters = Units.inchesToMeters(20);
  public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackWidthMeters);
public static final double kPDriveVel = 0;
  public static double ksVolts;

  public static class OperatorConstants {
    // public static final int kDriverControllerPort = 0;
  }
}