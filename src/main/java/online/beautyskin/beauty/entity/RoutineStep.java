package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RoutineStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "Description")
    private String description;
    @Column(name = "StepName")
    private String stepName;
    @Column(name = "StepOrder")
    @Min(1)
    private int stepOrder;
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "routine_id", nullable = false)
    @JsonBackReference
    private Routine routine;

//    @ManyToMany
//    @JoinTable(
//            name = "mapping_product_routine_step",
//            joinColumns = @JoinColumn(name = "step_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
    @ManyToMany(mappedBy = "routineSteps", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

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

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    
}
