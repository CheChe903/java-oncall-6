package oncall.view;

import oncall.domain.Calendar;
import oncall.domain.DayOfTheWeek;
import oncall.domain.StartDate;
import oncall.domain.WorkSchedule;

public class OutputView {

    public void printResult(WorkSchedule workSchedule, StartDate startDate) {
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.findByName(startDate.getDayOfTheWeek());
        Calendar month = Calendar.findByIndex(startDate.getStartMonth());
        int day = 1;
        for (String worker : workSchedule.getWorkers()) {
            System.out.println(
                    month.getIndex() + "월 " + day + "일 " + dayOfTheWeek.getName() + checkHoliDays(dayOfTheWeek, month,
                            day)
                            + worker);

            dayOfTheWeek = DayOfTheWeek.nextDay(dayOfTheWeek);
            day += 1;
        }
    }

    private String checkHoliDays(DayOfTheWeek dayOfTheWeek, Calendar month, int day) {
        if (!dayOfTheWeek.isWeekend() && month.isHoliday(day)) {
            return " (휴일) ";
        }
        return " ";
    }
}
