package com.practice.restfulwebservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.practice.restfulwebservices.entity.User;

@Repository
public class UserDaoService {

	private static List<User> userList = new ArrayList<User>();
	private static int listSize = 3;

	static {
		userList.add(new User(343044, "aman", new Date()));
		userList.add(new User(343045, "pawan", new Date()));
		userList.add(new User(343046, "vittej", new Date()));
		userList.add(new User(879564,"suraj",new Date()));
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

		for (User user : userList) {
			if (user.getId() == id)
				return user;
		}

		return null;
	}

	public User deleteById(int id) {

		Iterator<User> itr = userList.iterator();

		while (itr.hasNext()) {
			User user = itr.next();
			if (user.getId() == id) {
				itr.remove();
				return user;
			}
		}
		
		return null;
	}


}
