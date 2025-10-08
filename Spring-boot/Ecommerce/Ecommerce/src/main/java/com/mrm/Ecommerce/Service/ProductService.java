package com.mrm.Ecommerce.Service;

import com.mrm.Ecommerce.Entity.Product;
import com.mrm.Ecommerce.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }

    public Product getProductById(Long id){
        return productRepo.findById(id).orElse(null);
    }

    public Product addProduct(Product product){
        return productRepo.save(product);
    }

    public void deleProductById(Long id){
        productRepo.deleteById(id);
    }
}
