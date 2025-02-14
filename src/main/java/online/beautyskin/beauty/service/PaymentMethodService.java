package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.PaymentMethod;
import online.beautyskin.beauty.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    List<PaymentMethod> paymentMethods = new ArrayList<>();

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findByIsDeletedFalse();
    }

    public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    public List<PaymentMethod> createPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
        return paymentMethods;
    }

    public PaymentMethod deletePaymentMethod(long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id);
        paymentMethod.setDeleted(true);
        return paymentMethodRepository.save(paymentMethod);
    }


}
