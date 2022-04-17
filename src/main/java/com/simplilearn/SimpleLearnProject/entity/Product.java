package com.simplilearn.SimpleLearnProject.entity;

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
import javax.persistence.Table;



@Entity
@Table(name="Product")
public class Product {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="prodid")
    private Integer prodid;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Integer price;
    
	@Column(name="category")
    private String category;

	@ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
			name="common",
			joinColumns=@JoinColumn(name="prodid"),
			inverseJoinColumns=@JoinColumn(name="purchid")
			)	
	private List<Purchase> PurchaseList;
	



	public Integer getProdid() {
		return prodid;
	}

	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Purchase> getPurchaseList() {
		return PurchaseList;
	}

	public void setPurchaseList(List<Purchase> purchaseList) {
		PurchaseList = purchaseList;
	}


    
}
