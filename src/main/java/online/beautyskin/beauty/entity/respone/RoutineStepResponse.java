package online.beautyskin.beauty.entity.respone;

import java.util.List;

public class RoutineStepResponse {
    private Long id;
    private String description;
    private String stepName;
    private int stepOrder;
    private List<ProductResponseForRoutine> productResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ProductResponseForRoutine> getProductResponse() {
        return productResponse;
    }

    public void setProductResponse(List<ProductResponseForRoutine> productResponse) {
        this.productResponse = productResponse;
    }

    public RoutineStepResponse() {
    }

    public RoutineStepResponse(Long id, String description, String stepName, int stepOrder, List<ProductResponseForRoutine> productResponse) {
        this.id = id;
        this.description = description;
        this.stepName = stepName;
        this.stepOrder = stepOrder;
        this.productResponse = productResponse;
    }
}
