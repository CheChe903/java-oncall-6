package oncall;

import oncall.controller.OnCallController;
import oncall.service.OnCallService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        OnCallService onCallService = new OnCallService();

        OnCallController onCallController = new OnCallController(inputView, outputView, onCallService);

        onCallController.run();
        
    }
}
