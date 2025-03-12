package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.Promotion;
import online.beautyskin.beauty.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotion")
@SecurityRequirement(name = "api")
public class PromotionAPI {
    @Autowired
    private PromotionService promotionService;

    List<Promotion> promotions;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity getAll() {
        promotions = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotions);
    }

    @GetMapping("/getValid")
    public ResponseEntity getValid() {
        promotions = promotionService.getValidPromotions();
        return ResponseEntity.ok(promotions);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity create(@RequestBody Promotion promotion) {
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
