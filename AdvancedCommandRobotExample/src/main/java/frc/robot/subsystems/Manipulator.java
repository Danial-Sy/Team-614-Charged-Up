package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Manipulator;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Manipulator extends SubsystemBase {
  /* Creates a new intake subsystem */
  public PowerDistribution pdh;
  CANSparkMax intakeMotor;
  Timer timer;

  public Manipulator() {
    intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR, MotorType.kBrushless);
    pdh = new PowerDistribution();
    timer = new Timer();

    intakeMotor.setSmartCurrentLimit(Constants.MOTOR_CURRENT_LIMIT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(timer.get() >= 3 && getAverage() >= pdh.getCurrent(Constants.INTAKE_MOTOR)){
      RobotContainer.ledSubsystem.setLedColorGreen();
    }
    // if(timer.get() >= 5 && pdh.getCurrent(Constants.INTAKE_MOTOR) > Constants.PDH_CURRENT_THRESHOLD){
    //   RobotContainer.ledSubsystem.setLedColorGreen();
    // }
  }

  // // Returns rate of motor
  public double getSpeed() {
    return intakeMotor.get();
  }

  public double getAverage(){
    int count = 0;
    double sum = 0;
    if (pdh.getCurrent(Constants.INTAKE_MOTOR) < Constants.PDH_CURRENT_THRESHOLD){
      count = 0;
      sum = 0;
    }
  while (pdh.getCurrent(Constants.INTAKE_MOTOR) > Constants.PDH_CURRENT_THRESHOLD && count < 20){
    sum += pdh.getCurrent(Constants.INTAKE_MOTOR);
    count ++;
  }
  return sum/count;
  }

  // // Speed of motor
  public void set(double val) {
    intakeMotor.set(val);
    SmartDashboard.putNumber("Motor Speed", intakeMotor.get());

  }
}