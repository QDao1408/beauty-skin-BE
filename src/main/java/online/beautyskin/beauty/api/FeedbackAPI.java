package online.beautyskin.beauty.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.Feedback;
import online.beautyskin.beauty.entity.request.FeedbackRequest;
import online.beautyskin.beauty.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/feedback")
@SecurityRequirement(name = "api")
public class FeedbackAPI {

    List<Feedback> feedbackList = new ArrayList<>();

    @Autowired
    FeedBackService feedBackService;

    //create
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("create/{productId}")
    public ResponseEntity createFeedback(@PathVariable long productId, @RequestBody FeedbackRequest feedBack){
        return ResponseEntity.ok(feedBackService.createFeedback(productId,feedBack));
    }
    //remove
    @DeleteMapping("delete/{feedbackId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity delete(@PathVariable long feedbackId){
        Feedback feedback = feedBackService.removeFeedback(feedbackId);
        return ResponseEntity.ok(feedback);
    }
    //update
    @PutMapping("update/{feedbackId}")
    public ResponseEntity update(@PathVariable long feedbackId,@RequestBody FeedbackRequest feedBack){
        return ResponseEntity.ok(feedBackService.updateFeedback(feedbackId, feedBack));
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
