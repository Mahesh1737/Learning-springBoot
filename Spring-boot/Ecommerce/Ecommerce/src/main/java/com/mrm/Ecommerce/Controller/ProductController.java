package com.mrm.Ecommerce.Controller;

import com.mrm.Ecommerce.Entity.Product;
import com.mrm.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Product getAllProducts(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleProductById(id);
    }


}
