package oncall.domain;

import static oncall.utils.exception.ErrorMessage.NOT_AVAILABLE_DAY_OF_THE_WEEK;

import oncall.utils.exception.OnCallException;

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

    public static DayOfTheWeek findByName(String dayOfTheWeek) {
        for (DayOfTheWeek day : DayOfTheWeek.values()) {
            if (day.name.equals(dayOfTheWeek)) {
                return day;
            }
        }
        throw new OnCallException(NOT_AVAILABLE_DAY_OF_THE_WEEK);
    }

    public boolean isWeekend() {
        return name.equals("토") || name.equals("일");
    }

    public static DayOfTheWeek nextDay(DayOfTheWeek dayOfTheWeek) {
        boolean isOk = false;
        for (DayOfTheWeek day : DayOfTheWeek.values()) {
            if (isOk) {
                return day;
            }
            if (day.equals(dayOfTheWeek)) {
                if (day == SUNDAY) {
                    return MONDAY;
                }
                isOk = true;
            }
        }

        throw new OnCallException(NOT_AVAILABLE_DAY_OF_THE_WEEK);
    }

    public String getName() {
        return name;
    }
}
