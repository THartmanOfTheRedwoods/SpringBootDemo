package edu.redwoods.cis18.springdemo.demo.model;

import org.springframework.data.repository.CrudRepository;
import edu.redwoods.cis18.springdemo.demo.model.User;

import java.util.List;

// Spring Data JPA focuses on using JPA to store data in a relational database.
// Its most compelling feature is the ability to create repository implementations
// automatically, at runtime, from a repository interface.
// Below, UserRepository will be AUTO IMPLEMENTED by Spring into a Bean called
// userRepository. CRUD refers to Create, Read, Update, Delete
public interface UserRepository extends CrudRepository<User, Integer> {
    // Spring Data JPA lets you define other query methods simply by declaring their method signature.
    // For example, userRepository includes the findByEmail() method, and this gets auto-wired to our
    // User getEmail() getter.
    List<User> findByEmail(String email);
}
