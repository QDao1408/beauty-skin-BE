package online.beautyskin.beauty.service;

import jakarta.transaction.Transactional;
import online.beautyskin.beauty.entity.CartDetails;
import online.beautyskin.beauty.entity.CustomerCart;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.repository.CartDetailsRepository;
import online.beautyskin.beauty.repository.CustomerCartRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import online.beautyskin.beauty.repository.UserRepository;
import online.beautyskin.beauty.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerCartService {
    @Autowired
    private CustomerCartRepository customerCartRepository;

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtils userUtils;

    @Transactional
    public void addProductToCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        CustomerCart cart = customerCartRepository.findByUser(user).orElseGet(() -> {
            CustomerCart newCart = new CustomerCart();
            newCart.setUser(user);
            return customerCartRepository.save(newCart);
        });

        Optional<CartDetails> existingDetail = cartDetailsRepository.findByCustomerCartAndProduct(cart, product);
        if (existingDetail.isPresent()) {
            CartDetails cartDetail = existingDetail.get();
            cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
            cartDetail.updateSubtotal();
            cartDetailsRepository.save(cartDetail);
        } else {
            CartDetails cartDetail = new CartDetails(cart, product, quantity, BigDecimal.valueOf(product.getPrice() * quantity));
            cart.getCartDetails().add(cartDetail);
            cartDetail.setCustomerCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setQuantity(quantity);
            cartDetail.updateSubtotal();
            cartDetailsRepository.save(cartDetail);
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartDetails cartDetail : cart.getCartDetails()) {
            BigDecimal subtotal = cartDetail.getSubtotal() != null ? cartDetail.getSubtotal() : BigDecimal.ZERO;
            totalPrice = totalPrice.add(subtotal);
        }
        cart.setTotalPrice(totalPrice);

        cart.setLastUpdate(LocalDateTime.now());
        customerCartRepository.save(cart);
    }

    @Transactional
    public void removeProductFromCart(Long userId, Long productId) {
        CustomerCart cart = customerCartRepository.findByUser(userRepository.findById(userId).orElseThrow()).orElseThrow();
        CartDetails cartDetail = cartDetailsRepository.findByCustomerCartAndProduct(cart, productRepository.findById(productId).orElseThrow()).orElseThrow();
        cart.getCartDetails().remove(cartDetail);
        cartDetailsRepository.delete(cartDetail);
        cart.setLastUpdate(LocalDateTime.now());
        customerCartRepository.save(cart);
    }

    public Optional<CustomerCart> getCart() {
        User currentUser = userUtils.getCurrentUser();
        Optional<CustomerCart> customerCart = customerCartRepository.findByUser(currentUser);
        return customerCart;
    }
}
