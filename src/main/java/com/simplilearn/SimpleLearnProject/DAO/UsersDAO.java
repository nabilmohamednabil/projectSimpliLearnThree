package com.simplilearn.SimpleLearnProject.DAO;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.SimpleLearnProject.entity.Purchase;
import com.simplilearn.SimpleLearnProject.entity.Users;



@Repository
public interface UsersDAO  extends JpaRepository<Users, Integer> {
  
	public Optional<Users> findByName(String  name);

 
}