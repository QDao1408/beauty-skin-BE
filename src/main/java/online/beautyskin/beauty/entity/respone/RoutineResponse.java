package online.beautyskin.beauty.entity.respone;

import online.beautyskin.beauty.entity.SkinType;

import java.util.List;

public class RoutineResponse {
    private String name;
    private String description;
    private Long skinTypeId;
    private List<RoutineStepResponse> routineStepResponse;

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

    public List<RoutineStepResponse> getRoutineStepResponse() {
        return routineStepResponse;
    }

    public void setRoutineStepResponse(List<RoutineStepResponse> routineStepResponse) {
        this.routineStepResponse = routineStepResponse;
    }

    public RoutineResponse() {
    }

    public RoutineResponse(String name, String description, Long skinTypeId, List<RoutineStepResponse> routineStepResponse) {
        this.name = name;
        this.description = description;
        this.skinTypeId = skinTypeId;
        this.routineStepResponse = routineStepResponse;
    }
}
