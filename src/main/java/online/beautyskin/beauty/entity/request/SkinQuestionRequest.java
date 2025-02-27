package online.beautyskin.beauty.entity.request;

import java.util.List;

public class SkinQuestionRequest {
    private String question;
    private String description;
    private List<AnswerRequest>  answer;

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

    public List<AnswerRequest> getAnswer() {
        return answer;
    }

    public void setAnswer(List<AnswerRequest> answer) {
        this.answer = answer;
    }

}
