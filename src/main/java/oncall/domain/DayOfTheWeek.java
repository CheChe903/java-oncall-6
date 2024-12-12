package oncall.domain;

public enum DayOfTheWeek {

    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");


    private final String name;

    DayOfTheWeek(String name) {
        this.name = name;
    }

    public static boolean validateDayOfTheWeek(String currentDay) {
        for (DayOfTheWeek day : DayOfTheWeek.values()) {
            if (day.name.equals(currentDay)) {
                return true;
            }
        }
        return false;
    }
}
