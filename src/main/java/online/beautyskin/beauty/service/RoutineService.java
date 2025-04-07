package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.entity.request.ProductRequestRoutine;
import online.beautyskin.beauty.entity.request.RoutineRequest;
import online.beautyskin.beauty.entity.request.RoutineStepRequest;
import online.beautyskin.beauty.entity.respone.*;
import online.beautyskin.beauty.repository.ProductRepository;
import online.beautyskin.beauty.repository.RoutineRepository;
import online.beautyskin.beauty.repository.RoutineStepRepository;
import online.beautyskin.beauty.repository.SkinTypeRepository;
import online.beautyskin.beauty.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoutineService {
    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SkinTypeRepository skinTypeRepository;

    @Autowired
    private RoutineStepRepository routineStepRepository;

    @Autowired
    private UserUtils userUtils;

    public Routine createRoutine(RoutineRequest routineRequest) {
        // Tạo mới routine
        Routine routine = new Routine();
        routine.setName(routineRequest.getName());
        routine.setDescription(routineRequest.getDescription());
        routine.setSkinType(skinTypeRepository.findById(routineRequest.getSkinTypeId()).orElse(null));
        routine.setLastUpdate(LocalDateTime.now());

        // Lưu routine vào cơ sở dữ liệu
        routine = routineRepository.save(routine);

        // Lấy danh sách các bước trong routine
        List<RoutineStepRequest> routineStepRequests = routineRequest.getRoutineStepRequests();
        List<RoutineStep> routineSteps = new ArrayList<>();

        for (RoutineStepRequest routineStepRequest : routineStepRequests) {
            RoutineStep routineStep = new RoutineStep();
            routineStep.setDescription(routineStepRequest.getDescription());
            routineStep.setStepName(routineStepRequest.getStepName());
            routineStep.setStepOrder(routineStepRequest.getStepOrder());
            routineStep.setRoutine(routine);

            // Danh sách sản phẩm cho mỗi bước
            List<ProductRequestRoutine> productRequestRoutines = routineStepRequest.getProducts();
            List<Product> products = new ArrayList<>();

            for (ProductRequestRoutine productRequestRoutine : productRequestRoutines) {
                Product product = productRepository.findById(productRequestRoutine.getId());
                if (product == null) {
                    throw new RuntimeException("Product not found");
                }else {
                    product.setRoutineSteps(routineSteps);  // Thiết lập mối quan hệ với RoutineSteps
                    products.add(product);
                }
            }
            routineStep.setProducts(products);  // Thiết lập sản phẩm cho RoutineStep
            routineSteps.add(routineStep);
        }
        // Cập nhật danh sách steps vào routine
        routine.setRoutineSteps(routineSteps);
        routineRepository.save(routine);  // Lưu lại routine với các steps
        return routineRepository.save(routine);
    }

    public Routine updateRoutine(Long id, Long skinTypeId, String name, String description) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Routine not found"));
        SkinType skinType = skinTypeRepository.findById(skinTypeId)
                .orElseThrow(() -> new RuntimeException("SkinType not found"));
        routine.setName(name);
        routine.setDescription(description);
        routine.setLastUpdate(LocalDateTime.now());
        routine.setSkinType(skinType);
        routine = routineRepository.save(routine);
        return routineRepository.save(routine);
    }

    public void deleteRoutine(Long id) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Routine not found"));
        routine.setDeleted(true);
        routine = routineRepository.save(routine);
    }

    public RoutineStep createRoutineStep(Long routineId, RoutineStepRequest routineStepRequest) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException("Routine step not found"));
        RoutineStep routineStep = new RoutineStep();
        routineStep.setDescription(routineStepRequest.getDescription());
        routineStep.setStepName(routineStepRequest.getStepName());
        routineStep.setStepOrder(routineStepRequest.getStepOrder());
        routineStep.setRoutine(routine);
        routineStep = routineStepRepository.save(routineStep);
        routineStep.setStepOrder(routineStepRequest.getStepOrder());
        List<ProductRequestRoutine> productRequestRoutines = routineStepRequest.getProducts();
        List<Product> products = new ArrayList<>();
        for (ProductRequestRoutine productRequestRoutine : productRequestRoutines) {
            Product product = productRepository.findById(productRequestRoutine.getId());
            products.add(product);
        }
        routineStep.setProducts(products);
        routine = routineRepository.save(routine);
        return routineStep;
    }

    public RoutineStep updateRoutineStep(Long id, RoutineStepRequest routineStepRequest) {
        RoutineStep routineStep = routineStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Routine step not found"));
        routineStep.setDescription(routineStepRequest.getDescription());
        routineStep.setStepName(routineStepRequest.getStepName());
        routineStep.setStepOrder(routineStepRequest.getStepOrder());
        routineStep = routineStepRepository.save(routineStep);
        routineStep.setStepOrder(routineStepRequest.getStepOrder());
        List<ProductRequestRoutine> productRequestRoutines = routineStepRequest.getProducts();
        if (productRequestRoutines != null) {
            List<Product> products = new ArrayList<>();
            for (ProductRequestRoutine productRequestRoutine : productRequestRoutines) {
                Product product = productRepository.findById(productRequestRoutine.getId());
                products.add(product);
            }
            routineStep.setProducts(products);
        }
        routineStep = routineStepRepository.save(routineStep);
    return routineStep;
    }

    public void deleteRoutineStep(Long id) {
        RoutineStep routineStep = routineStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Routine step not found"));
        routineStep.setDeleted(true);
        routineStep = routineStepRepository.save(routineStep);
    }

    public List<RoutineResponse> getAll() {
        List<RoutineResponse> routinesResponse = new ArrayList<>();
        List<Routine> routines = routineRepository.findByIsDeletedFalse();
        for (Routine routine : routines) {
            RoutineResponse routineResponse = new RoutineResponse();
            routineResponse.setId(routine.getId());
            routineResponse.setName(routine.getName());
            routineResponse.setDescription(routine.getDescription());

            SkinTypeResponse skinTypeResponse = new SkinTypeResponse();
            skinTypeResponse.setTypeId(routine.getSkinType().getId());
            skinTypeResponse.setType(routine.getSkinType().getName());
            routineResponse.setSkinTypeResponse(skinTypeResponse);

            List<RoutineStep> routineSteps = routine.getRoutineSteps();
            List<RoutineStepResponse> routineStepResponses = new ArrayList<>();
            for (RoutineStep routineStep : routineSteps) {
                if (!routineStep.isDeleted()) {
                    RoutineStepResponse routineStepResponse = new RoutineStepResponse();
                    routineStepResponse.setId(routineStep.getId());
                    routineStepResponse.setDescription(routineStep.getDescription());
                    routineStepResponse.setStepName(routineStep.getStepName());
                    routineStepResponse.setStepOrder(routineStep.getStepOrder());
                    List<Product> products = routineStep.getProducts();
                    List<ProductResponseForRoutine> productResponsesForRoutines = new ArrayList<>();
                    for (Product product : products) {
                        ProductResponseForRoutine productResponseForRoutine = new ProductResponseForRoutine();
                        productResponseForRoutine.setId(product.getId());
                        productResponseForRoutine.setName(product.getName());
                        productResponsesForRoutines.add(productResponseForRoutine);
                    }
                    routineStepResponse.setProductResponse(productResponsesForRoutines);
                    routineStepResponses.add(routineStepResponse);
                }
            }
            routineResponse.setRoutineStepResponse(routineStepResponses);
            routinesResponse.add(routineResponse);
        }
        return routinesResponse;
    }

    public RoutineResponse getRoutine(Long skinTypeId) {
        RoutineResponse routinesResponse = new RoutineResponse();
        Routine routines = routineRepository.findBySkinTypeIdAndIsDeletedFalse(skinTypeId);
        if (routines != null) {
            routinesResponse.setId(routines.getId());
            routinesResponse.setName(routines.getName());
            routinesResponse.setDescription(routines.getDescription());

            SkinTypeResponse skinTypeResponse = new SkinTypeResponse();
            skinTypeResponse.setTypeId(routines.getSkinType().getId());
            skinTypeResponse.setType(routines.getSkinType().getName());
            routinesResponse.setSkinTypeResponse(skinTypeResponse);

            List<RoutineStep> routineSteps = routines.getRoutineSteps();
            List<RoutineStepResponse> routineStepResponses = new ArrayList<>();
            for (RoutineStep routineStep : routineSteps) {
                if (!routineStep.isDeleted()) {
                    RoutineStepResponse routineStepResponse = new RoutineStepResponse();
                    routineStepResponse.setId(routineStep.getId());
                    routineStepResponse.setDescription(routineStep.getDescription());
                    routineStepResponse.setStepName(routineStep.getStepName());
                    routineStepResponse.setStepOrder(routineStep.getStepOrder());

                    List<Product> products = routineStep.getProducts();
                    List<ProductResponseForRoutine> productResponsesForRoutines = new ArrayList<>();
                    for (Product product : products) {
                        ProductResponseForRoutine productResponseForRoutine = new ProductResponseForRoutine();
                        productResponseForRoutine.setId(product.getId());
                        productResponseForRoutine.setName(product.getName());
                        productResponsesForRoutines.add(productResponseForRoutine);
                    }

                    routineStepResponse.setProductResponse(productResponsesForRoutines);
                    routineStepResponses.add(routineStepResponse);
                }
            }
            routinesResponse.setRoutineStepResponse(routineStepResponses);
        }else{
            throw new RuntimeException("Routine not found");
        }
        return routinesResponse;
    }

    public RoutineResponse getRoutineByCurrentUser() {
        User user = userUtils.getCurrentUser();
        Routine routines = routineRepository.findBySkinTypeIdAndIsDeletedFalse(user.getSkinProfile().getId());
        RoutineResponse routinesResponse = new RoutineResponse();
        if (routines != null) {
            routinesResponse.setId(routines.getId());
            routinesResponse.setName(routines.getName());
            routinesResponse.setDescription(routines.getDescription());

            SkinTypeResponse skinTypeResponse = new SkinTypeResponse();
            skinTypeResponse.setTypeId(routines.getSkinType().getId());
            skinTypeResponse.setType(routines.getSkinType().getName());
            routinesResponse.setSkinTypeResponse(skinTypeResponse);

            List<RoutineStep> routineSteps = routines.getRoutineSteps();
            List<RoutineStepResponse> routineStepResponses = new ArrayList<>();
            for (RoutineStep routineStep : routineSteps) {
                if (!routineStep.isDeleted()) {
                    RoutineStepResponse routineStepResponse = new RoutineStepResponse();
                    routineStepResponse.setId(routineStep.getId());
                    routineStepResponse.setDescription(routineStep.getDescription());
                    routineStepResponse.setStepName(routineStep.getStepName());
                    routineStepResponse.setStepOrder(routineStep.getStepOrder());

                    List<Product> products = routineStep.getProducts();
                    List<ProductResponseForRoutine> productResponsesForRoutines = new ArrayList<>();
                    for (Product product : products) {
                        ProductResponseForRoutine productResponseForRoutine = new ProductResponseForRoutine();
                        productResponseForRoutine.setId(product.getId());
                        productResponseForRoutine.setName(product.getName());
                        productResponsesForRoutines.add(productResponseForRoutine);
                    }

                    routineStepResponse.setProductResponse(productResponsesForRoutines);
                    routineStepResponses.add(routineStepResponse);
                }
            }
            routinesResponse.setRoutineStepResponse(routineStepResponses);
        }else{
            throw new RuntimeException("Routine not found");
        }
        return routinesResponse;
    }

    public RoutineResponse getRoutineById(Long id) {
        Routine routines = routineRepository.findByIdAndIsDeletedFalse(id);
        RoutineResponse routinesResponse = new RoutineResponse();
        if (routines != null) {
            routinesResponse.setId(routines.getId());
            routinesResponse.setName(routines.getName());
            routinesResponse.setDescription(routines.getDescription());

            SkinTypeResponse skinTypeResponse = new SkinTypeResponse();
            skinTypeResponse.setTypeId(routines.getSkinType().getId());
            skinTypeResponse.setType(routines.getSkinType().getName());
            routinesResponse.setSkinTypeResponse(skinTypeResponse);

            List<RoutineStep> routineSteps = routines.getRoutineSteps();
            List<RoutineStepResponse> routineStepResponses = new ArrayList<>();
            for (RoutineStep routineStep : routineSteps) {
                if (!routineStep.isDeleted()) {
                    RoutineStepResponse routineStepResponse = new RoutineStepResponse();
                    routineStepResponse.setId(routineStep.getId());
                    routineStepResponse.setDescription(routineStep.getDescription());
                    routineStepResponse.setStepName(routineStep.getStepName());
                    routineStepResponse.setStepOrder(routineStep.getStepOrder());

                    List<Product> products = routineStep.getProducts();
                    List<ProductResponseForRoutine> productResponsesForRoutines = new ArrayList<>();
                    for (Product product : products) {
                        ProductResponseForRoutine productResponseForRoutine = new ProductResponseForRoutine();
                        productResponseForRoutine.setId(product.getId());
                        productResponseForRoutine.setName(product.getName());
                        productResponsesForRoutines.add(productResponseForRoutine);
                    }

                    routineStepResponse.setProductResponse(productResponsesForRoutines);
                    routineStepResponses.add(routineStepResponse);
                }
            }
            routinesResponse.setRoutineStepResponse(routineStepResponses);
        }else{
            throw new RuntimeException("Routine not found");
        }
        return routinesResponse;
    }
}
