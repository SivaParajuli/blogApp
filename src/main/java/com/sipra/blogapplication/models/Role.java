package com.sipra.blogapplication.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tbl_role")
public class Role {
    @Id
    private Long roleId;
    private String roleName;
    @OneToMany
    private List<User> users;

    public Role(){
    }

    public Role(Long roleId, String roleName, List<User> users) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
    }
    public Role(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
