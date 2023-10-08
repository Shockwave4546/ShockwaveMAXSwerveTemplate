package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.shuffleboard.GlobalTab;

public class OneWheelSwerve extends SubsystemBase {
  private final MAXSwerveModule module = new MAXSwerveModule(
      7,
      5,
      0);

  public OneWheelSwerve() {
    GlobalTab.DEBUG.addNumber("Angle", () -> module.getPosition().angle.getDegrees());
  }

  public void drive(double xSpeed, double ySpeed, double rot) {

    double xSpeedCommanded;
    double ySpeedCommanded;
    double m_currentRotation;

      xSpeedCommanded = xSpeed;
      ySpeedCommanded = ySpeed;
      m_currentRotation = rot;


    // Convert the commanded speeds into the correct units for the drivetrain
    double xSpeedDelivered = xSpeedCommanded * Constants.DriveConstants.MAX_SPEED_METERS_PER_SECOND;
    double ySpeedDelivered = ySpeedCommanded * Constants.DriveConstants.MAX_SPEED_METERS_PER_SECOND;
    double rotDelivered = m_currentRotation * Constants.DriveConstants.MAX_ANGULAR_SPEED;

    var swerveModuleStates = Constants.DriveConstants.DRIVE_KINEMATICS.toSwerveModuleStates(new ChassisSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered));
    SwerveDriveKinematics.desaturateWheelSpeeds(
        swerveModuleStates, Constants.DriveConstants.MAX_SPEED_METERS_PER_SECOND);
    module.setDesiredState(swerveModuleStates[0]);
  }

  public void setAngleDegrees(double angle) {
    module.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(angle)));
  }
}
