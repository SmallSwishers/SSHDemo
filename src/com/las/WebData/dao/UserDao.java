package com.las.WebData.dao;

import java.util.List;

import com.las.WebData.entity.Users;
/**
 * 
 * @author qiao
 * @version 1.0
 */
public interface UserDao {
	public void saveUser(Users user);
	
	public void delUser(Users user);
	
	public void editUser(Users user);
	
	public List<Users> getUser();
	
	public Users getUser(int id);
	
	public List<Users> getPageUser(int page,int size);
}
