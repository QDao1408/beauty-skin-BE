package online.beautyskin.beauty.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.Feedback;
import online.beautyskin.beauty.entity.request.FeedbackRequest;
import online.beautyskin.beauty.entity.respone.FeedbackResponse;
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
    @PostMapping("create")
    public ResponseEntity createFeedback(@RequestBody FeedbackRequest feedBack){
        return ResponseEntity.ok(feedBackService.createFeedback(feedBack));
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
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity update(@PathVariable long feedbackId,@RequestBody FeedbackRequest feedBack){
        return ResponseEntity.ok(feedBackService.updateFeedback(feedbackId, feedBack));
    }
    //display
    @GetMapping("getAll")
    public ResponseEntity getAll(){
        List<FeedbackResponse> feedback = feedBackService.getAll();
        return ResponseEntity.ok(feedback);
    }
    @GetMapping("getDeleteIsFalse")
    public ResponseEntity getDeleteIsFalse(){
        feedbackList = feedBackService.getFeedbackByDeleteIsFalse();
        return ResponseEntity.ok(feedbackList);
    }
    @GetMapping("getFeedbackById/{productId}")
    public ResponseEntity getFeedbackByProductId(@PathVariable long productId){
        List<FeedbackResponse> feedback = feedBackService.getFeedbackByProductId(productId);
        return ResponseEntity.ok(feedback);
    }
}
