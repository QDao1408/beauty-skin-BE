package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Feedback;
import online.beautyskin.beauty.repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackService {
    @Autowired
    FeedBackRepository feedBackRepository;

    //create
    public Feedback createFeedback(Feedback feedBack){
        return feedBackRepository.save(feedBack);
    }
    //remove
    public Feedback removeFeedback(long id){
        Feedback feedback = feedBackRepository.findFeedbackById(id);
        feedback.setDelete(true);
        return feedBackRepository.save(feedback);
    }
    //update

    //display
    public List<Feedback> getAll(){
        return feedBackRepository.findAll();
    }
    public List<Feedback> getFeedbackByDeleteIsFalse(){
        return feedBackRepository.findByIsDeleteFalse();
    }
    public Feedback getFeedbackById(long id){
        return feedBackRepository.findFeedbackById(id);
    }
}
