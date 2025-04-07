package online.beautyskin.beauty.entity.respone;

import online.beautyskin.beauty.entity.SkinType;

import java.util.List;

public class RoutineResponse {
    private Long id;
    private String name;
    private String description;
    private SkinTypeResponse skinTypeResponse;
    private List<RoutineStepResponse> routineStepResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public SkinTypeResponse getSkinTypeResponse() {
        return skinTypeResponse;
    }

    public void setSkinTypeResponse(SkinTypeResponse skinTypeResponse) {
        this.skinTypeResponse = skinTypeResponse;
    }

    public List<RoutineStepResponse> getRoutineStepResponse() {
        return routineStepResponse;
    }

    public void setRoutineStepResponse(List<RoutineStepResponse> routineStepResponse) {
        this.routineStepResponse = routineStepResponse;
    }

    public RoutineResponse() {
    }

    public RoutineResponse(Long id, String name, String description, SkinTypeResponse skinTypeResponse, List<RoutineStepResponse> routineStepResponse) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.skinTypeResponse = skinTypeResponse;
        this.routineStepResponse = routineStepResponse;
    }
}
