package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Brand;
import online.beautyskin.beauty.entity.request.BrandRequest;
import online.beautyskin.beauty.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findByIsDeletedFalse();
    }

    public Brand getBrandById(long id) {
        return brandRepository.findById(id);
    }

    public Brand deleteBrandById(long id) {
        Brand brand = brandRepository.findById(id);
        brand.setDeleted(false);
        return brandRepository.save(brand);
    }

    public Brand createBrand(BrandRequest brandRequest) {
        Brand brand = new Brand();
        brand.setName(brandRequest.getName());
        brand.setDescription(brandRequest.getDescription());
        brand.setDeleted(false);
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand);
    }
}
