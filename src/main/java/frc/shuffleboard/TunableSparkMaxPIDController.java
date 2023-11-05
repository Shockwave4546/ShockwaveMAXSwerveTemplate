package frc.shuffleboard;

import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;

/**
 * Wraps [SparkMaxPIDController] because it isn't natively supported by Shuffleboard.
 */
public class TunableSparkMaxPIDController implements Sendable {
  private final SparkMaxPIDController child;

  public TunableSparkMaxPIDController(SparkMaxPIDController child) {
    this.child = child;
  }

  @Override public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("P", child::getP, child::setP);
    builder.addDoubleProperty("I", child::getI, child::setI);
    builder.addDoubleProperty("D", child::getD, child::setD);
    builder.addDoubleProperty("FF", child::getFF, child::setFF);
    builder.addDoubleProperty("Min Output", child::getOutputMin, null);
    builder.addDoubleProperty("Max Output", child::getOutputMax, null);
  }
}
