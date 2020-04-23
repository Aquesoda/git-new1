package com.trainning.mysites.service.Impl;

import com.trainning.mysites.dao.UserRepository;
import com.trainning.mysites.domain.User;
import com.trainning.mysites.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void save(User u)throws Exception {
        try {
            userRepository.save(u);
            }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public Page<User> findAll(String kw, Pageable pageable) {
        return userRepository.findByKeyword(kw, pageable);
    }

    @Override
    public User findById(Integer uid) {
        return userRepository.findById(uid).get();  //通过get返回实体对象
    }

    @Override
    public void delete(User u) {
        userRepository.delete(u);
    }

    @Override
    public void deleteById(Integer uid) {
        userRepository.deleteById(uid);
    }
    @Transactional
    @Override
    public void deletes(List<User> users) {
        for (User u : users) {
            userRepository.delete(u);
        }
    }
}
