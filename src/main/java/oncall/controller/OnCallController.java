package oncall.controller;

import oncall.domain.StartDate;
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
        
    }

    private StartDate getStartDate() {
        while (true) {
            try {
                String input = inputView.askMonthAndDayOfTheWeek();

                return onCallService.makeStartDate(input);
            } catch (OnCallException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
