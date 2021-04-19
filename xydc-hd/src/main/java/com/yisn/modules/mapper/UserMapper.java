package com.yisn.modules.mapper;

import com.yisn.modules.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> findAllUser();

    void create(User user);

    User findByUserId(@Param("userId") int userId);

    void deleteByUserId(@Param("userId") int userId);

    void updateByUsesrId(User user);
}
