package com.spideyharsh.dreamcart.service.image;

import com.spideyharsh.dreamcart.dto.ImageDto;
import com.spideyharsh.dreamcart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(long id);
    List<ImageDto> saveImages(List<MultipartFile> files , Long productId);
    void deleteImageById(long id);
    void updateImage(MultipartFile file,Long imageId);
}
