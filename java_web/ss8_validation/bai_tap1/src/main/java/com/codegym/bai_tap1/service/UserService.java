package com.codegym.bai_tap1.service;

import com.codegym.bai_tap1.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{
    private List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }
}

