package TypewiseAlert;

import TypewiseAlert.TypewiseAlert.BreachType;

public class TypewiseAlert {

  public enum BreachType {
                          NORMAL("Normal"),
                          TOO_LOW("too low"),
                          TOO_HIGH("too high");

    private final String displayText;

    private BreachType(final String displayText) {
      this.displayText = displayText;
    }

    @Override
    public String toString() {
      return this.displayText;
    }
  };

  public static BreachType inferBreach(final double value, final double lowerLimit, final double upperLimit) {
    if (value < lowerLimit) {
      return BreachType.TOO_LOW;
    }
    if (value > upperLimit) {
      return BreachType.TOO_HIGH;
    }
    return BreachType.NORMAL;
  }

  public static BreachType classifyTemperatureBreach(final CoolingType coolingType, final double temperatureInC) {
    return coolingType.classifyBreach(temperatureInC);
  }

  public static void checkAndAlert(final AlertTarget alertTarget, final CoolingType coolingType,
      final double temperatureInC) {

    BreachType breachType = classifyTemperatureBreach(coolingType, temperatureInC);

    alertTarget.sendAlert(breachType);
  }

}

interface CoolingType {

  public BreachType classifyBreach(final double temp);

  CoolingType PassiveCooling = tempInC -> TypewiseAlert.inferBreach(tempInC, 0, 35);
  CoolingType HiActiveCooling = tempInC -> TypewiseAlert.inferBreach(tempInC, 0, 35);
  CoolingType MedActiveCooling = tempInC -> TypewiseAlert.inferBreach(tempInC, 0, 35);

}

interface AlertTarget {

  public void sendAlert(final BreachType breachType);

  AlertTarget MailAlert = breachType -> {
    String recepient = "a.b@c.com";
    if (!breachType.equals(BreachType.NORMAL)) {
      AlertTarget.mailContent(recepient, breachType.toString());
    }
  };

  AlertTarget ControllerAlert = breachType -> {
    int header = 0xfeed;
    System.out.printf("%d : %s\n", header, breachType.toString());
  };

  public static void mailContent(final String recepient, final String BreachType) {
    System.out.printf("To: %s\n", recepient);
    System.out.println("Hi, the temperature is " + BreachType + "\n");
  }

}
