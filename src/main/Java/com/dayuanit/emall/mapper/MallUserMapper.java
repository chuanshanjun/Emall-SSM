package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallUser;
import org.apache.ibatis.annotations.Param;

public interface MallUserMapper {

    int addMallUser(MallUser mallUser);

    MallUser getMallUserByUserName(@Param("username") String username);

}
