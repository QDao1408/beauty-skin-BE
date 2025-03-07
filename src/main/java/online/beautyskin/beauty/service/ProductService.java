package online.beautyskin.beauty.service;

import jakarta.persistence.Access;
import lombok.extern.slf4j.Slf4j;
import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.entity.request.ProductRequest;
import online.beautyskin.beauty.repository.BrandRepository;
import online.beautyskin.beauty.repository.CategoryRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import online.beautyskin.beauty.repository.SkinTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SkinTypeRepository skinTypeRepository;

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
        List<SkinType> skinTypes = addSkinType(productrequest.getSkinTypeId());
        product.setSkinTypes(skinTypes);
        product.setDeleted(false);
        return productRepository.save(product);
    }

    public List<SkinType> addSkinType(List<Long> typeId) {
        List<SkinType> types = new ArrayList<>();
        if(!typeId.isEmpty()) {
            for(long id : typeId) {
                types.add(skinTypeRepository.findByIdAndIsDeletedFalse(id));
            }
        }
        return types;
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
        p1.setCategory(categoryRepository.findById(productRequest.getCategoryId()));
        p1.setBrand(brandRepository.findById(productRequest.getBrandId()));
        List<SkinType> types = addSkinType(productRequest.getSkinTypeId());
        p1.setSkinTypes(types);
        return productRepository.save(p1);
    }


    public List<Product> getFromCate(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category != null) {
            return productRepository.findByCategory(category);
        }
        return List.of();
    }

    public List<Product> getFromBrand(String brandName) {
        Brand brand = brandRepository.findByName(brandName);
        if (brand != null) {
            return productRepository.findByBrand(brand);
        }
        return List.of();
    }

    public List<Product> getBySkinType(long skinTypeId) {
        List<Product> products = productRepository.findBySkinTypesIdAndIsDeletedFalse(skinTypeId);
        return products;

    }
}
