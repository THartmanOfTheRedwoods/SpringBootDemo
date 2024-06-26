package edu.redwoods.cis18.springdemo.demo.controller;

import edu.redwoods.cis18.springdemo.demo.model.User;
import edu.redwoods.cis18.springdemo.demo.model.UserRepository;
import edu.redwoods.cis18.springdemo.demo.model.Possessions;
import edu.redwoods.cis18.springdemo.demo.model.PossessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/possession") // This means URLs start with /possessions (after Application path)
public class PossessionsController {
    // Autowired will connect the userRepository variable to a bean dubbed userRepository,
    // which is auto-generated by SpringBoot JPA. We will use it to perform CRUD operations on the user data.
    @Autowired
    private UserRepository userRepository;
    // Autowired will connect the possessionsRepository variable to a bean dubbed possessionsRepository,
    // which is auto-generated by SpringBoot JPA. We will use it to perform CRUD operations on the possessions data.
    @Autowired
    private PossessionsRepository possessionsRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests. POSTS == CREATE in CRUD.
    public @ResponseBody String addNewPossession(@RequestParam String name ,
                                                 @RequestParam String description,
                                                 // Below is how you can make a nullable/optional RequestParam.
                                                 @RequestParam(value="itemLabel", required=false) String itemLabel,
                                                 @RequestParam Integer user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User n = userRepository.findById(user).orElse(null);
        if(n != null) {
            Possessions p = new Possessions();
            p.setUser(n);
            p.setName(name);
            p.setDescription(description);
            p.setItemLabel(itemLabel);
            possessionsRepository.save(p);
            return "Saved";
        }
        return "Invalid User";
    }

    @GetMapping(path="/get")
    public @ResponseBody Iterable<Possessions> getPossessionsByUser(@RequestParam Integer user) {
        User n = userRepository.findById(user).orElse(null);
        if(n != null) {
            // This returns a JSON or XML with the possessions.
            return possessionsRepository.findByUser(n, Sort.by(Sort.Direction.DESC, "id") );
        }
        return null;
    }
}
