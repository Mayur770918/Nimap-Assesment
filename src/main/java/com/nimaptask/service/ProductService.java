package com.nimaptask.service;

import com.nimaptask.entity.Product;
import com.nimaptask.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }


    public Product addProduct(Product product){
        return productRepository.save(product);
    }


    public Product updateProduct(Long id, Product updateproduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updateproduct.getName());
            product.setPrice(updateproduct.getPrice());
            product.setQuantity(updateproduct.getQuantity());
            product.setCategory(updateproduct.getCategory());  // Added category update

            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product Not Found with id: " + id));
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product Not Found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
