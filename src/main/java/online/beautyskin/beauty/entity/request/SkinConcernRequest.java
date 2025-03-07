package online.beautyskin.beauty.entity.request;

import jakarta.persistence.Column;

public class SkinConcernRequest {
    private String name;
    private String description;

    public SkinConcernRequest() {
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
}
