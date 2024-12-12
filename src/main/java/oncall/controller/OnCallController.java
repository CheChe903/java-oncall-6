package oncall.controller;

import java.util.List;
import oncall.domain.StartDate;
import oncall.domain.WorkSchedule;
import oncall.domain.Worker;
import oncall.service.OnCallService;
import oncall.utils.exception.OnCallException;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OnCallController {

    private final InputView inputView;
    private final OutputView outputView;
    private final OnCallService onCallService;

    public OnCallController(InputView inputView, OutputView outputView, OnCallService onCallService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.onCallService = onCallService;
    }

    public void run() {
        StartDate startDate = getStartDate();
        Worker workers = getWorkers();

        WorkSchedule workSchedule = onCallService.makeWorkSchedule(workers, startDate);

        outputView.printResult(workSchedule, startDate);
    }

    private Worker getWorkers() {
        while (true) {
            try {
                List<String> weeksDayWorkers = onCallService.makeWorkers(inputView.askWeeksDayWorkers());
                List<String> weekendDayWorkers = onCallService.makeWorkers(inputView.askWeekendWorkers());

                return new Worker(weeksDayWorkers, weekendDayWorkers);


            } catch (OnCallException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private StartDate getStartDate() {
        while (true) {
            try {
                return onCallService.makeStartDate(inputView.askMonthAndDayOfTheWeek());
            } catch (OnCallException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
