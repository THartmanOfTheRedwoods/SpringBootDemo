package edu.redwoods.cis18.springdemo.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity // This tells Hibernate to make a table out of this class
public class Possessions implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) // This is the Many side.
    @JoinColumn(name="user_id", nullable=false) // This is the table column (i.e. FK) we expect to join on.
    // The next 2 annotations will simply put "user": id in REST responses instead of nesting a User object which contains
    // references to Possessions
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user; // This will have getters and setters just like normal

    @Column(unique=true) // Must have a unique constraint.
    private String itemLabel;  // This should be a upc or qr code

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Possessions possessions = (Possessions)o;
        return id.equals(possessions.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }
}
