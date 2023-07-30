package com.miskevich.mappers;

import com.miskevich.domain.User;
import com.miskevich.dto.UserRequest;
import com.miskevich.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "firstname", target = "firstName")
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "email", target = "email")
    User convertCreateRequest(UserRequest userRequest);

    @Mapping(target = "fullname", expression = "java(user.getSurname() + \" \" + user.getFirstName() + \" \" + user.getLastName())")
    @Mapping(source = "role.name", target = "role")
    UserResponse toUserResponse(User user);
}
