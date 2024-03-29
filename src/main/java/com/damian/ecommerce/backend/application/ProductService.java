package com.damian.ecommerce.backend.application;

import com.damian.ecommerce.backend.domain.model.Product;
import com.damian.ecommerce.backend.domain.port.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
public class ProductService {
    private final IProductRepository iProductRepository;
    private final UploadFileService uploadFileService;

    public ProductService(IProductRepository iProductRepository, UploadFileService uploadFileService) {
        this.iProductRepository = iProductRepository;
        this.uploadFileService = uploadFileService;
    }

    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        if (product.getId() != 0) {
            if (multipartFile == null) {
                product.setUrlImage(product.getUrlImage());
            } else {
                String urlImage = product.getUrlImage();
                if (urlImage != null && urlImage.length() > 29) {
                    //cuando se haga deploy esto va a cambiar - > http://localhost..... despues de /images
                    String fileName = urlImage.substring(29);
                    log.info("nombre de imagen: {}", fileName);
                    if (fileName.equals("default.jpg")) {
                        uploadFileService.deleteImage(fileName);
                    }
                }
                product.setUrlImage(uploadFileService.uploadImage(multipartFile));
            }
        } else {
            product.setUrlImage(uploadFileService.uploadImage(multipartFile));
        }
        return iProductRepository.save(product);
    }

    public Iterable<Product> findAll(){
        return iProductRepository.findAll();
    }

    public Product findById(Integer id){
        return iProductRepository.findById(id);
    }

    public void deleteById(Integer id) {
        Product product = findById(id);
        String urlImage = product.getUrlImage();
        if (urlImage != null && urlImage.length() > 29) {
            //cuando se haga deploy esto va a cambiar - > http://localhost..... despues de /images
            String name = urlImage.substring(29);
            log.info("nombre de imagen: {}", name);
            if (!name.equals("default.jpg")) {
                uploadFileService.deleteImage(name);
            }
        }
        iProductRepository.deleteById(id);
    }
}
