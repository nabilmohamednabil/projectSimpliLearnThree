package com.simplilearn.SimpleLearnProject.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
@RequestMapping("/users")
public class UsersController {


	@Autowired
    private ProductService ProductService;     
	@Autowired
    private PurchaseService PurchaseService; 
	@Autowired
    private UsersService UsersService; 
    
 
    public UsersController( ProductService ProductService ,
    		PurchaseService PurchaseService ,
    		UsersService UsersService) {
          this.UsersService = UsersService;
          this.PurchaseService = PurchaseService;
          this.ProductService = ProductService;
    }
    
    
    @GetMapping("/filter")
    public String filterpurchases(Model theModel  , @Param("keyword") String keyword  ) {
    	Optional<Users> UsersList ;
    	List<Users> theusers = new ArrayList<>() ;
    	if(keyword != null ) {
    		try {  			
    			theusers = this.UsersService.findByName(keyword).stream().toList();
	    		theModel.addAttribute("UsersList", theusers);    		
			} catch (Exception e) {				
				return "errorpage";
			} 
    		
    		return "UsersReporting";
    	}
    	
    	else {
    		theusers = this.UsersService.findAll();
    		theModel.addAttribute("UsersList", theusers);  		
    	}
    	return "UsersReporting";
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
