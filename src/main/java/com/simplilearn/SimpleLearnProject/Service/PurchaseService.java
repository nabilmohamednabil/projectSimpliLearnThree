package com.simplilearn.SimpleLearnProject.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.simplilearn.SimpleLearnProject.entity.Product;
import com.simplilearn.SimpleLearnProject.entity.Purchase;

public interface PurchaseService {

	
    public List<Purchase> findAll();
  
    public Optional<Purchase> findById(int theId);

    public void save(Purchase purchase);

    public void deleteById(int theId);
    
    
    public List<Purchase> findByPurchdateContains(Date key);
}
