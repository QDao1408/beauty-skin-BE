package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.SkinQuestion;
import online.beautyskin.beauty.repository.SkinQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinQuestionService {
    @Autowired
    SkinQuestionRepository skinQuestionRepository;
    //create
    public SkinQuestion createSkinQuestion(SkinQuestion skinQuestion){
        return skinQuestionRepository.save(skinQuestion);
    }
    //remove
    public SkinQuestion removeSkinQuestion(long id){
        SkinQuestion skinQuestion = skinQuestionRepository.findSkinQuestionById(id);
        skinQuestion.setDelete(true);
        return skinQuestionRepository.save(skinQuestion);
    }
    //update
    public SkinQuestion updateSkinQuestion(SkinQuestion skinQuestion){
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
