package com.sipra.blogapplication.services;

import com.sipra.blogapplication.payLoads.UserDto;
import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Long userId);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    void deleteUser(Long userId);
}
