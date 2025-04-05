package online.beautyskin.beauty.entity.request;

import online.beautyskin.beauty.entity.Product;

import java.util.List;

public class RoutineStepRequest {

    private String description;

    private String stepName;

    private int stepOrder;

    private List<ProductRequestRoutine> products;

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

    public List<ProductRequestRoutine> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRequestRoutine> products) {
        this.products = products;
    }
}
