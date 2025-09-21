package com.mrm.bookMyShow.Service;

import com.mrm.bookMyShow.DTO.UserDto;
import com.mrm.bookMyShow.Exception.ResourceNotFoundException;
import com.mrm.bookMyShow.Model.User;
import com.mrm.bookMyShow.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDto createUser(UserDto userDto){
        User user = mapToEntity(userDto);
        User savedUser = userRepo.save(user);
        return mapToDto(savedUser);
    }

    public UserDto getUserById(Long id){
        User user=userRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id:"+id));
        return mapToDto(user);
    }

    public List<UserDto> getAllUser(){
        List<User> users = userRepo.findAll();
        return users.
                stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }



    //update user
    //delete user

    private User mapToEntity(UserDto userDto){
        User user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setMobileNumber(userDto.getPhoneNumber());
        return user;
    }

    private UserDto mapToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getMobileNumber());
        return userDto;
    }
}
