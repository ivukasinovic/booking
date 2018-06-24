package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.Image;
import XMLandSecurity.backend1.repository.ImageRepository;
import XMLandSecurity.backend1.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ivan V. on 23-Jun-18
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
