package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.CartDetails;
import online.beautyskin.beauty.entity.CustomerCart;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.entity.request.CartDetailsRequest;
import online.beautyskin.beauty.entity.request.CustomerCartRequest;
import online.beautyskin.beauty.repository.CustomerCartRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import online.beautyskin.beauty.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerCartService {
    @Autowired
    private CustomerCartRepository customerCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserUtils userUtils;

    public List<CustomerCart> findAllCart() {
        List<CustomerCart> carts = customerCartRepository.findAll();
        if (carts.isEmpty()) {
            throw new RuntimeException("No cart found");
        }else {
            return carts;
        }
    }

    public CustomerCart getCartById(long customerId) {
        CustomerCart cart = customerCartRepository.findByUserId(customerId);
        if (cart == null) {
            throw new RuntimeException("No cart found");
        }else {
            return cart;
        }
    }

    public CustomerCart createCart(CustomerCartRequest customerCartRequest) {
        CustomerCart customerCart = new CustomerCart();
        customerCart.setQuantity(customerCartRequest.getQuantity());
        customerCart.setLastUpdate(customerCartRequest.getLastUpdate());
        //set user
        customerCart.setUser(userUtils.getCurrentUser());

        List<CartDetails> cartDetailsList = new ArrayList<>();
        for (CartDetailsRequest cartDetailsRequest : customerCartRequest.getCartDetails()) {
            CartDetails cartDetails = new CartDetails();
            cartDetails.setQuantity(cartDetailsRequest.getQuantity());
            cartDetails.setLastUpdate(LocalDateTime.now());

            // Fetch Product from Database
            Product product = productRepository.findById(cartDetailsRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            cartDetails.setProduct(product);
            cartDetails.setCustomerCart(customerCart);

            cartDetailsList.add(cartDetails);
        }

        customerCart.setCartDetails(cartDetailsList);
        return customerCartRepository.save(customerCart);
    }
}
