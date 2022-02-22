package TypewiseAlert;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import TypewiseAlert.TypewiseAlert.AlertTarget;
import TypewiseAlert.TypewiseAlert.CoolingType;

public class TypewiseAlertTest {

  @Test
  public void testInferBreach() {
    assertTrue(TypewiseAlert.inferBreach(12, 20, 30) == TypewiseAlert.BreachType.TOO_LOW);
    assertTrue(TypewiseAlert.inferBreach(32, 20, 30) == TypewiseAlert.BreachType.TOO_HIGH);
    assertTrue(TypewiseAlert.inferBreach(25, 20, 30) == TypewiseAlert.BreachType.NORMAL);
  }

  @Test
  public void testClassifyTemperatureBreach() {
    assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.HI_ACTIVE_COOLING,
        55) == TypewiseAlert.BreachType.TOO_HIGH);
    assertTrue(
        TypewiseAlert.classifyTemperatureBreach(CoolingType.MED_ACTIVE_COOLING, 35) == TypewiseAlert.BreachType.NORMAL);
    assertTrue(
        TypewiseAlert.classifyTemperatureBreach(CoolingType.PASSIVE_COOLING, 40) == TypewiseAlert.BreachType.TOO_HIGH);
  }

  @Test
  public void testCheckAndAlert() {
    TypewiseAlert.checkAndAlert(AlertTarget.TO_CONTROLLER, CoolingType.HI_ACTIVE_COOLING, 35);
    TypewiseAlert.checkAndAlert(AlertTarget.TO_EMAIL, CoolingType.PASSIVE_COOLING, 55);
  }
}
