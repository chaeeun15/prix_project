package com.prix.user.service;

import com.prix.user.DTO.UserDTO;
import com.prix.user.Entity.UserEntity;

public interface UserService {

    UserEntity register(UserDTO userDTO);

    boolean withdrawl(String userID);
}