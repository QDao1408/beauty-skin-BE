package online.beautyskin.beauty.entity.respone;

public class SkinTypeResponse {
    private Long typeId;
    private String type;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SkinTypeResponse() {
    }

    public SkinTypeResponse(Long typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }
}
