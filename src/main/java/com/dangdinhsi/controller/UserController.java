package com.dangdinhsi.controller;

import com.dangdinhsi.entity.User;
import com.dangdinhsi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    // them user
    @PostMapping(value = "/create")
    public ResponseEntity<User> addUser(@RequestBody User obj){
        try {
           User createUser = userService.create(obj);
           return new ResponseEntity<>(createUser,HttpStatus.CREATED);

        }catch (Exception ex){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //list user
   @GetMapping()
    public ResponseEntity<List<User>> listUser(){
        List<User> userList = userService.getList();
        return new ResponseEntity<>(userList,HttpStatus.OK);
   }

   //get user theo id
   @GetMapping(value = "/{id}")
    public ResponseEntity<User> detailUser(@PathVariable long id){
        User user = userService.findById(id);
        if(user==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
   }

   //delete user theo id
   @DeleteMapping(value = "/{id}")
   public ResponseEntity<List<User>> deleteUser(@PathVariable long id){

       User existUser =userService.findById(id);
       if(existUser==null){
           return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
       }else {
           userService.delete(id,existUser);
           List<User> list = userService.getList();
           return new ResponseEntity<>(list,HttpStatus.OK);
       }
   }

   //edit user theo id
   @PutMapping(value = "/{id}")
    public ResponseEntity<User> editUser(@PathVariable long id,@RequestBody User obj){
        User existUser =userService.findById(id);
        if(existUser==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }else {
            existUser.setUsername(obj.getUsername());
            existUser.setPassword(obj.getPassword());
            existUser.setStatus(2);
            userService.update(existUser);
            return new ResponseEntity<>(existUser,HttpStatus.OK);
        }
   }
}
