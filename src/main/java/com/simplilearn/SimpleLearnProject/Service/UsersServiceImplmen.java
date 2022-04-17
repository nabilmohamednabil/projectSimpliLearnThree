package com.simplilearn.SimpleLearnProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.SimpleLearnProject.DAO.ProductDAO;
import com.simplilearn.SimpleLearnProject.entity.Purchase;
import com.simplilearn.SimpleLearnProject.entity.Users;
import com.simplilearn.SimpleLearnProject.DAO.*;

@Service
public class UsersServiceImplmen implements UsersService {

	
    private UsersDAO UsersDAO;  
    
    @Autowired
    public UsersServiceImplmen( UsersDAO UsersDAO) {
                   this. UsersDAO = UsersDAO;
    }
    
	@Override
	public List<Users> findAll() {
		
		return this.UsersDAO.findAll() ;
	}

	@Override
	public Optional<Users> findById(int theId) {
		
		return this.UsersDAO.findById(theId) ;
	}

	@Override
	public void save(Users Users) {
		
		this.UsersDAO.save(Users);
	}

	@Override
	public void deleteById(int theId) {
		
		this.UsersDAO.deleteById(theId);
	}

	@Override
	public Optional<Users> findByName(String name) {
		
		return this.UsersDAO.findByName(name);
	}

}
