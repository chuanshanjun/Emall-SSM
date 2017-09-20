package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallAddressMapper {

    int addAddress(MallAddress mallAddress);

    List<MallAddress> listAddress(@Param("userId") Integer userId);

    int modify(MallAddress mallAddress);

    int deleteAddress(@Param("addressId") Integer addressId);

    MallAddress getAddressById(@Param("addressId")Integer addressId);
}
