package oncall.service;

import static oncall.utils.exception.ErrorMessage.HAVE_TO_INT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        return initWorkSchedule(worker, startDate);
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
        String previousWorker = "";
        Queue<String> weeksDayQueue = new LinkedList<>();
        Queue<String> weekendDayQueue = new LinkedList<>();
        while (workers.size() != month.getLastDay()) {
            if (dayOfTheWeek.isWeekend() || month.isHoliday(currentDay)) {
                if (!weekendDayQueue.isEmpty()) {
                    String nowWorker = weekendDayQueue.poll();

                    workers.add(nowWorker);
                    System.out.println(nowWorker + " " + currentDay);
                    previousWorker = nowWorker;
                    continue;
                }
                String nowWorker = weekendDayWorkers.get(weekendDayPivot);
                if (previousWorker.equals(nowWorker)) {
                    weekendDayQueue.add(nowWorker);
                    weekendDayPivot += 1;
                    weekendDayPivot = weekendDayPivot % (weekendDayWorkers.size() - 1);
                    nowWorker = weekendDayWorkers.get(weekendDayPivot);

                }
                previousWorker = nowWorker;
                workers.add(nowWorker);
                weekendDayPivot += 1;
                weekendDayPivot = weekendDayPivot % (weekendDayWorkers.size() - 1);

                dayOfTheWeek = DayOfTheWeek.nextDay(dayOfTheWeek);
                currentDay++;

                continue;
            }

            if (!dayOfTheWeek.isWeekend()) {
                if (!weeksDayQueue.isEmpty()) {
                    String nowWorker = weeksDayQueue.poll();

                    workers.add(nowWorker);
                    previousWorker = nowWorker;
                }
                String nowWorker = weeksDayWorkers.get(weeksDayPivot);
                if (previousWorker.equals(nowWorker)) {
                    weeksDayQueue.add(nowWorker);
                    weeksDayPivot += 1;
                    weeksDayPivot = weeksDayPivot % (weeksDayWorkers.size() - 1);
                    nowWorker = weeksDayWorkers.get(weeksDayPivot);
                }
                previousWorker = nowWorker;
                workers.add(nowWorker);
                weeksDayPivot += 1;
                weeksDayPivot = weeksDayPivot % (weeksDayWorkers.size() - 1);

                dayOfTheWeek = DayOfTheWeek.nextDay(dayOfTheWeek);
                currentDay++;
            }

        }

        return new WorkSchedule(workers);
    }
}
