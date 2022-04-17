package com.simplilearn.SimpleLearnProject.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="purchid")
    private Integer purchid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usid")
    private Users user;
    
    @Column(name="purchdate")
    private Date purchdate;

    @Column(name="totalamount")
    private Integer totalamount;
    
    
	@ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.ALL})
	@JoinTable(
			name="common",
			joinColumns=@JoinColumn(name="purchid"),
			inverseJoinColumns=@JoinColumn(name="prodid")
			)	
	private List<Product> ProductList ;

    
   public void AddToProductList (Product theproduct) {
	   if(this.ProductList == null) {
		   this.ProductList =  new ArrayList<>();
		   this.ProductList.add(theproduct);    
	   }
	   else {this.ProductList.add(theproduct);}
   }
 
   
	public List<Product> getProductList() {
	return ProductList;
}


public void setProductList(List<Product> productList) {
	ProductList = productList;
}




	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public Integer getPurchid() {
		return purchid;
	}


	public void setPurchid(Integer purchid) {
		this.purchid = purchid;
	}


	public Date getPurchdate() {
		return purchdate;
	}


	public void setPurchdate(Date purchdate) {
		this.purchdate = purchdate;
	}


	public Integer getTotalamount() {
		return totalamount;
	}


	public void setTotalamount(Integer totalamount) {
		this.totalamount = totalamount;
	}


	
	
}
