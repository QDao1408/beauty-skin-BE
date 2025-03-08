package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Image;
import online.beautyskin.beauty.entity.request.ImageRequest;
import online.beautyskin.beauty.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    public Image create(ImageRequest request) {
        Image image = new Image(request.getUrl());
        return repository.save(image);
    }

    public Image delete(long id) {
        Image image = repository.findByIdAndIsDeletedFalse(id);
        image.setDeleted(true);
        return repository.save(image);
    }

    public List<Image> getAll() {
        List<Image> images = repository.findByIsDeletedFalse();
        return images;
    }

    public Image update(long id, ImageRequest request) {
        Image image = repository.findByIdAndIsDeletedFalse(id);
        image.setUrl(request.getUrl());
        return image;
    }

}
