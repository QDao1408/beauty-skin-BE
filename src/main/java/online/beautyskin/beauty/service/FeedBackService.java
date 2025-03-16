package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.entity.request.FeedbackRequest;
import online.beautyskin.beauty.enums.FeedbackEnums;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.repository.FeedBackRepository;
import online.beautyskin.beauty.repository.OrderDetailRepository;
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

    @Autowired
    OrderDetailRepository orderDetailRepository;
    //create
    public Feedback createFeedback(FeedbackRequest feedbackRequest){
        OrderDetail orderDetail = orderDetailRepository.findById(feedbackRequest.getOrderDetailId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết đơn hàng!"));

        Product product = orderDetail.getProduct();
        Order order = orderDetail.getOrder();

        // Kiểm tra trạng thái đơn hàng
        if (!OrderStatusEnums.DELIVERED.equals(order.getOrderStatus())) {
            throw new RuntimeException("Order chưa được giao, không thể đánh giá!");
        }

        // Kiểm tra xem user đã feedback chưa
        User user = userUtils.getCurrentUser();
        boolean hasFeedback = product.getFeedbacks()
                .stream()
                .anyMatch(feedback -> feedback.getUser().getId().equals(user.getId()));

        if (hasFeedback) {
            throw new RuntimeException("Bạn đã đánh giá sản phẩm này rồi!");
        }

        // Tạo Feedback mới
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setRating(feedbackRequest.getRating());
        feedback.setComment(feedbackRequest.getComment());
        feedback.setImage(feedbackRequest.getImage());
        feedback.setFeedBackDate(LocalDate.now());
        feedback.setProduct(product);
        feedback.setFeedbackStatus(FeedbackEnums.SUCCESS);

        return feedBackRepository.save(feedback);
    }

    //remove
    public Feedback removeFeedback(long id){
        Feedback feedback = feedBackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Feedback!"));
        feedback.setDelete(true);
        return feedBackRepository.save(feedback);
    }
    //update
    public Feedback updateFeedback(long id, FeedbackRequest feedbackRequest){
        Feedback feedback = feedBackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Feedback!"));

        // Kiểm tra quyền user
        User currentUser = userUtils.getCurrentUser();
        if (!feedback.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Bạn không có quyền chỉnh sửa feedback này!");
        }

            feedback.setRating(feedbackRequest.getRating());
            feedback.setComment(feedbackRequest.getComment());
            feedback.setImage(feedbackRequest.getImage());
            feedback.setFeedBackDate(LocalDate.now());
            return feedBackRepository.save(feedback);

    }

    //display
    public List<Feedback> getAll(){
        return feedBackRepository.findAll();
    }
    public List<Feedback> getFeedbackByDeleteIsFalse(){
        return feedBackRepository.findByIsDeleteFalse();
    }
    public List<Feedback> getFeedbackByProductId(long productId){
        return feedBackRepository.findByProductId(productId);
    }
}
