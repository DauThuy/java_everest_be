package com.example.demo.util;

import com.example.demo.model.dto.UserDto;

import java.util.Comparator;

public class UserUtils implements Comparator <UserDto> {
    @Override
    public int compare(UserDto userDto1, UserDto userDto2) {
        return userDto1.getUserId() - userDto2.getUserId();
    }
}
