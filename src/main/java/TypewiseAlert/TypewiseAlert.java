package TypewiseAlert;

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

  public enum CoolingType {
                           PASSIVE_COOLING("Passive Cooling", 0, 35),
                           HI_ACTIVE_COOLING("Hi active Cooling", 0, 45),
                           MED_ACTIVE_COOLING("Med active Cooling", 0, 40);

    private final String displayText;
    private final int lowerLimit;
    private final int upperLimit;

    private CoolingType(final String displayText, final int lowerLimit, final int upperLimit) {
      this.displayText = displayText;
      this.lowerLimit = lowerLimit;
      this.upperLimit = upperLimit;
    }

    @Override
    public String toString() {
      return this.displayText;
    }

    public int getLowerLimit() {
      return this.lowerLimit;
    }

    public int getUpperLimit() {
      return this.upperLimit;
    }
  };

  public static BreachType classifyTemperatureBreach(final CoolingType coolingType, final double temperatureInC) {
    return inferBreach(temperatureInC, coolingType.getLowerLimit(), coolingType.getUpperLimit());
  }

  public enum AlertTarget {
                           TO_CONTROLLER,
                           TO_EMAIL
  };

  public static void checkAndAlert(final AlertTarget alertTarget, final CoolingType coolingType,
      final double temperatureInC) {

    BreachType breachType = classifyTemperatureBreach(coolingType, temperatureInC);

    if (alertTarget.equals(AlertTarget.TO_CONTROLLER)) {
      sendToController(breachType);
    }
    if (alertTarget.equals(AlertTarget.TO_EMAIL)) {
      sendToEmail(breachType);
    }
  }

  public static void sendToController(final BreachType breachType) {
    int header = 0xfeed;
    System.out.printf("%d : %s\n", header, breachType.toString());
  }

  public static void mailContent(final String recepient, final String BreachType) {
    System.out.printf("To: %s\n", recepient);
    System.out.println("Hi, the temperature is " + BreachType + "\n");
  }

  public static void sendToEmail(final BreachType breachType) {
    String recepient = "a.b@c.com";
    if (!breachType.equals(BreachType.NORMAL)) {
      mailContent(recepient, breachType.toString());
    }

  }
}
