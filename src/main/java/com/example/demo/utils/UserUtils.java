package com.example.demo.utils;

import com.example.demo.model.dto.account.UserDto;
import java.util.Comparator;

public class UserUtils implements Comparator <UserDto> {
    @Override
    public int compare(UserDto userDto1, UserDto userDto2) {
        return userDto1.getUserName().compareTo(userDto2.getUserName());
    }
}
