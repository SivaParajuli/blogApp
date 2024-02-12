package com.sipra.blogapplication.payLoads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class UserDto {
    private Long userId;
    @NotEmpty
    @Size(min=3,message="Name must be at least 3 character")
    private String name;
    @Email(message = "Invalid email")
    private String email;
    @NotEmpty
    @Size(min=5, message="Password must be 5 character")
    private String password;
    private String about;
    private String image;
    private RoleDto roles;

    public UserDto(){
        super();
    }

    public UserDto(Long userId, String name, String email, String password, String about, String image, RoleDto roles) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.image = image;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RoleDto getRoles() {
        return roles;
    }
    public void setRoles(RoleDto roles) {
        this.roles = roles;
    }
}
