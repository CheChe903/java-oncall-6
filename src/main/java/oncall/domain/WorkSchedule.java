package oncall.domain;

import java.util.List;

public class WorkSchedule {

    private final List<String> workers;

    public WorkSchedule(List<String> workers) {
        this.workers = workers;
    }

    public List<String> getWorkers() {
        return workers;
    }
}
