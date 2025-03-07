package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Tag;
import online.beautyskin.beauty.entity.request.TagRequest;
import online.beautyskin.beauty.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;

    public Tag create(TagRequest request) {
        Tag tag = new Tag();
        tag.setName(request.getName());
        return repository.save(tag);
    }

    public List<Tag> getAll() {
        return repository.findByIsDeletedFalse();
    }

    public Tag update(long id, TagRequest request) {
        Tag tag = repository.findById(id);
        tag.setName((request.getName()));
        return repository.save(tag);
    }

    public Tag delete(long id) {
        Tag tag = repository.findById(id);
        tag.setDeleted(true);
        return repository.save(tag);
    }


}
