package com.practice.restfulwebservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.practice.restfulwebservices.entity.User;

@Repository
public class UserDaoService {

	private static List<User> userList = new ArrayList<User>();
	private static int listSize = 3;

	static {
		userList.add(new User(343044, "suraj", new Date()));
		userList.add(new User(343045, "nivesh", new Date()));
		userList.add(new User(343046, "ashvini", new Date()));
	}

	public List<User> findAll() {
		return userList;
	}

	public User save(User user) {
		if (user.getId() == 0) {
			user.setId(++listSize);
		}
		userList.add(user);
		return user;
	}

	public User findOne(int id) {
		return userList.stream().filter(user -> user.getId() == id).findAny().get();
	}

}
