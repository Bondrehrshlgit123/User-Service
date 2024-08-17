package com.example.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class AddRolestoUserDto {
    private Set<Long> RoleIds=new HashSet<>();
}
