package com.simplilearn.SimpleLearnProject.Service;

import java.util.List;
import java.util.Optional;

import com.simplilearn.SimpleLearnProject.entity.Product;

public interface ProductService {

	
    public List<Product> findAll();
  
    public Optional<Product> findById(int theId);

    public void save(Product product);

    public void deleteById(int theId);
}
