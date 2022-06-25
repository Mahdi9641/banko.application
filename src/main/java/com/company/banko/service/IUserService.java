package com.company.banko.service;


import com.company.banko.domain.User;
import com.company.banko.model.UserDTO;

public interface IUserService {

    User registerNewUserAccount(UserDTO accountDto);
}
