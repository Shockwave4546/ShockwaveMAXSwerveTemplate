package frc.shuffleboard;

import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;

import java.util.function.Supplier;

/**
 * Wraps [SparkMaxPIDController] because it isn't natively supported by Shuffleboard.
 */
public class TunableSparkMaxPIDController implements Sendable {
  private final SparkMaxPIDController child;
  private final Supplier<Double> desiredState;

  public TunableSparkMaxPIDController(SparkMaxPIDController child, Supplier<Double> desiredState) {
    this.child = child;
    this.desiredState = desiredState;
  }

  @Override public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("PIDController");
    builder.addDoubleProperty("p", child::getP, child::setP);
    builder.addDoubleProperty("i", child::getI, child::setI);
    builder.addDoubleProperty("d", child::getD, child::setD);
    builder.addDoubleProperty("f", child::getFF, child::setFF);
    // TODO: 11/12/23 This is annoying to do, but do it sometime else.
    builder.addDoubleProperty("setpoint", null, null);
    // TODO: 11/12/23 Lowkey same thing as the thing above.
    builder.addBooleanProperty("enabled", () -> true, null);
  }
}
