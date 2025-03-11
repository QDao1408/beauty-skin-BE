package online.beautyskin.beauty.service;

import lombok.extern.slf4j.Slf4j;
import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.entity.request.ProductRequest;
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

    public List<Product> getAllProducts() {
        return productRepository.findByIsDeletedFalse();
    }


    public Product getProductById(long id) {
        return productRepository.findByIdAndIsDeletedFalse(id);
    }

    // viết sql
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
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

        List<Promotion> promotions = addPromo(productRequest.getPromotions());
        product.setPromotions(promotions);

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

    public List<SkinConcern> addSkinConcern(List<Long> concernId) {
        List<SkinConcern> concerns = new ArrayList<>();
        if(!concernId.isEmpty()) {
            for(long id : concernId) {
                concerns.add(skinConcernRepository.findByIdAndIsDeletedFalse(id));
            }
        }
        return concerns;
    }

    public List<Tag> addProductTag(List<Long> tagId) {
        List<Tag> tags = new ArrayList<>();
        if(!tagId.isEmpty()) {
            for(long id : tagId) {
                tags.add(tagRepository.findByIdAndIsDeletedFalse(id));
            }
        }
        return tags;
    }

    public List<RoutineStep> addRoutineStep(List<Long> stepId) {
        List<RoutineStep> steps = new ArrayList<>();
        if(!stepId.isEmpty()) {
            for(long id : stepId) {
                steps.add(routineStepRepository.findByIdAndIsDeletedFalse(id));
            }
        }
        return steps;
    }

    public List<Form> addForm(List<Long> formId) {
        List<Form> forms = new ArrayList<>();
        if(!formId.isEmpty()) {
            for(long id : formId) {
                forms.add(formRepository.findByIdAndIsDeletedFalse(id));
            }
        }
        return forms;
    }

    public List<Image> addImage(List<Long> imageId) {
        List<Image> images = new ArrayList<>();
        if(!imageId.isEmpty()) {
            for(long id : imageId) {
                images.add(imageRepository.findByIdAndIsDeletedFalse(id));
            }
        }
        return images;
    }

    public List<Promotion> addPromo(List<Long> promoId) {
        List<Promotion> promotions = new ArrayList<>();
        if(!promoId.isEmpty()) {
            for(long id : promoId) {
                promotions.add(promotionRepository.findByIdAndIsDeletedFalse(id));
            }
        }
        return promotions;
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

        List<Promotion> promotions = addPromo(productRequest.getPromotions());
        product.setPromotions(promotions);

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


    public List<Product> getBySkinType(long skinTypeId) {
        List<Product> products = productRepository.findBySkinTypesIdAndIsDeletedFalse(skinTypeId);
        return products;
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
}
