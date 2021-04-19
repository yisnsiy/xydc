package com.yisn.modules.service;


import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.User;

public interface UserService {
    PageInfo<User> findPaperByPage(int pageNum, int pageSize);

    void create(User user);

    User findByUserId(int userId);

    void deleteByUserId(int userId);

    void updateByUserId(User user);
}
