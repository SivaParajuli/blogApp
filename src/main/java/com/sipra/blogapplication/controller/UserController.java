package com.sipra.blogapplication.controller;

import com.sipra.blogapplication.payLoads.ResponseDto;
import com.sipra.blogapplication.payLoads.UserDto;
import com.sipra.blogapplication.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId")Long id){
        UserDto updatedUser = userService.updateUser(userDto,id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto>deleteUser(@PathVariable("userId")Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(new ResponseDto(true,"User Deleted Successfully"),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<ResponseDto> getAllUsers(){
        List<UserDto> userLists=userService.getAllUsers();
        return new ResponseEntity<>(new ResponseDto(true, userLists ,"All Users Fetched."),HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("userId")Long id){
        UserDto user=userService.getUserById(id);
        return new ResponseEntity<>(new ResponseDto(true,user,"User is fetched."),HttpStatus.OK);
    }
    @PostMapping("auth/register")
    public ResponseEntity<ResponseDto>registerUser(@Valid @RequestBody UserDto userDto){
        UserDto registeredUser = userService.registerUser(userDto);
        return new ResponseEntity<>(new ResponseDto(true,registeredUser,"User Registered"),HttpStatus.CREATED);
    }
}
