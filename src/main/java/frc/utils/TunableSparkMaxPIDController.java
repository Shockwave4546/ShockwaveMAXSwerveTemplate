package frc.utils;

import com.revrobotics.SparkMaxPIDController;

public class TunableSparkMaxPIDController {
  private final SparkMaxPIDController child;

  public TunableSparkMaxPIDController(SparkMaxPIDController child) {
    this.child = child;
  }

  
}
