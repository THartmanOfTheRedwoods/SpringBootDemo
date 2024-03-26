package edu.redwoods.cis18.springdemo.demo.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;
import edu.redwoods.cis18.springdemo.demo.model.Possessions;
import edu.redwoods.cis18.springdemo.demo.model.User;

import java.util.List;

public interface PossessionsRepository extends CrudRepository<Possessions, Long> {
    List<Possessions> findByUser(User user, Sort sort);
}