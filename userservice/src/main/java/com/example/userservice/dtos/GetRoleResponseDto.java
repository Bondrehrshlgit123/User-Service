package com.example.userservice.dtos;

import com.example.userservice.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRoleResponseDto {
    private Long id;
    private String role;
    public static GetRoleResponseDto from(Role role){
        GetRoleResponseDto getRoleResponseDto=new GetRoleResponseDto();
        getRoleResponseDto.setId(role.getId());
        getRoleResponseDto.setRole(role.getRole());
        return getRoleResponseDto;
    }
}
