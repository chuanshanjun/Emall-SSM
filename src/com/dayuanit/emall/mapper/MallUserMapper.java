package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallUser;
import com.dayuanit.emall.pojo.MallUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallUserMapper {
    long countByExample(MallUserExample example);

    int deleteByExample(MallUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallUser record);

    int insertSelective(MallUser record);

    List<MallUser> selectByExample(MallUserExample example);

    MallUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallUser record, @Param("example") MallUserExample example);

    int updateByExample(@Param("record") MallUser record, @Param("example") MallUserExample example);

    int updateByPrimaryKeySelective(MallUser record);

    int updateByPrimaryKey(MallUser record);
}