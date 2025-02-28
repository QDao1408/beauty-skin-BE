package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnswerId")
    private long id;

    @ManyToOne
    @JoinColumn(name = "quizid")
    @JsonIgnore
    private SkinQuestion skinQuestion;

    private String answer;

    private int points;

    public Answer(String answer, int point, SkinQuestion skinQuestion) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public SkinQuestion getSkinQuestion() {
        return skinQuestion;
    }

    public void setSkinQuestion(SkinQuestion skinQuestion) {
        this.skinQuestion = skinQuestion;
    }

    public Answer(long id, String answer, int points) {
        this.id = id;
        this.answer = answer;
        this.points = points;
    }

    public Answer() {
    }
}
