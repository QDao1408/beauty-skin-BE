package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.Promotion;
import online.beautyskin.beauty.entity.request.PromoRequest;
import online.beautyskin.beauty.entity.respone.PromoResponse;
import online.beautyskin.beauty.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/promotion")
@SecurityRequirement(name = "api")
public class PromotionAPI {
    @Autowired
    private PromotionService promotionService;



    List<Promotion> promotions;


    @GetMapping("/getValid")
    public ResponseEntity getValid() {
        List<PromoResponse> responses = promotionService.getValidPromotions();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity create(@RequestBody PromoRequest promotion) {
        Promotion promo1 = promotionService.createPromotion(promotion);
        return ResponseEntity.ok(promo1);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity delete(@RequestBody Promotion promotion, @PathVariable long id) {
        Promotion del = promotionService.deletePromotion(promotion);
        return ResponseEntity.ok(del);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity update(@RequestBody Promotion promotion, @PathVariable long id) {
        Promotion upd = promotionService.updatePromotion(promotion);
        return ResponseEntity.ok(upd);
    }

    
}
