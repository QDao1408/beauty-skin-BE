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



@RestController
@RequestMapping("/api/promotion")
@SecurityRequirement(name = "api")
public class PromotionAPI {
    @Autowired
    private PromotionService promotionService;


    @GetMapping("/getValid")
    public ResponseEntity getValid() {
        List<PromoResponse> responses = promotionService.getValidPromotions();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/get-promotion-by-rank/{rankId}")
    public ResponseEntity getPromotionByRank(@PathVariable long rankId) {
        List<Promotion> promotions = promotionService.getPromotionByRank(rankId);
        return ResponseEntity.ok(promotions);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity create(@RequestBody PromoRequest promotion) {
        Promotion promo1 = promotionService.createPromotion(promotion);
        return ResponseEntity.ok(promo1);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity delete(@PathVariable long id) {
        Promotion del = promotionService.deletePromotion(id);
        return ResponseEntity.ok(del);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity update(@RequestBody PromoRequest promotion, @PathVariable long id) {
        Promotion upd = promotionService.updatePromotion(promotion, id);
        return ResponseEntity.ok(upd);
    }

    
}
