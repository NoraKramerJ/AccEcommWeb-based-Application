package com.cydeo.controller;

import com.cydeo.annotation.ExecutionTime;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDto;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class UserController {

private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
@ExecutionTime
    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllUsers(){
    List<UserDto> userDtoList=userService.listAllUsers();
   return ResponseEntity
   .ok(new ResponseWrapper("All users are retrieved",userDtoList, HttpStatus.OK));
    }

    @ExecutionTime
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> getUserById(@PathVariable("id") Long id){
        UserDto userDto=userService.findById(id);
        return ResponseEntity.ok(new ResponseWrapper("User is retrieved",userDto,HttpStatus.OK));

    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDto userDto){
        userService.save(userDto);
        return ResponseEntity.ok(new ResponseWrapper("user is saved",HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDto userDto){
        userService.update(userDto);
        return ResponseEntity.ok(new ResponseWrapper("user is successfully updated", HttpStatus.OK));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper> deleteUserById(@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.ok(new ResponseWrapper("User is successfully deleted",HttpStatus.OK));
    }
}
