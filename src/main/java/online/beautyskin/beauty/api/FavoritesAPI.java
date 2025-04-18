package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/favorites")
@SecurityRequirement(name = "api")
public class FavoritesAPI {
    @Autowired
    FavoritesService favoritesService;

    @PostMapping("/addToFavorites/{productId}")
    @PreAuthorize("hasAnyAuthority('User')")
    public ResponseEntity addFavorites(@PathVariable long productId) {
        String response = favoritesService.addFavorites(productId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("removeFromFavorites/{productId}")
    @PreAuthorize("hasAnyAuthority('User')")
    public ResponseEntity removeFavorites(@PathVariable long productId) {
        String response = favoritesService.removeFavorites(productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getFavorites")
    @PreAuthorize("hasAnyAuthority('User')")
    public ResponseEntity getFavorites() {
        List<Product> favorites = favoritesService.getFavorites();
        return ResponseEntity.ok(favorites);
    }
}
