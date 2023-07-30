package com.miskevich.controller;

import com.miskevich.domain.User;
import com.miskevich.dto.UserRequest;
import com.miskevich.dto.UserResponse;
import com.miskevich.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> findAll(Pageable pageable) {
        Page<UserResponse> users = userService.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> CreatUsers(@Valid @RequestBody UserRequest createRequest) {
        User createdUser = userService.create(createRequest);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}