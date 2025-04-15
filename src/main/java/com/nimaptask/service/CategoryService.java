package com.nimaptask.service;

import com.nimaptask.entity.Category;
import com.nimaptask.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatecategory){
        return categoryRepository.findById(id).map(category -> {
            category.setName(updatecategory.getName());
            category.setProducts(updatecategory.getProducts());

            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category Not Found"));
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}
