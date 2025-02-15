package online.beautyskin.beauty.api;

import online.beautyskin.beauty.entity.Promotion;
import online.beautyskin.beauty.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotion")
public class PromotionAPI {
    @Autowired
    private PromotionService promotionService;

    List<Promotion> promotions;

    @GetMapping("/getAll")
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
    public ResponseEntity create(@RequestBody Promotion promotion) {
        Promotion promo1 = promotionService.createPromotion(promotion);
        return ResponseEntity.ok(promo1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Promotion promotion, @PathVariable long id) {
        Promotion del = promotionService.deletePromotion(promotion);
        return ResponseEntity.ok(del);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Promotion promotion, @PathVariable long id) {
        Promotion upd = promotionService.updatePromotion(promotion);
        return ResponseEntity.ok(upd);
    }



}
