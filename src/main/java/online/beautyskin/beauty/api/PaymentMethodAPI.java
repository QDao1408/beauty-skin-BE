package online.beautyskin.beauty.api;

import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.PaymentMethod;
import online.beautyskin.beauty.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paymentMethod")
public class PaymentMethodAPI {
    @Autowired
    private PaymentMethodService paymentMethodService;



    @PostMapping("/create")
    public ResponseEntity createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        paymentMethodService.createPaymentMethod(paymentMethod);
        return ResponseEntity.ok(paymentMethod);
    }

    @GetMapping("/get")
    public ResponseEntity getPaymentMethod() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getPaymentMethods();
        return ResponseEntity.ok(paymentMethods);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePaymentMethod(@Valid @RequestBody PaymentMethod paymentMethod, @PathVariable long id) {
        paymentMethodService.updatePaymentMethod(paymentMethod);
        return ResponseEntity.ok(paymentMethod);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePaymentMethod(long id) {
        PaymentMethod paymentMethod = paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.ok(paymentMethod);
    }
}
