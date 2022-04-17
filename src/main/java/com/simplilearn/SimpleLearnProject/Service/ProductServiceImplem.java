package com.simplilearn.SimpleLearnProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.SimpleLearnProject.entity.Product;
import com.simplilearn.SimpleLearnProject.DAO.*;
@Service
public class ProductServiceImplem implements ProductService  {

	
    private ProductDAO ProductDAO;  
    
    @Autowired
    public ProductServiceImplem(ProductDAO ProductDAO) {
                   this.ProductDAO =  ProductDAO;
    }
    
	@Override
	public List<Product> findAll() {
		
		return this.ProductDAO.findAll() ;
	}

	@Override
	public Optional<Product> findById(int theId) {
		
		return this.ProductDAO.findById(theId) ;
	}

	@Override
	public void save(Product product) {
		
		this.ProductDAO.save(product);
	}

	@Override
	public void deleteById(int theId) {
		
		this.ProductDAO.deleteById(theId);
	}

}
