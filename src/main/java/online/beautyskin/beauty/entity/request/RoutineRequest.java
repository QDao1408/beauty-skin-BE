package online.beautyskin.beauty.entity.request;


import online.beautyskin.beauty.entity.SkinType;

import java.util.List;

public class RoutineRequest {
    private String name;
    private String description;
    private Long skinTypeId;
    private List<RoutineStepRequest> routineStepRequests;

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

    public List<RoutineStepRequest> getRoutineStepRequests() {
        return routineStepRequests;
    }

    public void setRoutineStepRequests(List<RoutineStepRequest> routineStepRequests) {
        this.routineStepRequests = routineStepRequests;
    }

    //    public List<Long> getStepId() {
//        return stepId;
//    }
//
//    public void setStepId(List<Long> stepId) {
//        this.stepId = stepId;
//    }
}
