package com.miskevich.service;

import com.miskevich.domain.Role;
import com.miskevich.domain.User;
import com.miskevich.dto.UserRequest;
import com.miskevich.dto.UserResponse;
import com.miskevich.mappers.UserMapper;
import com.miskevich.repository.RoleRepo;
import com.miskevich.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final UserMapper userMapper;


    @Override
    @Transactional
    public User create(UserRequest request) {
        log.info("Create new user : {}", request.toString());

        User createUser = userMapper.convertCreateRequest(request);
        Optional<Role> userRole = roleRepo.findRoleByName(request.getRole().toString());

        if (userRole.isPresent()) {
            createUser.setRole(userRole.get());
            log.info("Role sets  {}", userRole.get().getName());
        }

        User createdUser = userRepo.save(createUser);
        log.info("User create  {}", createdUser);
        return createdUser;
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        log.info("All users");
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("email").ascending());
        return userRepo.findAll(sortedPageable).map(userMapper::toUserResponse);
    }
}
