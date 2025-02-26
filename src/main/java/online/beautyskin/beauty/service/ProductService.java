package online.beautyskin.beauty.service;

import jakarta.persistence.Access;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.entity.request.ProductRequest;
import online.beautyskin.beauty.repository.BrandRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<Product> getAllProducts() {
        return productRepository.findByIsDeletedFalse();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id);
    }

    // viết sql
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product createProduct(ProductRequest productrequest) {
        Product product = new Product();
        product.setName(productrequest.getName());
        product.setDescription(productrequest.getDescription());
        product.setBrand(brandRepository.findById(productrequest.getBrandId()));
        product.setStock(productrequest.getStock());
        product.setCreateDateTime(productrequest.getCreateDateTime());
        product.setLastUpdateDateTime(productrequest.getLastUpdateDateTime());
        product.setExpiredDateTime(productrequest.getExpiredDateTime());
        product.setStatus(productrequest.getStatus());
        product.setInstruction(productrequest.getInstruction());
        product.setDeleted(false);

        return productRepository.save(product);
    }

    public Product deleteProduct(long id) {
        Product p1 = productRepository.findById(id);
        p1.setDeleted(true);
        return productRepository.save(p1);
    }

    public Product updateProduct(long id, ProductRequest productRequest) {
        Product p1 = productRepository.findById(id);
        p1.setName(productRequest.getName());
        p1.setDescription(productRequest.getDescription());
        p1.setStock(productRequest.getStock());
        p1.setCreateDateTime(productRequest.getCreateDateTime());
        p1.setLastUpdateDateTime(productRequest.getLastUpdateDateTime());
        p1.setExpiredDateTime(productRequest.getExpiredDateTime());
        p1.setStatus(productRequest.getStatus());
        p1.setInstruction(productRequest.getInstruction());
        p1.setDeleted(false);
        p1.setBrand(brandRepository.findById(productRequest.getBrandId()));
        return productRepository.save(p1);
    }



}
