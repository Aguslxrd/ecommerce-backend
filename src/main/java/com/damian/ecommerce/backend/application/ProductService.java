package com.damian.ecommerce.backend.application;

import com.damian.ecommerce.backend.domain.model.Product;
import com.damian.ecommerce.backend.domain.port.IProductRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ProductService {
    private final IProductRepository iProductRepository;
    private final UploadFileService uploadFileService;

    public ProductService(IProductRepository iProductRepository, UploadFileService uploadFileService) {
        this.iProductRepository = iProductRepository;
        this.uploadFileService = uploadFileService;
    }

    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        if (product.getId() != 0){
            if (multipartFile == null){
                product.setUrlImage(product.getUrlImage());
            }else {
                product.setUrlImage(uploadFileService.uploadImage(multipartFile));
            }

        }else {
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

    public void deleteById(Integer id){
        iProductRepository.deleteById(id);
    }
}
