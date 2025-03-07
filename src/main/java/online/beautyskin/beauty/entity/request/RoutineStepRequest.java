package online.beautyskin.beauty.entity.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import online.beautyskin.beauty.entity.Routine;

public class RoutineStepRequest {

    private String description;

    private String stepName;

    private int stepOrder;

    private Routine routine;

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

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
}
