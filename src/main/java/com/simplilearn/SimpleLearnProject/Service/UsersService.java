package com.simplilearn.SimpleLearnProject.Service;

import java.util.List;
import java.util.Optional;

import com.simplilearn.SimpleLearnProject.entity.Purchase;
import com.simplilearn.SimpleLearnProject.entity.Users;

public interface UsersService {

	
    public List<Users> findAll();
    
    public Optional<Users> findByName(String  name);
    
    public Optional<Users> findById(int theId);

    public void save(Users user);

    public void deleteById(int theId);
    
}
