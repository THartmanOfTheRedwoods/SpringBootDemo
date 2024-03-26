package edu.redwoods.cis18.springdemo.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;


    // This is the ONE side of a 1:M relationship
    // Lazy means don't fetch until accessed.
    // Cascade means cascade deletes and updates in the underlying database.
    @OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    // The next 2 annotations will simply put "possessions": ids in REST responses instead of nesting possession
    // objects. This will be a list in Json because there are a list of ids. Without this, object nesting will occur.
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Possessions> possessions; // This will have getters and setters just like normal instance fields.
    // NOTE: since this will reference MANY possessions, we use a data-structure that can handle MANY results (Set).

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Possessions> getPossessions() {
        return possessions;
    }

    public void setPossessions(Set<Possessions> possessions) {
        this.possessions = possessions;
    }

    // This method is overridden so that the DATABASE record id is used for comparing object equality.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    // This method is overridden so that the object id will be tied to the DATABASE record id.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
