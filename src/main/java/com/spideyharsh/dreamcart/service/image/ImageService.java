package com.spideyharsh.dreamcart.service.image;

import com.spideyharsh.dreamcart.dto.ImageDto;
import com.spideyharsh.dreamcart.exceptions.ResourceNotFoundException;
import com.spideyharsh.dreamcart.model.Image;
import com.spideyharsh.dreamcart.model.Product;
import com.spideyharsh.dreamcart.repository.ImageRepository;
import com.spideyharsh.dreamcart.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{

    private final ImageRepository imageRepository;
    private final ProductService productService;

    @Override
    public Image getImageById(long id) {
        return imageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Image not found"));
    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImageDto = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFilename(file.getOriginalFilename());
                image.setFiletype(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl ="/api/v1/images/image/download/";
                String downloadUrl = buildDownloadUrl +image.getId();
                image.setDownloadUrl(downloadUrl);

               Image savedImage =  imageRepository.save(image);
               savedImage.setDownloadUrl(buildDownloadUrl+savedImage.getId());
               imageRepository.save(savedImage);

               ImageDto imageDto = new ImageDto();
                imageDto.setImageId(savedImage.getId());
                imageDto.setImageName(savedImage.getFilename());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDto.add(imageDto);

            }catch (IOException|SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }

    @Override
    public void deleteImageById(long id) {
        imageRepository.findById(id)
                .ifPresentOrElse(imageRepository::delete,()->{
                    throw new ResourceNotFoundException("Image not found");
                });
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFilename(file.getOriginalFilename());
            image.setFiletype(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException |SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
