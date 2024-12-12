package oncall.domain;

import java.util.List;

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
}