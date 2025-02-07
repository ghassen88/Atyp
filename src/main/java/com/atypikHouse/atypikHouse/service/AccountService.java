package com.atypikHouse.atypikHouse.service;


import com.atypikHouse.atypikHouse.model.AppRole;
import com.atypikHouse.atypikHouse.model.AppUser;

public interface AccountService {
    public AppUser saveUser(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String username,String roleName);
    public AppUser findUserByUsername(String username);
}
