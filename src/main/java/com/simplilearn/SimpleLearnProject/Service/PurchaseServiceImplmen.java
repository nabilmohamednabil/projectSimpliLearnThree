package com.simplilearn.SimpleLearnProject.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.SimpleLearnProject.DAO.ProductDAO;
import com.simplilearn.SimpleLearnProject.entity.Product;
import com.simplilearn.SimpleLearnProject.entity.Purchase;
import com.simplilearn.SimpleLearnProject.DAO.*;

@Service
public class PurchaseServiceImplmen implements PurchaseService  {

	
    private PurchaseDAO PurchaseDAO;  
    
    @Autowired
    public PurchaseServiceImplmen(PurchaseDAO PurchaseDAO) {
                   this.PurchaseDAO = PurchaseDAO;
    }
    
	@Override
	public List<Purchase> findAll() {
		
		return this.PurchaseDAO.findAll() ;
	}

	@Override
	public Optional<Purchase> findById(int theId) {
		
		return this.PurchaseDAO.findById(theId) ;
	}

	@Override
	public void save(Purchase Purchase) {
		
		this.PurchaseDAO.save(Purchase);
	}

	@Override
	public void deleteById(int theId) {
		
		this.PurchaseDAO.deleteById(theId);
	}

	@Override
	public List<Purchase> findByPurchdateContains(Date key) {
		
		try { 
			 return this.PurchaseDAO.findByPurchdateContains(key);
			}
		catch (Exception ex) {
			return this.PurchaseDAO.findAll() ;
		}
	}

}
