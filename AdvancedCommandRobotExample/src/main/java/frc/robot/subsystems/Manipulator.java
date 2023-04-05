package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.SimpleCommands.Intake;
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
  public int count;

  public Manipulator() {
    intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR, MotorType.kBrushless);
    pdh = new PowerDistribution();
    timer = new Timer();
    count = 0;

    intakeMotor.setSmartCurrentLimit(Constants.MOTOR_CURRENT_LIMIT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(Intake.commandActivated){
    if(getEncoderVelocity() > -1 && getEncoderVelocity() < 1){
      count++;
    }
    else {
      count = 0;
    }
    if (count >= 2){
      RobotContainer.ledSubsystem.setLedColorRainbow();
    }
  }
    // if(timer.get() >= 5 && pdh.getCurrent(Constants.INTAKE_MOTOR) > Constants.PDH_CURRENT_THRESHOLD){
    //   RobotContainer.ledSubsystem.setLedColorGreen();
    // }
  }

  // // Returns rate of motor
  public double getSpeed() {
    return intakeMotor.get();
  }

  public double getEncoderVelocity(){
    return intakeMotor.getEncoder().getVelocity();
  }
  // // Speed of motor
  public void set(double val) {
    intakeMotor.set(val);
    SmartDashboard.putNumber("Motor Speed", intakeMotor.get());

  }
}