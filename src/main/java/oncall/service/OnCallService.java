package oncall.service;

import static oncall.utils.exception.ErrorMessage.HAVE_TO_INT;

import oncall.domain.StartDate;
import oncall.utils.Splitter;
import oncall.utils.exception.OnCallException;

public class OnCallService {

    public StartDate makeStartDate(String input) {
        String[] monthAndDayOfTheWeek = splitByComma(input);

        return new StartDate(parseToInteger(monthAndDayOfTheWeek[0]), monthAndDayOfTheWeek[1]);

    }

    private String[] splitByComma(String input) {
        return Splitter.splitBy(input, ",");
    }

    private int parseToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new OnCallException(HAVE_TO_INT);
        }
    }


}
