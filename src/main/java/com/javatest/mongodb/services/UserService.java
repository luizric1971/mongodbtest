package com.javatest.mongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatest.mongodb.domain.User;
import com.javatest.mongodb.repository.UserRepository;

@Service
public class UserService {

		@Autowired
		private UserRepository repository;
		
		public List<User> findAll() {
			return repository.findAll();
		}
}