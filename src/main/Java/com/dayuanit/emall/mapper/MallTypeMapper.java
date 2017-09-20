package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallType;

import java.util.List;

public interface MallTypeMapper {

    List listType();

    int addType(MallType mallType);
}
