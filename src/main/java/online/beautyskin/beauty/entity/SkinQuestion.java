package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class SkinQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizid")
    private long id;
    @NotBlank(message = "Question is not empty!")
    private String question;
    @NotBlank(message = "Description is not empty!")
    private String description;
    @OneToMany(mappedBy = "skinQuestion")
    private List<Answer> answers;
    private boolean isDelete = false;

    public SkinQuestion(String question, String description) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public SkinQuestion(long id, String question, String description, boolean isDelete) {
        this.id = id;
        this.question = question;
        this.description = description;
        this.isDelete = isDelete;
    }

    public SkinQuestion() {
    }
}
