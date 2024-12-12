package oncall.domain;

import static oncall.utils.exception.ErrorMessage.NOT_AVAILABLE_DAY_OF_THE_WEEK;

import java.util.List;
import oncall.utils.exception.OnCallException;

public enum Calendar {

    JANUARY(1, 31, List.of(1)),
    FEBRUARY(2, 28, List.of()),
    MARCH(3, 31, List.of(1)),
    APRIL(4, 30, List.of()),
    MAY(5, 31, List.of(5)),
    JUNE(6, 30, List.of(6)),
    JULY(7, 31, List.of()),
    AUGUST(8, 31, List.of(15)),
    SEPTEMBER(9, 30, List.of()),
    OCTOBER(10, 31, List.of(3, 9)),
    NOVEMBER(11, 30, List.of()),
    DECEMBER(12, 31, List.of(25));


    private final int index;
    private final int lastDay;
    private final List<Integer> holiDays;

    Calendar(int index, int lastDay, List<Integer> holiDays) {
        this.index = index;
        this.lastDay = lastDay;
        this.holiDays = holiDays;
    }

    public static Calendar findByIndex(int startMonth) {
        for (Calendar calendar : Calendar.values()) {
            if (calendar.index == startMonth) {
                return calendar;
            }
        }
        throw new OnCallException(NOT_AVAILABLE_DAY_OF_THE_WEEK);
    }

    public boolean isHoliday(int currentDay) {
        for (int day : holiDays) {
            if (currentDay == day) {
                return true;
            }
        }
        return false;
    }

    public int getIndex() {
        return index;
    }

    public int getLastDay() {
        return lastDay;
    }
}
