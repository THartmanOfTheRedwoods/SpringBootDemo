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
    // This is the Many side of a 1:M relationship.
    // Lazy means don't fetch until accessed.
    // Optional=false means participation in the relationship is NOT optional
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="user_id", nullable=false) // This is the table column (i.e. FK) we expect to join on.
    // The next 2 annotations will simply put "user": id in Json REST responses instead of nesting a User object which
    // contains a reference to multiple other Possessions in the Json response.
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user; // This will have getters and setters just like normal instance fields

    @Column(unique=true) // Must have a unique constraint since we don't want duplicate UPC/QR codes.
    private String itemLabel; // This should be a UPC or QR code

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

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    // This method is overridden so that the DATABASE record id is used for comparing object equality.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Possessions possessions = (Possessions)o;
        return id.equals(possessions.id);
    }

    // This method is overridden so that the object id will be tied to the DATABASE record id.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}