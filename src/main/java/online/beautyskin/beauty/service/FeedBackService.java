package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Feedback;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.FeedbackRequest;
import online.beautyskin.beauty.repository.FeedBackRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import online.beautyskin.beauty.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeedBackService {
    @Autowired
    FeedBackRepository feedBackRepository;

    @Autowired
    UserUtils userUtils;

    @Autowired
    ProductRepository productRepository;
    //create
    public Feedback createFeedback(long productId, FeedbackRequest feedbackRequest){
        User user = userUtils.getCurrentUser();
        Product product = productRepository.findById(productId);
        if (product == null){
            throw new RuntimeException("Product not found");
        }else {
            Feedback feedback = new Feedback();
            feedback.setRating(feedbackRequest.getRating());
            feedback.setComment(feedbackRequest.getComment());
            feedback.setFeedBackDate(LocalDate.now());
            feedback.setUser(user);
            feedback.setProduct(product);
            product.setFeedbacks(List.of(feedback));
            return feedBackRepository.save(feedback);
        }
    }
    //remove
    public Feedback removeFeedback(long id){
        Feedback feedback = feedBackRepository.findFeedbackById(id);
        feedback.setDelete(true);
        return feedBackRepository.save(feedback);
    }
    //update
    public Feedback updateFeedback(long id, FeedbackRequest feedbackRequest){
        Feedback feedback = feedBackRepository.findFeedbackById(id);
        if (feedback == null){
            throw new RuntimeException("Feedback not found");
        }else {
            feedback.setRating(feedbackRequest.getRating());
            feedback.setComment(feedbackRequest.getComment());
            feedback.setFeedBackDate(LocalDate.now());
            feedback.setUser(userUtils.getCurrentUser());
            return feedBackRepository.save(feedback);
        }
    }

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
