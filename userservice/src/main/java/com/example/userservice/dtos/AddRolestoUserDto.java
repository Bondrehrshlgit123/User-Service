package com.example.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AddRolestoUserDto {
    private List<Long> RoleIds;
}
