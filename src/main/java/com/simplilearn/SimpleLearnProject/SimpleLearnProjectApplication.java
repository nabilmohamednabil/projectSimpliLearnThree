package com.simplilearn.SimpleLearnProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.simplilearn.SimpleLearnProject.Service.* ;

import ch.qos.logback.core.util.SystemInfo;

@SpringBootApplication
public class SimpleLearnProjectApplication implements CommandLineRunner  {

	
    private ProductService ProductService; 
    private PurchaseService PurchaseService;  
    private UsersService UsersService;  
    
    @Autowired
    public SimpleLearnProjectApplication(ProductService ProductService ,
    		PurchaseService PurchaseService ,
    		UsersService UsersService ) {
                   this.ProductService = ProductService;
                   this.PurchaseService =PurchaseService;
                   this.UsersService = UsersService;
    }
	public static void main(String[] args) {
		SpringApplication.run(SimpleLearnProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		ProductService.findAll().forEach(  x -> System.out.println(x));
		PurchaseService.findAll().forEach(  x -> System.out.println(x));
		UsersService.findAll().forEach(  x -> System.out.println(x));
	}

}
