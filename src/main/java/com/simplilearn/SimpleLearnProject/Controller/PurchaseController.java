package com.simplilearn.SimpleLearnProject.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
    private ProductService ProductService;     
	@Autowired
    private PurchaseService PurchaseService; 
	@Autowired
    private UsersService UsersService; 
    
 
    public PurchaseController( ProductService ProductService ,
    		PurchaseService PurchaseService ,
    		UsersService UsersService) {
          this.UsersService = UsersService;
          this.PurchaseService = PurchaseService;
          this.ProductService = ProductService;
    }
    
    @PostMapping("/buy")
    public String UpdateProduct(@ModelAttribute("product") Product theproduct) {
    	 this.ProductService.save(theproduct);
    	 return "redirect:/applica/adminHandling";
    }
    
    @GetMapping("/buy")
    public String BuyProduct ( @RequestParam("productId") int theId , HttpSession session ) {
    	 String userName  =  (String)session.getAttribute("LoggedUser");
    	 Users theuser    =  this.UsersService.findByName(userName).get();
    	 System.out.println("the logged user is " + theuser.getName() );
    	 
    	 if( theuser.getPurchase() != null)
    	 {
 
         Product pr1 = this.ProductService.findById(theId).get() ;
         Purchase purcah1 = theuser.getPurchase();
         purcah1.AddToProductList(pr1); 
         this.PurchaseService.save(purcah1);
    	 }
    	 else {
    		 Purchase p1 = new Purchase();
    		 p1.setPurchdate(new Date());
    		 p1.setTotalamount(this.ProductService.findById(theId).get().getPrice());
    		 p1.setUser(theuser);
    		 p1.AddToProductList(this.ProductService.findById(theId).get());
    		 this.PurchaseService.save(p1);
    	 }
    	 return "redirect:/applica/admin";
    }
    
    @GetMapping("/payment")
    public String BuyProduct ( HttpSession session  , Model theModel ) {
    	 String userName  =  (String)session.getAttribute("LoggedUser");
    	 Integer sum = 0 ;
    	 payment pay1 = new payment();
    	 List<Product> theList = new ArrayList<>();
    	 
    	 try {
    		 Purchase purcah1 = this.UsersService.findByName(userName).get().getPurchase();
    	  	 theList   = purcah1.getProductList();
        	 for(Product x :  theList) {
        		 sum = x.getPrice() + sum ;
        	 }
        	 pay1.setTotalcost(sum);
        	 theModel.addAttribute("price", pay1);
        	 purcah1.setTotalamount(sum);
        	 this.PurchaseService.save(purcah1);
        	 return "payment";
    	 }
    	 catch(Exception ex) {
    		 return "redirect:/applica/adminHandling";
    	 }
    }
    
}
