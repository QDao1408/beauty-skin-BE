package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RountineID")
    private long id;
    @Column(name = "RoutineName")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "LastUpdate")
    @NotNull
    private LocalDateTime lastUpdate;
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "skinType")
    private SkinType skinType;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private List<RoutineStep> routineSteps = new ArrayList<>();


    public void addRoutineStep(RoutineStep step) {
        routineSteps.add(step);
        step.setRoutine(this);
    }

    public void removeRoutineStep(RoutineStep step) {
        routineSteps.remove(step);
        step.setRoutine(null);
    }

    public Routine() {
    }

    public Routine(long id, String name, String description, LocalDateTime lastUpdate, boolean isDeleted, SkinType skinType, List<RoutineStep> routineSteps) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.isDeleted = isDeleted;
        this.skinType = skinType;
        this.routineSteps = routineSteps;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinType skinType) {
        this.skinType = skinType;
    }

    public List<RoutineStep> getRoutineSteps() {
        return routineSteps;
    }

    public void setRoutineSteps(List<RoutineStep> routineSteps) {
        this.routineSteps = routineSteps;
    }


}
