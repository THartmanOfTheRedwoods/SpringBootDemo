package edu.redwoods.cis18.springdemo.demo.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;
import edu.redwoods.cis18.springdemo.demo.model.Possessions;
import edu.redwoods.cis18.springdemo.demo.model.User;

import java.util.List;

// Spring Data JPA focuses on using JPA to store data in a relational database.
// Its most compelling feature is the ability to create repository implementations
// automatically, at runtime, from a repository interface.
// Below, PossessionsRepository will be AUTO IMPLEMENTED by Spring into a Bean called
// possessionsRepository. CRUD refers to Create, Read, Update, Delete
public interface PossessionsRepository extends CrudRepository<Possessions, Long> {
    // Spring Data JPA lets you define other query methods simply by declaring their method signature. For example,
    // the possessionsRepository bean includes the **findByUser** method after this, and it gets auto-wired to our
    // Possessions entity getUser getter via JPA. Sort object allows sorting on various Object/Entity instance fields.
    List<Possessions> findByUser(User user, Sort sort);
}