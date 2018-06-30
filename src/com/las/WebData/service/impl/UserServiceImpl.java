package com.las.WebData.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.las.WebData.dao.UserDao;
import com.las.WebData.dao.impl.UserDaoImpl;
import com.las.WebData.entity.Users;
import com.las.WebData.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public void saveUser(Users user) {
		// TODO Auto-generated method stub
		userDao.saveUser(user);
	}

	@Override
	public void delUser(Users user) {
		// TODO Auto-generated method stub
		userDao.delUser(user);
	}

	@Override
	public void editUser(Users user) {
		// TODO Auto-generated method stub
		userDao.editUser(user);
	}

	@Override
	public List<Users> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getUser();
	}

	@Override
	public Users getUser(int id) {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}

	@Override
	public List<Users> getPageUser(int page,int size) {
		// TODO Auto-generated method stub
		return userDao.getPageUser(page, size);
	}
	
	

}
