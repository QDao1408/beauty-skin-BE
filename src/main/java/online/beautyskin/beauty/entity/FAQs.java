package online.beautyskin.beauty.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class FAQs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "Question")
    @NotNull
    private String question;
    @Column(name = "Answer")
    @NotNull
    private String answer;
    private boolean isDeleted = false;

    public FAQs() {}
    public FAQs(String question, String answer) { this.question = question; this.answer = answer;}
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }

}
