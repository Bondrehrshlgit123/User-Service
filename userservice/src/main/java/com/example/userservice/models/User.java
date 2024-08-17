package com.example.userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String email;
    private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = false)
    private Role role;
//@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//@JoinTable(
//        name = "user_roles",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id")
//)
//    private Set<Role> roles=new HashSet<>();

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "User_role",
//            joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new LinkedHashSet<>();

//    public void addRole(Role role) {
//        roles.add(role);
//        role.getUsers().add(this);
//    }

//    public void removeRole(Role role) {
//        roles.remove(role);
//        role.getUsers().remove(this);
//    }
   // @OneToMany(fetch = FetchType.EAGER,cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    //private Set<Role> roles=new HashSet<>();
}
