package com.las.WebData.service;


import java.util.List;

import com.las.WebData.entity.Users;


public interface UserService {
	public void saveUser(Users user);
		
	public void delUser(Users user);
	
	public void editUser(Users user);
	
	public List<Users> getAllUser();
	
	public Users getUser(int id);
	
	public List<Users> getPageUser(int page , int size); 
}
