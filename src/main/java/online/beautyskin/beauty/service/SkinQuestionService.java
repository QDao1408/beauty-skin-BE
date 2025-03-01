package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Answer;
import online.beautyskin.beauty.entity.SkinQuestion;
import online.beautyskin.beauty.entity.request.AnswerRequest;
import online.beautyskin.beauty.entity.request.SkinQuestionRequest;
import online.beautyskin.beauty.repository.AnswerRepository;
import online.beautyskin.beauty.repository.SkinQuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkinQuestionService {
    @Autowired
    SkinQuestionRepository skinQuestionRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private AnswerRepository answerRepository;

    //create
    public SkinQuestion createSkinQuestion(SkinQuestionRequest skinQuestionRequest) {
        // Chuyển đổi request thành entity
        SkinQuestion skinQuestion = modelMapper.map(skinQuestionRequest, SkinQuestion.class);

        // Lưu SkinQuestion để có ID hợp lệ
        skinQuestion = skinQuestionRepository.save(skinQuestion);

        // Duyệt danh sách câu trả lời và liên kết với SkinQuestion
        List<Answer> answers = new ArrayList<>();
        for (AnswerRequest answerRequest : skinQuestionRequest.getAnswer()) {
            Answer answer = new Answer();
            answer.setAnswer(answerRequest.getAnswer());
            answer.setPoints(answerRequest.getPoint());
            answer.setSkinQuestion(skinQuestion); // Đúng: Gán đối tượng, không phải ID
            answers.add(answer);
        }

        // Lưu danh sách câu trả lời vào database
        answerRepository.saveAll(answers);

        // Gán lại danh sách Answer vào SkinQuestion (chỉ cần nếu muốn phản hồi đầy đủ)
        skinQuestion.setAnswers(answers);

        // Trả về SkinQuestion đã tạo
        return skinQuestion;
    }

    //remove
    public SkinQuestion removeSkinQuestion(long id){
        SkinQuestion skinQuestion = skinQuestionRepository.findSkinQuestionById(id);
        if (skinQuestion != null) {
            skinQuestion.setDelete(true);
        }else {
            throw new RuntimeException("SkinQuestion not found");
        }
        return skinQuestionRepository.save(skinQuestion);
    }
    //update
    public SkinQuestion updateSkinQuestion(long id, SkinQuestionRequest skinQuestionRequest){
        SkinQuestion skinQuestion = skinQuestionRepository.findSkinQuestionById(id);
        if (skinQuestion != null) {
            skinQuestion.setQuestion(skinQuestionRequest.getQuestion());
            skinQuestion.setDescription(skinQuestionRequest.getDescription());
            if(skinQuestionRequest.getAnswer() != null){
                List<Answer> answers = new ArrayList<>();
                for (AnswerRequest answerRequest : skinQuestionRequest.getAnswer()) {
                    Answer answer = new Answer();
                    answer.setAnswer(answerRequest.getAnswer());
                    answer.setPoints(answerRequest.getPoint());
                    answer.setSkinQuestion(skinQuestion);
                    answers.add(answer);
                }
                answerRepository.deleteAll(skinQuestion.getAnswers());
                answerRepository.saveAll(answers);
                skinQuestion.setAnswers(answers);
            }
        }else {
            throw new RuntimeException("SkinQuestion not found");
        }
        return skinQuestionRepository.save(skinQuestion);
    }
    //get
    public List<SkinQuestion> getSkinQuestion(){
        return skinQuestionRepository.findAll();
    }
    //getByIsDeleteFalse
    public List<SkinQuestion> getSkinQuestionDeleteIsFalse(){
        return skinQuestionRepository.findByIsDeleteFalse();
    }
}
