package TypewiseAlert;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TypewiseAlertTest extends TypewiseAlert {

  @Test
  public void testInferBreach() {
    assertTrue(TypewiseAlert.inferBreach(12, 20, 30) == TypewiseAlert.BreachType.TOO_LOW);
    assertTrue(TypewiseAlert.inferBreach(32, 20, 30) == TypewiseAlert.BreachType.TOO_HIGH);
    assertTrue(TypewiseAlert.inferBreach(25, 20, 30) == TypewiseAlert.BreachType.NORMAL);
  }

  @Test
  public void testClassifyTemperatureBreach() {
    assertTrue(
        TypewiseAlert.classifyTemperatureBreach(CoolingType.HiActiveCooling, 55) == TypewiseAlert.BreachType.TOO_HIGH);
    assertTrue(
        TypewiseAlert.classifyTemperatureBreach(CoolingType.MedActiveCooling, 35) == TypewiseAlert.BreachType.NORMAL);
    assertTrue(
        TypewiseAlert.classifyTemperatureBreach(CoolingType.PassiveCooling, 40) == TypewiseAlert.BreachType.TOO_HIGH);
  }

  @Test
  public void testCheckAndAlert() {
    TypewiseAlert.checkAndAlert(AlertTarget.ControllerAlert, CoolingType.HiActiveCooling, 35);
    TypewiseAlert.checkAndAlert(AlertTarget.MailAlert, CoolingType.PassiveCooling, 55);
    TypewiseAlert.checkAndAlert(AlertTarget.MailAlert, CoolingType.MedActiveCooling, 30);
  }
}
