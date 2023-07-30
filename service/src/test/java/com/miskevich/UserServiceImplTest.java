package com.miskevich;

import com.miskevich.domain.Role;
import com.miskevich.domain.SystemRoles;
import com.miskevich.domain.User;
import com.miskevich.dto.UserRequest;
import com.miskevich.mappers.UserMapper;
import com.miskevich.repository.RoleRepo;
import com.miskevich.repository.UserRepo;
import com.miskevich.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private RoleRepo roleRepo;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testCreateWithValidRequest() {
        UserRequest request = new UserRequest();
        request.setFirstname("Zhenya");
        request.setSurname("Hock");
        request.setLastname("Merlin");
        request.setEmail("vedroclsadsaub@gmail.com");
        request.setRole(SystemRoles.ROLE_ADMINISTRATOR);

        User mappedUser = new User();
        mappedUser.setFirstName("Zhenya");
        mappedUser.setSurname("Hock");
        mappedUser.setLastName("Merlin");
        mappedUser.setEmail("vedroclsadsaub@gmail.com");
        mappedUser.setRole(new Role());

        when(userMapper.convertCreateRequest(request)).thenReturn(mappedUser);
        when(roleRepo.findRoleByName(request.getRole().toString())).thenReturn(Optional.of(new Role()));
        when(userRepo.save(any())).thenReturn(mappedUser);

        User createdUser = userService.create(request);
        assertEquals("Zhenya", createdUser.getFirstName());
        assertEquals("vedroclsadsaub@gmail.com", createdUser.getEmail());
        Assertions.assertNotNull(createdUser.getRole());
    }

    @Test
    void testCreateWithInvalidEmailRequest() {
        UserRequest request = new UserRequest();
        request.setFirstname("Ivan");
        request.setSurname("Vedrovich");
        request.setLastname("Merlin");
        request.setEmail("vedroclub@gmail.com");
        request.setRole(SystemRoles.ROLE_ADMINISTRATOR);

        User mappedUser = new User();
        mappedUser.setFirstName("Ivan");
        mappedUser.setSurname("Vedrovich");
        mappedUser.setLastName("Merlin");
        mappedUser.setEmail("vedroclub@gmail.com");
        mappedUser.setRole(new Role());

        when(userMapper.convertCreateRequest(request)).thenReturn(mappedUser);
        when(roleRepo.findRoleByName(request.getRole().toString())).thenReturn(Optional.of(new Role()));
        when(userRepo.save(any())).thenReturn(mappedUser);

        User createdUser = userService.create(request);
        assertEquals("Ivan", createdUser.getFirstName());
        assertEquals("vedroclub@gmail.com", createdUser.getEmail());
        Assertions.assertNotNull(createdUser.getRole());
    }


}