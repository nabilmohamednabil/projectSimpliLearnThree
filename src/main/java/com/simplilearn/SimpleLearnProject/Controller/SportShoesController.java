package com.simplilearn.SimpleLearnProject.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilearn.SimpleLearnProject.Service.*;
import com.simplilearn.SimpleLearnProject.entity.Product;
import com.simplilearn.SimpleLearnProject.entity.Users;

@Controller
@RequestMapping("/applica")
public class SportShoesController {

	@Autowired
    private ProductService ProductService;     
	@Autowired
    private PurchaseService PurchaseService; 
	@Autowired
    private UsersService UsersService; 
    
 
    public SportShoesController( ProductService ProductService ,
    		PurchaseService PurchaseService ,
    		UsersService UsersService) {
          this.UsersService = UsersService;
          this.PurchaseService = PurchaseService;
          this.ProductService = ProductService;
    }
    
    @GetMapping("/admin")
    public String ShowAdmin(Model theModel) {
         return "login";
    }
    
    @PostMapping("/update")
    public String UpdateProduct(@ModelAttribute("product") Product theproduct) {
    	 this.ProductService.save(theproduct);
    	 return "redirect:/applica/adminHandling";
    }
    
    @GetMapping("/delete")
    public String DeleteProduct(@RequestParam("productId") int theId) {
    	 this.ProductService.deleteById(theId);
    	 return "redirect:/applica/adminHandling";
    }
    
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("productId") int theId,
									Model theModel) {
		

		Product theproduct = this.ProductService.findById(theId).get();
		theModel.addAttribute("product", theproduct);
		return "ProductUpdate";			
	}
	
    
    @PostMapping("/adminHandling")
    public String listProducts(  Users theuser , Model theModel  , HttpSession session ) {
    	String User = "Default" ;
    	if(this.UsersService.findByName(theuser.getName()).isPresent()) 
    	{
    		if (this.UsersService.findByName(theuser.getName()).get().getName().equals("admin")&&
    		    this.UsersService.findByName(theuser.getName()).get().getPassword().equals(theuser.getPassword()))
    			User="admin";
    		else if (this.UsersService.findByName(theuser.getName()).get().getPassword().equals(theuser.getPassword()))
    			User="normal";
    		session.setAttribute("LoggedUser", theuser.getName());
    	}
    	
        
		List<Product> theproducts = ProductService.findAll();
		theModel.addAttribute("products", theproducts);
    	if(User.equals("admin")) return "adminpage";
    	else if (User.equals("normal")) return "userpage";
    	else return "errorpage" ;
    }
    
    @GetMapping("/adminHandling")
    public String listprod(   Model theModel  , HttpSession session ) 
    {
		List<Product> theproducts = ProductService.findAll();
		theModel.addAttribute("products", theproducts);
    	 return "adminpage";
    }
     
    
}
