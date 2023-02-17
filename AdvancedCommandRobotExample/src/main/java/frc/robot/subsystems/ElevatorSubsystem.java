package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.ElevatorSubsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ElevatorSubsystem extends SubsystemBase {

  CANSparkMax elevatorRightMotor = null;
  CANSparkMax elevatorLeftMotor = null;

  public ElevatorSubsystem() {
    // elevatorRightMotor = new CANSparkMax(Constants.ELEVATOR_RIGHT_MOTOR,
    // MotorType.kBrushless);
    elevatorLeftMotor = new CANSparkMax(Constants.ELEVATOR_LEFT_MOTOR, MotorType.kBrushless);

    // elevatorRightMotor.follow(elevatorLeftMotor, false); //important make sure to
    // test out
    // elevatorRightMotor.setSmartCurrentLimit(Constants.MOTOR_CURRENT_LIMIT);
    elevatorLeftMotor.setSmartCurrentLimit(Constants.MOTOR_CURRENT_LIMIT);
  }

  public void periodic() {
    // Called once per scheduler run
  }

  public double getHeight() {
    SmartDashboard.putNumber("Position is", elevatorLeftMotor.getEncoder().getPosition());
    return elevatorLeftMotor.getEncoder().getPosition();
  }
  public double getInches(){
    double motorRevolutions = elevatorLeftMotor.getEncoder().getPosition()/Constants.TICKS_PER_REV;
    double gearBoxOutputRevolutions = motorRevolutions * Constants.GEAR_BOX_RATIO;
    double inchesTraveled = gearBoxOutputRevolutions * Constants.M_PI * Constants.WHEEL_DIAMETER;
    return inchesTraveled;
  }
  public void set(double val){
    elevatorLeftMotor.set(val);
  }
}