package com.yisn.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.User;
import com.yisn.modules.mapper.UserMapper;
import com.yisn.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findPaperByPage(User user, int pageNum, int pageSize) {
//        设置第几页，每页显示的数量
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.find(user);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public void create(User user) {
        userMapper.create(user);
        return;
    }

    @Override
    public User findByUserId(int userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public void deleteByUserId(int userId) {
        userMapper.deleteByUserId(userId);
        return;
    }

    @Override
    public void updateByUserId(User user) {
        userMapper.updateByUsesrId(user);
        return;
    }
}
