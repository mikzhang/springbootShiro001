package com.ran.springbootShiro001.mapper;

import com.ran.springbootShiro001.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User findByUsername(String username);
}
