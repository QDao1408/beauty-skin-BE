package online.beautyskin.beauty.api;


import online.beautyskin.beauty.entity.Feedback;
import online.beautyskin.beauty.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/feedback")
public class FeedbackAPI {

    List<Feedback> feedbackList = new ArrayList<>();

    @Autowired
    FeedBackService feedBackService;

    //create
    @PostMapping("create")
    public ResponseEntity createFeedback(@RequestBody Feedback feedBack){
        return ResponseEntity.ok(feedBackService.createFeedback(feedBack));
    }
    //remove
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable long id){
        Feedback feedback = feedBackService.removeFeedback(id);
        return ResponseEntity.ok(feedback);
    }
    //update
    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable long id,@RequestBody Feedback feedBack){
        Feedback feedback = feedBackService.createFeedback(feedBack);
        return ResponseEntity.ok(feedback);
    }
    //display
    @GetMapping("getAll")
    public ResponseEntity getAll(){
        feedbackList = feedBackService.getAll();
        return ResponseEntity.ok(feedbackList);
    }
    @GetMapping("getDeleteIsFalse")
    public ResponseEntity getDeleteIsFalse(){
        feedbackList = feedBackService.getFeedbackByDeleteIsFalse();
        return ResponseEntity.ok(feedbackList);
    }
    @GetMapping("getFeedbackById/{id}")
    public ResponseEntity getFeedbackById(@PathVariable long id){
        Feedback feedback = feedBackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }
}
