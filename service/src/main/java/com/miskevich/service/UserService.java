package com.miskevich.service;

import com.miskevich.domain.User;
import com.miskevich.dto.UserRequest;
import com.miskevich.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User create(UserRequest request);

    Page<UserResponse> findAll(Pageable pageable);

}
