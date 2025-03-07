package online.beautyskin.beauty.service;

import jakarta.persistence.Access;
import lombok.extern.slf4j.Slf4j;
import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.entity.request.ProductRequest;
import online.beautyskin.beauty.repository.*;
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

    @Autowired
    private SkinConcernRepository skinConcernRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private RoutineStepRepository routineStepRepository;

    @Autowired
    private FormRepository formRepository;

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

    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setBrand(brandRepository.findById(productRequest.getBrandId()));
        product.setStock(productRequest.getStock());
        product.setCreateDateTime(productRequest.getCreateDateTime());
        product.setLastUpdateDateTime(productRequest.getLastUpdateDateTime());
        product.setExpiredDateTime(productRequest.getExpiredDateTime());
        product.setStatus(productRequest.getStatus());
        product.setInstruction(productRequest.getInstruction());

        List<SkinType> skinTypes = addSkinType(productRequest.getSkinTypeId());
        product.setSkinTypes(skinTypes);

        List<SkinConcern> skinConcerns = addSkinConcern(productRequest.getSkinConcernId());
        product.setSkinConcerns(skinConcerns);

        List<Tag> tags = addProductTag(productRequest.getTagId());
        product.setTags(tags);

        List<RoutineStep> steps = addRoutineStep(productRequest.getRoutineSteps());
        product.setRoutineSteps(steps);

        List<Form> forms = addForm(productRequest.getForms());
        product.setForms(forms);

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

        List<SkinConcern> concerns = addSkinConcern(productRequest.getSkinConcernId());
        p1.setSkinConcerns(concerns);

        List<Tag> tags = addProductTag(productRequest.getTagId());
        p1.setTags(tags);

        List<RoutineStep> steps = addRoutineStep(productRequest.getRoutineSteps());
        p1.setRoutineSteps(steps);

        List<Form> forms = addForm(productRequest.getForms());
        p1.setForms(forms);

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
