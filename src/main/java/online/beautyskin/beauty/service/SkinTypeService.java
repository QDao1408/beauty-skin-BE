package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.SkinType;
import online.beautyskin.beauty.entity.request.SkinTypeRequest;
import online.beautyskin.beauty.repository.SkinTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinTypeService {

    @Autowired
    private SkinTypeRepository repo;

    public List<SkinType> getAll() {
        return repo.findByIsDeletedFalse();
    }

    public SkinType create(SkinTypeRequest request) {
        SkinType type = new SkinType();
        type.setTypeName(request.getName());
        type.setDescription(request.getDescription());
        return repo.save(type);
    }

    public SkinType delete(long id) {
        SkinType type = repo.findById(id);
        type.setDeleted(true);
        return repo.save(type);
    }

    public SkinType update(long id, SkinTypeRequest request) {
        SkinType type = repo.findById(id);
        type.setTypeName(request.getName());
        type.setDescription(request.getDescription());
        return repo.save(type);
    }

}
