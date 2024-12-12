package oncall.domain;

import static oncall.utils.exception.ErrorMessage.MONTH_OUT_OF_RANGE;
import static oncall.utils.exception.ErrorMessage.WEEK_NOT_AVAILABLE;

import oncall.utils.exception.OnCallException;

public class StartDate {

    private final int startMonth;
    private final String dayOfTheWeek;

    public StartDate(int startMonth, String dayOfTheWeek) {
        this.startMonth = startMonth;
        this.dayOfTheWeek = dayOfTheWeek;
        validateStartMonth();
        validateDayOfTheWeek();
    }

    private void validateStartMonth() {
        if (startMonth < 1 || startMonth > 12) {
            throw new OnCallException(MONTH_OUT_OF_RANGE);
        }
    }

    private void validateDayOfTheWeek() {
        if (!DayOfTheWeek.validateDayOfTheWeek(dayOfTheWeek)) {
            throw new OnCallException(WEEK_NOT_AVAILABLE);
        }
    }
}
