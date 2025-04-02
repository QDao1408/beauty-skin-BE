package online.beautyskin.beauty.service;

import lombok.extern.slf4j.Slf4j;
import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.entity.request.ProductRequest;
import online.beautyskin.beauty.entity.respone.ProductResponse;
import online.beautyskin.beauty.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SkinTypeRepository skinTypeRepository;

    @Autowired
    private SkinConcernRepository skinConcernRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private RoutineStepRepository routineStepRepository;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findByIsDeletedFalse();
        List<ProductResponse> productResponses = new ArrayList<>();
        if (products.isEmpty()) {
            throw new RuntimeException("Product list is empty");
        }else {
            for (Product product : products) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setId(product.getId());
                productResponse.setName(product.getName());
                productResponse.setDescription(product.getDescription());
                productResponse.setStock(product.getStock());
                productResponse.setCreateDateTime(product.getCreateDateTime());
                productResponse.setLastUpdateDateTime(product.getLastUpdateDateTime());
                productResponse.setExpiredDateTime(product.getExpiredDateTime());
                productResponse.setStatus(product.getStatus());
                productResponse.setInstruction(product.getInstruction());
                productResponse.setPrice(product.getPrice());
                productResponse.setIngredient(product.getIngredient());
                productResponse.setCategory(product.getCategory());
                productResponse.setSkinTypes(product.getSkinTypes());
                productResponse.setSkinConcerns(product.getSkinConcerns());
                productResponse.setTags(product.getTags());
                productResponse.setForms(product.getForms());
                productResponse.setRoutineSteps(product.getRoutineSteps());
                productResponse.setImages(product.getImages());
                productResponse.setFavoritedByUsers(product.getFavoritedByUsers());
                productResponse.setAverageRating(productRepository.findAverageRatingByProductId(product.getId()));
                productResponse.setProductSold(productRepository.findTotalSoldByProductId(product.getId()));
                productResponses.add(productResponse);
            }
        }
        return productResponses;
    }


    public ProductResponse getProductById(long id) {
        Product product = productRepository.findByIdAndIsDeletedFalse(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }else{
            ProductResponse productResponse = new ProductResponse();
            mappingProductToProductResponse(product, productResponse);
            return productResponse;
        }
    }

    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setStock(productRequest.getStock());
        product.setCreateDateTime(productRequest.getCreateDateTime());
        product.setLastUpdateDateTime(productRequest.getLastUpdateDateTime());
        product.setExpiredDateTime(productRequest.getExpiredDateTime());
        product.setStatus(productRequest.getStatus());
        product.setInstruction(productRequest.getInstruction());
        product.setPrice(productRequest.getPrice());
        product.setIngredient(productRequest.getIngredient());
        product.setCategory(categoryRepository.findById(productRequest.getCategoryId()));
        product.setPromotion(productRequest.getPromotion());

        List<SkinType> skinTypes = addSkinType(productRequest.getSkinTypeId());
        product.setSkinTypes(skinTypes);

        List<SkinConcern> skinConcerns = addSkinConcern(productRequest.getSkinConcernId());
        product.setSkinConcerns(skinConcerns);

        List<Tag> tags = addProductTag(productRequest.getTagId());
        product.setTags(tags);

        List<RoutineStep> steps = addRoutineStep(productRequest.getRoutineSteps());
        product.setRoutineSteps(steps);

        List<Form> forms = addForm(productRequest.getFormIds());
        product.setForms(forms);

        List<Image> images = addImage(productRequest.getImages());
        product.setImages(images);

        product.setDeleted(false);
        return productRepository.save(product);
    }

    public List<SkinType> addSkinType(List<Long> typeId) {
        List<SkinType> types = new ArrayList<>();
        if(!typeId.isEmpty()) {
            for(long id : typeId) {
                types.add(skinTypeRepository.findByIdAndIsDeletedFalse(id));
            }
        } else {
            return null;
        }
        return types;
    }

    public List<SkinConcern> addSkinConcern(List<Long> concernId) {
        List<SkinConcern> concerns = new ArrayList<>();
        if(!concernId.isEmpty()) {
            for(long id : concernId) {
                concerns.add(skinConcernRepository.findByIdAndIsDeletedFalse(id));
            }
        } else {
            return null;
        }
        return concerns;
    }

    public List<Tag> addProductTag(List<Long> tagId) {
        List<Tag> tags = new ArrayList<>();
        if(!tagId.isEmpty()) {
            for(long id : tagId) {
                tags.add(tagRepository.findByIdAndIsDeletedFalse(id));
            }
        } else {
            return null;
        }
        return tags;
    }

    public List<RoutineStep> addRoutineStep(List<Long> stepId) {
        List<RoutineStep> steps = new ArrayList<>();
        if(!stepId.isEmpty()) {
            for(long id : stepId) {
                steps.add(routineStepRepository.findByIdAndIsDeletedFalse(id));
            }
        } else {
            return null;
        }
        return steps;
    }

    public List<Form> addForm(List<Long> formId) {
        List<Form> forms = new ArrayList<>();
        if(!formId.isEmpty()) {
            for(long id : formId) {
                forms.add(formRepository.findByIdAndIsDeletedFalse(id));
            }
        } else {
            return null;
        }
        return forms;
    }

    public List<Image> addImage(List<Long> imageId) {
        List<Image> images = new ArrayList<>();
        if(!imageId.isEmpty()) {
            for(long id : imageId) {
                images.add(imageRepository.findByIdAndIsDeletedFalse(id));
            }
        } else {
            return null;
        }
        return images;
    }

    public Product deleteProduct(long id) {
        Product p1 = productRepository.findById(id);
        p1.setDeleted(true);
        return productRepository.save(p1);
    }

    public Product updateProduct(long id, ProductRequest productRequest) {

        Product product = productRepository.findById(id);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setStock(productRequest.getStock());
        product.setCreateDateTime(productRequest.getCreateDateTime());
        product.setLastUpdateDateTime(productRequest.getLastUpdateDateTime());
        product.setExpiredDateTime(productRequest.getExpiredDateTime());
        product.setStatus(productRequest.getStatus());
        product.setInstruction(productRequest.getInstruction());
        product.setPrice(productRequest.getPrice());
        product.setIngredient(productRequest.getIngredient());
        product.setCategory(categoryRepository.findById(productRequest.getCategoryId()));

        List<SkinType> skinTypes = addSkinType(productRequest.getSkinTypeId());
        product.setSkinTypes(skinTypes);

        List<SkinConcern> skinConcerns = addSkinConcern(productRequest.getSkinConcernId());
        product.setSkinConcerns(skinConcerns);

        List<Tag> tags = addProductTag(productRequest.getTagId());
        product.setTags(tags);

        List<RoutineStep> steps = addRoutineStep(productRequest.getRoutineSteps());
        product.setRoutineSteps(steps);

        List<Form> forms = addForm(productRequest.getFormIds());
        product.setForms(forms);

        List<Image> images = addImage(productRequest.getImages());
        product.setImages(images);

        product.setDeleted(false);

        return productRepository.save(product);
    }


    public List<Product> getFromCate(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category != null) {
            return productRepository.findByCategory(category);
        }
        return List.of();
    }


    public List<ProductResponse> getBySkinType(long skinTypeId) {
        List<Product> products = productRepository.findBySkinTypesIdAndIsDeletedFalse(skinTypeId);
        if (products.isEmpty()) {
            throw new RuntimeException("Product list is empty");
        }else {
            List<ProductResponse> productResponses = new ArrayList<>();
            for (Product product : products) {
                ProductResponse productResponse = new ProductResponse();
                mappingProductToProductResponse(product, productResponse);
                productResponses.add(productResponse);
            }
            return productResponses;
        }

    }

    public List<Product> getBySkinConcern(long concernId) {
        List<Product> products = productRepository.findBySkinConcernsIdAndIsDeletedFalse(concernId);
        return products;
    }

    public List<Product> getByTag(long tagId) {
        List<Product> products = productRepository.findByTagsIdAndIsDeletedFalse(tagId);
        return products;
    }

    public List<Product> getByRoutineStep(long stepId) {
        List<Product> products = productRepository.findByRoutineStepsIdAndIsDeletedFalse(stepId);
        return products;
    }

    public List<Product> getByForm(long id) {
        List<Product> products = productRepository.findByFormsIdAndIsDeletedFalse(id);
        return products;
    }

    public List<Product> getProductsByName(String name) {
        List<Product> products = productRepository.findByName(name);
        return products;
    }

    public List<ProductResponse> getFromCateId(long id) {
        List<Product> products = productRepository.getByCategory(categoryRepository.findById(id));

        if (products.isEmpty()) {
            throw new RuntimeException("Product list is empty");
        }else {
            List<ProductResponse> productResponses = new ArrayList<>();
            for (Product product : products) {
                ProductResponse productResponse = new ProductResponse();
                mappingProductToProductResponse(product, productResponse);
                productResponses.add(productResponse);
            }
            return productResponses;
        }
    }

    public ProductResponse mappingProductToProductResponse(Product product, ProductResponse productResponse) {
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setStock(product.getStock());
        productResponse.setCreateDateTime(product.getCreateDateTime());
        productResponse.setLastUpdateDateTime(product.getLastUpdateDateTime());
        productResponse.setExpiredDateTime(product.getExpiredDateTime());
        productResponse.setStatus(product.getStatus());
        productResponse.setInstruction(product.getInstruction());
        productResponse.setPrice(product.getPrice());
        productResponse.setIngredient(product.getIngredient());
        productResponse.setCategory(product.getCategory());
        productResponse.setSkinTypes(product.getSkinTypes());
        productResponse.setSkinConcerns(product.getSkinConcerns());
        productResponse.setTags(product.getTags());
        productResponse.setForms(product.getForms());
        productResponse.setRoutineSteps(product.getRoutineSteps());
        productResponse.setImages(product.getImages());
        productResponse.setFavoritedByUsers(product.getFavoritedByUsers());
        productResponse.setAverageRating(productRepository.findAverageRatingByProductId(product.getId()));
        productResponse.setProductSold(productRepository.findTotalSoldByProductId(product.getId()));
        return productResponse;
    }
}
