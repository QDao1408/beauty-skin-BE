package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Form;
import online.beautyskin.beauty.entity.request.FormRequest;
import online.beautyskin.beauty.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    @Autowired
    private FormRepository repository;

    public Form create(FormRequest request) {
        Form form = new Form();
        form.setName(request.getName());
        form.setDescription(request.getDescription());
        return repository.save(form);
    }

    public List<Form> getAll() {
        return repository.findByIsDeletedFalse();
    }

    public Form delete(long id) {
        Form form = repository.findById(id);
        form.setDeleted(true);
        return repository.save(form);
    }

    public Form update(long id, FormRequest request) {
        Form form = repository.findById(id);
        form.setDescription(request.getDescription());
        form.setName(request.getName());
        return repository.save(form);
    }


}
