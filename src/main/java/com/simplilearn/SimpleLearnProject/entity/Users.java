package com.simplilearn.SimpleLearnProject.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class Users {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="usid")
    private Integer usid;

    @Column(name="name")
    private String name;
    
    @Column(name="password")
    private String password;
    
    @OneToOne( mappedBy = "user")
    private Purchase  Purchase ;




	public Integer getUsid() {
		return usid;
	}

	public void setUsid(Integer usid) {
		this.usid = usid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Purchase getPurchase() {
		return Purchase;
	}

	public void setPurchase(Purchase purchase) {
		Purchase = purchase;
	}
   
    
}
