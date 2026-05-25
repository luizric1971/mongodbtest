package com.javatest.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatest.mongodb.domain.User;
import com.javatest.mongodb.dto.UserDTO;
import com.javatest.mongodb.repository.UserRepository;
import com.javatest.mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

		@Autowired
		private UserRepository repository;
		
		public List<User> findAll() {
			return repository.findAll();
		}

		public User findById(String id) {
			Optional<User> obj = repository.findById(id);
			
			return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
		}
		
		public User insert(User obj) {
			return repository.insert(obj);
		}
		
		public void delete(String id) {
			if (findById(id) != null) {
				repository.deleteById(id);
			}
			else {
				throw new ObjectNotFoundException("Object not found");
			}
		}
		
		public User update(User obj) {
			User newObj = findById(obj.getId());
			
			updateData(newObj, obj);
			return repository.save(newObj);
		}
		
		private void updateData(User newObj, User obj) {
			newObj.setName(obj.getName());
			newObj.setEmail(obj.getEmail());
		}
		
		public User fromDTO(UserDTO objDto) {
			return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
		}
}