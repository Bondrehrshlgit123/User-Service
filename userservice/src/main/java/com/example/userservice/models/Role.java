package com.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Role extends BaseModel{
    private String role;
    @OneToMany(fetch = FetchType.EAGER,
            cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<User> users;
//    @ManyToMany(mappedBy ="use")
//    private Set<User> users;
//
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users = new LinkedHashSet<>();

}
