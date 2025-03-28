package online.beautyskin.beauty.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.beautyskin.beauty.entity.Image;
import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.Report;
import online.beautyskin.beauty.entity.request.ImageRequest;
import online.beautyskin.beauty.entity.request.ReportRequest;
import online.beautyskin.beauty.repository.ImageRepository;
import online.beautyskin.beauty.repository.OrderRepository;
import online.beautyskin.beauty.repository.ReportRepository;
import online.beautyskin.beauty.utils.UserUtils;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ImageRepository imageRepository;
    
    @Autowired
    private UserUtils userUtils;

    public Report create(ReportRequest request) {
        Report report = new Report();
        Order order = orderRepository.findOrderById(request.getOrderId());
        report.setDescription(request.getDescription());
        report.setReason(request.getReason());
        report.setOrder(order);
        report.setImages(generateImage(request.getImages()));
        report.setReportDate(LocalDateTime.now());
        report.setRefund(order.getTotalPrice());
        report.setCustomer(userUtils.getCurrentUser());
        report.setApproved(false);
        reportRepository.save(report);
        return report;
    }

    public List<Image> generateImage(List<ImageRequest> requests) {
        List<Image> images = new ArrayList<>();

        if (requests != null) {
            requests.forEach(imageRequest -> {
                Image image = imageRepository.findByUrl(imageRequest.getUrl())
                        .orElseGet(() -> {
                            Image newImage = new Image();
                            newImage.setUrl(imageRequest.getUrl());
                            return imageRepository.save(newImage); // Lưu ảnh nếu chưa tồn tại
                        });

                images.add(image); // Thêm ảnh vào danh sách
            });
        }
        return images;
    }

}
