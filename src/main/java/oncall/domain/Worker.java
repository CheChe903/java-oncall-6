package oncall.domain;

import static oncall.utils.exception.ErrorMessage.NOT_AVAILABLE_WORKER;
import static oncall.utils.exception.ErrorMessage.WORKER_NAME_OUT_OF_RANGE;
import static oncall.utils.exception.ErrorMessage.WORKER_SIZE_OUT_OF_RANGE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.utils.exception.OnCallException;

public class Worker {

    private final List<String> weeksDayWorkers;
    private final List<String> weekendDayWorkers;

    public Worker(List<String> weeksDayWorkers, List<String> weekendDayWorkers) {
        this.weeksDayWorkers = weeksDayWorkers;
        this.weekendDayWorkers = weekendDayWorkers;
        validateEqualWorkers();
        validateWorkersSize();
        validateWorkerName();
    }

    private void validateEqualWorkers() {
        Set<String> weeksDay = new HashSet<>(weeksDayWorkers);
        Set<String> weekendDay = new HashSet<>(weekendDayWorkers);
        Set<String> megeredSet = new HashSet<>();
        megeredSet.addAll(weekendDay);
        megeredSet.addAll(weeksDay);

        if ((megeredSet.size() != weeksDay.size()) || weeksDay.size() != weekendDay.size()) {
            throw new OnCallException(NOT_AVAILABLE_WORKER);
        }
    }

    private void validateWorkersSize() {
        if (weekendDayWorkers.size() < 5 || weekendDayWorkers.size() > 35) {
            throw new OnCallException(WORKER_SIZE_OUT_OF_RANGE);
        }
    }

    private void validateWorkerName() {
        for (String workerName : weekendDayWorkers) {
            if (workerName.length() > 5) {
                throw new OnCallException(WORKER_NAME_OUT_OF_RANGE);
            }
        }
    }

    public List<String> getWeeksDayWorkers() {
        return weeksDayWorkers;
    }

    public List<String> getWeekendDayWorkers() {
        return weekendDayWorkers;
    }
}
