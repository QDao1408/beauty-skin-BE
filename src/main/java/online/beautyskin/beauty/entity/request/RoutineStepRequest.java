package online.beautyskin.beauty.entity.request;

public class RoutineStepRequest {

    private String description;

    private String stepName;

    private int stepOrder;

    private Long routine;

    public RoutineStepRequest() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public Long getRoutine() {
        return routine;
    }

    public void setRoutine(Long routine) {
        this.routine = routine;
    }
}
