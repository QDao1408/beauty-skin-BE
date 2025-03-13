package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Favorites;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.repository.FavoritesRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import online.beautyskin.beauty.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FavoritesService {

    @Autowired
    FavoritesRepository favoritesRepository;

    @Autowired
    UserUtils userUtils;

    @Autowired
    private ProductRepository productRepository;

    public String addFavorites(long productId) {
        User user = userUtils.getCurrentUser();
        Favorites favorites = favoritesRepository.findByUserId(user.getId()).orElse(null);
        if (favorites == null) {
            favorites = new Favorites();
            favorites.setUser(user);
            favorites.setFavoriteProducts(new ArrayList<>());
        }

        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        if (favorites.getFavoriteProducts().contains(product)) {
            throw new RuntimeException("Favorite product already exists");
        }

        favorites.getFavoriteProducts().add(product);
        favoritesRepository.save(favorites);
        return "Added to favorites";
    }

    public String removeFavorites(long productId) {
        User user = userUtils.getCurrentUser();
        Favorites favorites = favoritesRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Favorites list not found"));

        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        if (!favorites.getFavoriteProducts().contains(product)) {
            return "Product is not in favorites";
        }

        favorites.getFavoriteProducts().remove(product);
        favoritesRepository.save(favorites);
        return "Removed from favorites";
    }

    public List<Product> getFavorites() {
        User user = userUtils.getCurrentUser();
        return favoritesRepository.findByUserId(user.getId())
                .map(Favorites::getFavoriteProducts)
                .orElse(Collections.emptyList());
    }
}
