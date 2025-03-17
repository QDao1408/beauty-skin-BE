package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.entity.request.FeedbackRequest;
import online.beautyskin.beauty.entity.request.ImageRequest;
import online.beautyskin.beauty.enums.FeedbackEnums;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.repository.FeedbackRepository;
import online.beautyskin.beauty.repository.ImageRepository;
import online.beautyskin.beauty.repository.OrderDetailRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import online.beautyskin.beauty.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedBackService {
    @Autowired
    FeedbackRepository feedBackRepository;

    @Autowired
    UserUtils userUtils;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ImageRepository imageRepository;
    //create
    public Feedback createFeedback(FeedbackRequest feedbackRequest){
        OrderDetail orderDetail = orderDetailRepository.findById(feedbackRequest.getOrderDetailId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết đơn hàng!"));


        Order order = orderDetail.getOrder();
        // Kiểm tra trạng thái đơn hàng
        if (!OrderStatusEnums.DELIVERED.equals(order.getOrderStatus())) {
            throw new RuntimeException("Order chưa được giao, không thể đánh giá!");
        }

        // Kiểm tra xem user đã feedback chưa
        User user = userUtils.getCurrentUser();
//        orderDetail.getProduct().getFeedbacks().stream().forEach(feedback -> {
//            if (feedback.getUser().getId() == user.getId()) {
//                throw new RuntimeException("FEEDBACK RỒI AI CHO FEEDBACK NUA HẢ???");
//            }
//        });
        if (orderDetail.isFeedback()){
            throw new RuntimeException("FEEDBACK RỒI GÌ MÀ FEEDBACK HOÀI VẬY!!!");
        }

        // Tạo Feedback mới
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setRating(feedbackRequest.getRating());
        feedback.setComment(feedbackRequest.getComment());
        //set Image
        List<Image> images = new ArrayList<>();

        if (feedbackRequest.getImage() != null) {
            feedbackRequest.getImage().forEach(imageRequest -> {
                // Kiểm tra xem ảnh đã tồn tại chưa
                Image image = imageRepository.findByUrl(imageRequest.getUrl())
                        .orElseGet(() -> {
                            Image newImage = new Image();
                            newImage.setUrl(imageRequest.getUrl());
                            return imageRepository.save(newImage); // Lưu ảnh nếu chưa tồn tại
                        });

                images.add(image); // Thêm ảnh vào danh sách
            });
        }

        // Gán danh sách ảnh vào feedback
        feedback.setImages(images);

        feedback.setFeedBackDate(LocalDate.now());
        feedback.setProduct(orderDetail.getProduct());

        orderDetail.setFeedback(true);
        orderDetailRepository.save(orderDetail);
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
        List<Image> images = new ArrayList<>();

        if (feedbackRequest.getImage() != null) {
            feedbackRequest.getImage().forEach(imageRequest -> {
                // Kiểm tra xem ảnh đã tồn tại chưa
                Image image = imageRepository.findByUrl(imageRequest.getUrl())
                        .orElseGet(() -> {
                            Image newImage = new Image();
                            newImage.setUrl(imageRequest.getUrl());
                            return imageRepository.save(newImage); // Lưu ảnh nếu chưa tồn tại
                        });
                images.add(image); // Thêm ảnh vào danh sách
                });
            }

            // Gán danh sách ảnh vào feedback
            feedback.setImages(images);
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
