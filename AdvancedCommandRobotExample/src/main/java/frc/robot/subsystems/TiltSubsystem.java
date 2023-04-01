package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TiltSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class TiltSubsystem extends PIDSubsystem {
  CANSparkMax tiltRightMotor = new CANSparkMax(Constants.TILT_RIGHT_MOTOR, MotorType.kBrushless);
  CANSparkMax tiltLeftMotor = new CANSparkMax(Constants.TILT_LEFT_MOTOR, MotorType.kBrushless);

  public TiltSubsystem() {
    super(
        // The controller that the command will use
        new PIDController(Constants.Pivot_kP, Constants.Pivot_kI, Constants.Pivot_kD));
    getController().setTolerance(Constants.PID_POSITION_THRESHOLD);
    tiltLeftMotor.setSmartCurrentLimit(Constants.MOTOR_CURRENT_LIMIT);
    tiltRightMotor.setSmartCurrentLimit(Constants.MOTOR_CURRENT_LIMIT);
  }

  public double getRightHeight() {
    // SmartDashboard.putNumber("Position is",
    // tiltRightMotor.getEncoder().getPosition());
    return Math.abs(tiltRightMotor.getEncoder().getPosition());
  }

  public double getLeftHeight() {
    // SmartDashboard.putNumber("Position is",
    // tiltLeftMotor.getEncoder().getPosition());
    return Math.abs(tiltLeftMotor.getEncoder().getPosition());
  }

  public double getSpeed() {
    return tiltLeftMotor.get();
  }

  public void resetTiltEncoders() {
    tiltLeftMotor.getEncoder().setPosition(Constants.MOTOR_ZERO_SPEED);
    tiltRightMotor.getEncoder().setPosition(Constants.MOTOR_ZERO_SPEED);
  }

  public void resetTiltEncodersLow(){
    tiltLeftMotor.getEncoder().setPosition(Constants.TILT_LOW_SETPOINT);
    tiltRightMotor.getEncoder().setPosition(Constants.TILT_LOW_SETPOINT);
  }

  public void set(double val) {
    tiltLeftMotor.set(val);
    tiltRightMotor.set(Constants.NEGATE_NUMBER * val);
  }

  @Override
  protected void useOutput(double output, double setpoint) {
    if ((getLeftHeight() > Constants.TILT_HYBRID_LOWER_THRESHOLD) && (setpoint > Constants.TILT_HYBRID_THRESHOLD)) {
      tiltLeftMotor.set(Constants.MOTOR_ZERO_SPEED);
      tiltRightMotor.set(Constants.MOTOR_ZERO_SPEED);
    } else {
      tiltLeftMotor.set(output + getController().calculate(getMeasurement(), setpoint));
      tiltRightMotor.set(output + getController().calculate(getMeasurement(), setpoint));
    }
  }

  @Override
  protected double getMeasurement() {
    return RobotContainer.tiltSubsystem.getLeftHeight();
  }

  public boolean atSetpoint() {
    return getController().atSetpoint();
  }
}
