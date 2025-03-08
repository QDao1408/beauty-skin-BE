package online.beautyskin.beauty.entity.request;

import java.util.List;

public class RoutineRequest {
    private String name;
    private String description;
    private long skinTypeId;
    //private List<Long> stepId;

    public RoutineRequest() {
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

    public Long getSkinTypeId() {
        return skinTypeId;
    }

    public void setSkinTypeId(Long skinTypeId) {
        this.skinTypeId = skinTypeId;
    }

    public void setSkinTypeId(long skinTypeId) {
        this.skinTypeId = skinTypeId;
    }

//    public List<Long> getStepId() {
//        return stepId;
//    }
//
//    public void setStepId(List<Long> stepId) {
//        this.stepId = stepId;
//    }
}
