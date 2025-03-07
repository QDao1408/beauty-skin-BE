package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.SkinConcern;
import online.beautyskin.beauty.entity.SkinType;
import online.beautyskin.beauty.entity.request.SkinConcernRequest;
import online.beautyskin.beauty.repository.SkinConcernRepository;
import online.beautyskin.beauty.repository.SkinTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinConcernService {
    @Autowired
    private SkinConcernRepository repository;

    public SkinConcern save(SkinConcernRequest request) {
        SkinConcern skinConcern = new SkinConcern();
        skinConcern.setDescription(request.getDescription());
        skinConcern.setName(request.getName());
        return repository.save(skinConcern);
    }

    public SkinConcern delete(long id) {
        SkinConcern skinConcern = repository.findById(id);
        skinConcern.setDeleted(true);
        return repository.save(skinConcern);
    }

    public SkinConcern update(long id, SkinConcernRequest request) {
        SkinConcern skinConcern = repository.findById(id);
        skinConcern.setName(request.getName());
        skinConcern.setDescription(request.getDescription());
        return repository.save(skinConcern);
    }

    public List<SkinConcern> getAll() {
        return repository.findByIsDeletedFalse();
    }
}
