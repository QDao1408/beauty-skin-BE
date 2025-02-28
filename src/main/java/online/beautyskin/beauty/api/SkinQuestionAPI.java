package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.SkinQuestion;
import online.beautyskin.beauty.entity.request.SkinQuestionRequest;
import online.beautyskin.beauty.service.SkinQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/skinQuestion")
@SecurityRequirement(name = "api")
public class SkinQuestionAPI {
    List<SkinQuestion> skinQuestionList = new ArrayList<>();
    @Autowired
    SkinQuestionService skinQuestionService;

    @PostMapping("create")
    public ResponseEntity createSkinQuestion(@RequestBody SkinQuestionRequest skinQuestionRequest){
        SkinQuestion newSkinQuest = skinQuestionService.createSkinQuestion(skinQuestionRequest);
        return ResponseEntity.ok(newSkinQuest);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteSkinQuestion(@PathVariable long id){
        SkinQuestion skinQuestion = skinQuestionService.removeSkinQuestion(id);
        return ResponseEntity.ok(skinQuestion);
    }
    @PutMapping("update/{id}")
    public ResponseEntity updateSkinQuestion(@PathVariable long id,@RequestBody SkinQuestionRequest skinQuestionRequest){
        SkinQuestion newSkinQuestion = skinQuestionService.updateSkinQuestion(id, skinQuestionRequest);
        return ResponseEntity.ok(newSkinQuestion);
    }
    @GetMapping("getAll/{id}")
    public ResponseEntity getAllQuestion(){
        skinQuestionList = skinQuestionService.getSkinQuestion();
        return ResponseEntity.ok(skinQuestionList);
    }
    @GetMapping("getDeleteIsFalse")
    public ResponseEntity getDeleteIsFalse(){
        skinQuestionList = skinQuestionService.getSkinQuestionDeleteIsFalse();
        return ResponseEntity.ok(skinQuestionList);
    }
}
