package oncall.service;

import static oncall.utils.exception.ErrorMessage.HAVE_TO_INT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oncall.domain.Calendar;
import oncall.domain.DayOfTheWeek;
import oncall.domain.StartDate;
import oncall.domain.WorkSchedule;
import oncall.domain.Worker;
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

    public List<String> makeWorkers(String workerNames) {
        return new ArrayList<>(Arrays.asList(splitByComma(workerNames)));
    }

    public WorkSchedule makeWorkSchedule(Worker worker, StartDate startDate) {
        WorkSchedule workSchedule = initWorkSchedule(worker, startDate);

        return workSchedule;
    }

    private WorkSchedule initWorkSchedule(Worker worker, StartDate startDate) {

        List<String> workers = new ArrayList<>();
        int weeksDayPivot = 0;
        int weekendDayPivot = 0;
        int currentDay = 1;

        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.findByName(startDate.getDayOfTheWeek());
        Calendar month = Calendar.findByIndex(startDate.getStartMonth());
        List<String> weeksDayWorkers = worker.getWeeksDayWorkers();
        List<String> weekendDayWorkers = worker.getWeekendDayWorkers();

        while (workers.size() != month.getLastDay()) {
            if (dayOfTheWeek.isWeekend() || month.isHoliday(currentDay)) {
                workers.add(weekendDayWorkers.get(weekendDayPivot));
                weekendDayPivot += 1;
                weekendDayPivot = weekendDayPivot % (weekendDayWorkers.size() - 1);
            }

            if (!dayOfTheWeek.isWeekend()) {
                workers.add(weeksDayWorkers.get(weeksDayPivot));
                weeksDayPivot += 1;
                weeksDayPivot = weeksDayPivot % (weeksDayWorkers.size() - 1);
            }
            dayOfTheWeek = DayOfTheWeek.nextDay(dayOfTheWeek);
            currentDay++;
        }

        return new WorkSchedule(workers);
    }
}
