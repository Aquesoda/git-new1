package com.trainning.mysites.service.Impl;

import com.trainning.mysites.dao.UserRepository;
import com.trainning.mysites.domain.User;
import com.trainning.mysites.domain.UserLogin;
import com.trainning.mysites.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void save(User u)throws Exception {
        try {
            //存储当前的登陆时间
            u.setLasttime((int)Instant.now().getEpochSecond());

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

    /**
     * 核查用户是否合法的方法
     * @param user  用户的用户名和密码
     * @return  如果合法返回true 否则返回false
     */
    @Override
    public User checkUser(UserLogin user) {
        User u = null;
        //首先去数据库中查找用户信息
        Optional<User> ou = userRepository.findByAccount(user.getAccount());

        if (ou.isPresent()){
            u = ou.get();
            //将库中的密码与登录时密码比对是否一致
            if (u.getPassword().equals(user.getPassword())){
                return u;//返回用户对象
            }
        }
        return null;//身份不正确 直接返回null
    }
}
