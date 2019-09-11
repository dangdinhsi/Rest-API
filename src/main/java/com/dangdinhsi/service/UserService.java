package com.dangdinhsi.service;

import com.dangdinhsi.entity.User;
import com.dangdinhsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User create(User user){
        user.setId(Calendar.getInstance().getTimeInMillis());
        user.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        userRepository.save(user);
        return user;
    }
    public List<User> getList(){
        return userRepository.findAll();
    }
    public User update(User user){
        user.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        userRepository.save(user);
        return user;
    }

    public List<User> delete(long id,User user){
        user.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        user.setStatus(-1);
        userRepository.deleteById(id);
        return userRepository.findAll();
    }
    public User findById(long id){
        return userRepository.findById(id).orElse(null);
    }
}
