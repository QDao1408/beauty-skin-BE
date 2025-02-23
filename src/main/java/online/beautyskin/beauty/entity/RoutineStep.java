package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class RoutineStep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "Description")
    private String description;
    @Column(name = "StepName")
    @Min(1)
    private String stepName;
    @Column(name = "StepOrder")
    @Min(1)
    private int stepOrder;
    private boolean isDeleted = false;

    public RoutineStep() {}
    public RoutineStep(String description, String stepName, int stepOrder) {
        this.description = description;
        this.stepName = stepName;
        this.stepOrder = stepOrder;
    }
    public long getId() { return id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStepName() { return stepName; }
    public void setStepName(String stepName) { this.stepName = stepName; }
    public int getStepOrder() { return stepOrder; }
    public void setStepOrder(int stepOrder) { this.stepOrder = stepOrder; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
}
