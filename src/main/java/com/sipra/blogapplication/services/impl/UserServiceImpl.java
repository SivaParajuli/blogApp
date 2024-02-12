package com.sipra.blogapplication.services.impl;

import com.sipra.blogapplication.config.AppConstants;
import com.sipra.blogapplication.exceptions.ResourceNotFoundException;
import com.sipra.blogapplication.models.Role;
import com.sipra.blogapplication.models.User;
import com.sipra.blogapplication.payLoads.UserDto;
import com.sipra.blogapplication.repo.RoleRepo;
import com.sipra.blogapplication.repo.UserRepo;
import com.sipra.blogapplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role(AppConstants.NORMAL_USER,"USER");
        Role userRole = roleRepo.save(role);
        user.setRoles(userRole);
        User registeredUser=userRepo.save(user);
        return userToDto(registeredUser);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user =this.dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return this.userToDto(savedUser);
    }
    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        User updatedUser = userRepo.save(user);
        return userToDto(updatedUser);
    }
    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return userToDto(user);
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepo.findAll();
        List<UserDto> users = userList.stream().map(this::userToDto).toList();
        return users;
    }
    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        userRepo.delete(user);
    }
    private User dtoToUser(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }
    private UserDto userToDto(User user){
        return modelMapper.map(user,UserDto.class);
    }
}
