package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.Image;

import java.util.List;

/**
 * Created by Ivan V. on 23-Jun-18
 */
public interface ImageService {

    List<Image> findAll();

    Image save(Image image);
}
