package online.beautyskin.beauty.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.LoyaltyPoint;
import online.beautyskin.beauty.service.LoyaltyPointService;


@RestController
@RequestMapping("/api/loyalty-point")
@SecurityRequirement(name = "api")
public class LoyaltyPointAPI {
    
    @Autowired
    private LoyaltyPointService loyaltyPointService;

    @GetMapping("/gets")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(loyaltyPointService.getAll());
    }

    @GetMapping("/get-user-by-rank/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity getUserByRank(@PathVariable long id) {
        return ResponseEntity.ok(loyaltyPointService.getUserByRank(id));
    }

    @PostMapping("/create-rank")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity createRank(@RequestBody LoyaltyPoint loyaltyPoint) {
        return ResponseEntity.ok(loyaltyPointService.createRank(loyaltyPoint));
    }
    
    
}
