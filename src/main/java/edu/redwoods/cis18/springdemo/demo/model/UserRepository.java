package edu.redwoods.cis18.springdemo.demo.model;

import org.springframework.data.repository.CrudRepository;
import edu.redwoods.cis18.springdemo.demo.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface UserRepository extends CrudRepository<User, Integer> {

}
