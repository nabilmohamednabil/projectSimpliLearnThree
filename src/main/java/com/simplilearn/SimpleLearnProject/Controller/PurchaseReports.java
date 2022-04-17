package com.simplilearn.SimpleLearnProject.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilearn.SimpleLearnProject.Service.ProductService;
import com.simplilearn.SimpleLearnProject.Service.PurchaseService;
import com.simplilearn.SimpleLearnProject.Service.UsersService;
import com.simplilearn.SimpleLearnProject.Payments.payment;
import com.simplilearn.SimpleLearnProject.Service.*;
import com.simplilearn.SimpleLearnProject.entity.Product;
import com.simplilearn.SimpleLearnProject.entity.Purchase;
import com.simplilearn.SimpleLearnProject.entity.Users;


@Controller
@RequestMapping("/Report")
public class PurchaseReports {
	
	@Autowired
    private ProductService ProductService;     
	@Autowired
    private PurchaseService PurchaseService; 
	@Autowired
    private UsersService UsersService; 
    
 
    public PurchaseReports( ProductService ProductService ,
    		PurchaseService PurchaseService ,
    		UsersService UsersService) {
          this.UsersService = UsersService;
          this.PurchaseService = PurchaseService;
          this.ProductService = ProductService;
    }
    
    
    @GetMapping("/purchases")
    public String Reporting ( HttpSession session  , Model theModel ) {
    	 String userName  =  (String)session.getAttribute("LoggedUser");
    	 List<Purchase> PurchaseList = new ArrayList<>();
    	 List<Product> ProductList = new ArrayList<>();
    	 PurchaseList  = this.PurchaseService.findAll();	     	 
    	 theModel.addAttribute("PurchaseList", PurchaseList);
    	 return "Reporting";
    }
    
    
    @GetMapping("/filter")
    public String filterpurchases(Model theModel  , @Param("keyword") String keyword , @Param("keyword2") String keyword2 ) {
    	List<Purchase> PurchaseList = new ArrayList<>();
    	List<Purchase> PurchaseFilter = new ArrayList<>();
    	System.out.println("keyword are ... " + keyword  );
    	System.out.println("keyword2 are ... " + keyword2  );
    	
    	if(keyword != null &&   keyword2 == null) {
    		System.out.println("values are ... " + keyword  );
    		try {  			
				Date thedate = new SimpleDateFormat("yyyy-mmm-dd").parse(keyword);
				System.out.println("date are ... " + thedate  );
	    		PurchaseList = this.PurchaseService.findByPurchdateContains(thedate);
	    		theModel.addAttribute("PurchaseList", PurchaseList);
	    		
			} catch (ParseException e) {
				
				return "errorpage";
			} 

    		return "Reporting";
    	}
    	
    	else if( (keyword != null &&   keyword2 != null)  || (keyword == null  &&  keyword2 != null )) {
			try {
			Date	thedate = new SimpleDateFormat("yyyy-mmm-dd").parse(keyword);
    		PurchaseList = this.PurchaseService.findByPurchdateContains(thedate);
    		for(Purchase x : PurchaseList) {
    			 for(Product y : x.getProductList() ) {
    				 if(y.getCategory().contains(keyword2)) PurchaseFilter.add(x);
    			 }
    		}
			} catch (ParseException e) {
	    		PurchaseList = this.PurchaseService.findAll();
	    		for(Purchase x : PurchaseList) {
	    			 for(Product y : x.getProductList() ) {
	    				 if(y.getCategory().contains(keyword2)) PurchaseFilter.add(x);
	    			 }
	    		}
			}
    		
			theModel.addAttribute("PurchaseList", PurchaseFilter);
			return "Reporting";
    	}
    	else {
    		System.out.println("values2323 are ... " + keyword  );
    		PurchaseList = this.PurchaseService.findAll();
    		theModel.addAttribute("PurchaseList", PurchaseList);
    		return "Reporting";
    	}
    	
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
