package com.trainning.mysites.dao;

import com.trainning.mysites.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where account like ?1 or name like ?1 or mobile like ?1 or email like ?1")
    public Page<User> findByKeyword(String kw, Pageable pageable);


    @Query("update User u set u.password=?1 where u.uid=?2")
    public void modifyPassword(String pwd,Integer uid);

    //如果要通过手机号码，邮箱 可以自定义一个方法 如第一个查询方法 查询时用精准查询
    public Optional<User> findByAccount(String account);
}
