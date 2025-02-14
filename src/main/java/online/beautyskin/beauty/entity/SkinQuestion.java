package online.beautyskin.beauty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class SkinQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Question is not blank")
    private String question;
    @NotBlank(message = "typeNormal is not blank")
    private String typeNormal;
    @NotBlank(message = "typeOily is not blank")
    private String typeOily;
    @NotBlank(message = "typeCombine is not blank")
    private String typeCombine;
    @NotBlank(message = "typeDry is not blank")
    private String typeDry;
    private boolean isDelete = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTypeCombine() {
        return typeCombine;
    }

    public void setTypeCombine(String typeCombine) {
        this.typeCombine = typeCombine;
    }

    public String getTypeDry() {
        return typeDry;
    }

    public void setTypeDry(String typeDry) {
        this.typeDry = typeDry;
    }

    public String getTypeNormal() {
        return typeNormal;
    }

    public void setTypeNormal(String typeNormal) {
        this.typeNormal = typeNormal;
    }

    public String getTypeOily() {
        return typeOily;
    }

    public void setTypeOily(String typeOily) {
        this.typeOily = typeOily;
    }

    public SkinQuestion() {
    }

    public SkinQuestion(long id, boolean isDelete, String question, String typeCombine, String typeDry, String typeNormal, String typeOily) {
        this.id = id;
        this.isDelete = isDelete;
        this.question = question;
        this.typeCombine = typeCombine;
        this.typeDry = typeDry;
        this.typeNormal = typeNormal;
        this.typeOily = typeOily;
    }
}
